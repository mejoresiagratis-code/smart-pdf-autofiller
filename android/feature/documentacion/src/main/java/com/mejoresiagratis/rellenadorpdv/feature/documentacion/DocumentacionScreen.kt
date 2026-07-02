package com.mejoresiagratis.rellenadorpdv.feature.documentacion

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.UploadFile
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * Paso 2 · Documentación — placeholder de Fase 1/2.
 * Implementación real en Fase 3: subida de DNI/censal/banco, chips de
 * proveedores de IA, barrera de consentimiento RGPD (ver mockup → panel2).
 */
@Composable
fun DocumentacionScreen(onContinue: () -> Unit) {
    Scaffold(topBar = { TopAppBar(title = { Text("Documentación") }) }) { padding ->
        Column(
            modifier = Modifier.padding(padding).fillMaxSize().padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Icon(Icons.Rounded.UploadFile, contentDescription = null, modifier = Modifier.size(48.dp))
            Spacer(Modifier.height(12.dp))
            Text("Paso 2 · Documentación", style = MaterialTheme.typography.headlineSmall)
            Spacer(Modifier.height(6.dp))
            Text(
                "Pendiente de Fase 3 — subida de documentos y extracción multi-IA.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }
    }
}
