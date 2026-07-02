package com.mejoresiagratis.rellenadorpdv.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Description
import androidx.compose.material.icons.rounded.Draw
import androidx.compose.material.icons.rounded.FactCheck
import androidx.compose.material.icons.rounded.UploadFile
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.mejoresiagratis.rellenadorpdv.designsystem.theme.ExtraShapes

/**
 * Los 4 pasos reales del wizard (rellenador-pro.html: data-step 1-4).
 * Las pantallas de "Páginas de firma" y "Vista previa" del mockup son
 * sub-pantallas del paso Firma, no pasos nuevos — mismo criterio que ya
 * se usó al diseñar el mockup visual, para no romper el modelo mental
 * de 4 pasos que el usuario final del PdV ya conoce de la web.
 */
enum class WizardStep(val label: String, val icon: ImageVector) {
    Contrato("Contrato", Icons.Rounded.Description),
    Documentacion("Docs", Icons.Rounded.UploadFile),
    Revision("Revisión", Icons.Rounded.FactCheck),
    Firma("Firma", Icons.Rounded.Draw),
}

@Composable
fun WizardNavigationBar(
    current: WizardStep,
    maxUnlockedOrdinal: Int,
    onStepSelected: (WizardStep) -> Unit,
    modifier: Modifier = Modifier,
) {
    NavigationBar(modifier = modifier) {
        WizardStep.entries.forEach { step ->
            val unlocked = step.ordinal <= maxUnlockedOrdinal
            NavigationBarItem(
                selected = step == current,
                enabled = unlocked,
                onClick = { onStepSelected(step) },
                icon = { Icon(step.icon, contentDescription = step.label) },
                label = { Text(step.label) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    indicatorColor = MaterialTheme.colorScheme.primaryContainer,
                    unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                ),
            )
        }
    }
}

/** Indicador de "paso completado" (pastilla naranja) para las miguitas superiores del wizard. */
@Composable
fun StepPill(active: Boolean, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .height(4.dp)
            .background(
                color = if (active) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceContainerHigh,
                shape = ExtraShapes.Chip,
            ),
    )
}
