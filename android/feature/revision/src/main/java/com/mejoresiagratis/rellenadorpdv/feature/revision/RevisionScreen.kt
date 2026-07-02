package com.mejoresiagratis.rellenadorpdv.feature.revision

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.FactCheck
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * Paso 3 · Revisión — placeholder de Fase 1/2.
 * Implementación real en Fase 3: campos dinámicos agrupados, popover de
 * candidatos por campo, chips de validación DNI/NIE/CIF/IBAN (ver mockup → panel3).
 */
@Composable
fun RevisionScreen(onContinue: () -> Unit) {
    Scaffold(topBar = { TopAppBar(title = { Text("Revisión") }) }) { padding ->
        Column(
            modifier = Modifier.padding(padding).fillMaxSize().padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Icon(Icons.Rounded.FactCheck, contentDescription = null, modifier = Modifier.size(48.dp))
            Spacer(Modifier.height(12.dp))
            Text("Paso 3 · Revisión", style = MaterialTheme.typography.headlineSmall)
            Spacer(Modifier.height(6.dp))
            Text(
                "Pendiente de Fase 3 — formulario dinámico y candidatos de IA.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }
    }
}
