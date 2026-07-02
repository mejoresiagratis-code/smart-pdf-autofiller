package com.mejoresiagratis.rellenadorpdv.feature.contrato

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Description
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * Paso 1 · Contrato — placeholder de Fase 1/2.
 * Implementación real en Fase 3: botón "Usar contrato base", dropzone,
 * tarjeta de plantilla detectada (ver mockup-visual-app-android.html →
 * panel1 para el diseño ya validado que hay que reproducir aquí).
 */
@Composable
fun ContratoScreen(onContinue: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Contrato") })
        },
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Icon(Icons.Rounded.Description, contentDescription = null, modifier = Modifier.size(48.dp))
            Spacer(Modifier.height(12.dp))
            Text("Paso 1 · Contrato", style = MaterialTheme.typography.headlineSmall)
            Spacer(Modifier.height(6.dp))
            Text(
                "Pendiente de Fase 3 — carga de PDF, contrato base y detección de plantilla.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }
    }
}
