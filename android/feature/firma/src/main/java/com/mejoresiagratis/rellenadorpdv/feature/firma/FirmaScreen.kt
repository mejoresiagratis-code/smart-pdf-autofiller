package com.mejoresiagratis.rellenadorpdv.feature.firma

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Draw
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * Paso 4 · Firma — placeholder de Fase 1/2.
 * Implementación real en Fase 4: SignaturePad (Canvas + detectDragGestures),
 * importar/mejorar firma por foto, selector de páginas, vista previa final
 * (ver mockup → panel4/5/6, ya validado visualmente y aprobado).
 */
@Composable
fun FirmaScreen(onFinish: () -> Unit) {
    Scaffold(topBar = { TopAppBar(title = { Text("Firma") }) }) { padding ->
        Column(
            modifier = Modifier.padding(padding).fillMaxSize().padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Icon(Icons.Rounded.Draw, contentDescription = null, modifier = Modifier.size(48.dp))
            Spacer(Modifier.height(12.dp))
            Text("Paso 4 · Firma", style = MaterialTheme.typography.headlineSmall)
            Spacer(Modifier.height(6.dp))
            Text(
                "Pendiente de Fase 4 — pad de firma, foto, páginas y vista previa.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }
    }
}
