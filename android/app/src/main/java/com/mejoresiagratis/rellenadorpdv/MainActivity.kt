package com.mejoresiagratis.rellenadorpdv

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.mejoresiagratis.rellenadorpdv.designsystem.component.WizardNavigationBar
import com.mejoresiagratis.rellenadorpdv.designsystem.component.WizardStep
import com.mejoresiagratis.rellenadorpdv.designsystem.theme.RellenadorTheme
import com.mejoresiagratis.rellenadorpdv.feature.contrato.ContratoScreen
import com.mejoresiagratis.rellenadorpdv.feature.documentacion.DocumentacionScreen
import com.mejoresiagratis.rellenadorpdv.feature.firma.FirmaScreen
import com.mejoresiagratis.rellenadorpdv.feature.revision.RevisionScreen
import dagger.hilt.android.AndroidEntryPoint

/**
 * Punto de entrada único. La navegación entre los 4 pasos vive aquí, no
 * dentro de cada feature — así cada módulo :feature:* no conoce a los
 * demás y no hay dependencias cruzadas entre pantallas (auditoría §06).
 *
 * `maxUnlockedOrdinal` reproduce S.maxStep de rellenador-pro.html: no se
 * puede saltar a un paso futuro hasta completar el actual. En Fase 1 se
 * deja siempre en el máximo (todo desbloqueado) para poder navegar y ver
 * los 4 placeholders; en Fase 3 pasa a vivir en un ViewModel compartido.
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RellenadorTheme {
                RellenadorApp()
            }
        }
    }
}

private val routeByStep = mapOf(
    WizardStep.Contrato to "contrato",
    WizardStep.Documentacion to "documentacion",
    WizardStep.Revision to "revision",
    WizardStep.Firma to "firma",
)

@Composable
private fun RellenadorApp() {
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route ?: "contrato"
    val currentStep = WizardStep.entries.firstOrNull { routeByStep[it] == currentRoute } ?: WizardStep.Contrato

    Scaffold(
        bottomBar = {
            WizardNavigationBar(
                current = currentStep,
                maxUnlockedOrdinal = WizardStep.entries.lastIndex, // TODO Fase 3: sustituir por el progreso real
                onStepSelected = { step ->
                    navController.navigate(routeByStep.getValue(step)) {
                        popUpTo(navController.graph.startDestinationId) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
            )
        },
    ) { padding ->
        NavHost(
            navController = navController,
            startDestination = "contrato",
            modifier = Modifier.padding(padding),
        ) {
            composable("contrato") {
                ContratoScreen(onContinue = { navController.navigate("documentacion") })
            }
            composable("documentacion") {
                DocumentacionScreen(onContinue = { navController.navigate("revision") })
            }
            composable("revision") {
                RevisionScreen(onContinue = { navController.navigate("firma") })
            }
            composable("firma") {
                FirmaScreen(onFinish = { /* TODO Fase 4: exportar/compartir PDF final */ })
            }
        }
    }
}
