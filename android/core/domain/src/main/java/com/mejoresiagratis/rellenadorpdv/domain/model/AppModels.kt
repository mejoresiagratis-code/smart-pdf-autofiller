package com.mejoresiagratis.rellenadorpdv.domain.model

/** Región del proveedor — pieza clave del modo "solo motores UE" (RGPD). */
enum class ProviderRegion { EU, NON_EU }

/**
 * Los 9 proveedores reales de ai-proxy.php (auditoría §04.5). `pdfSupport`
 * distingue si el motor recibe el PDF nativo (visión) o el texto ya
 * extraído en cliente por pdf.js — Groq y los motores UE reciben texto.
 */
data class AiProvider(
    val id: String,
    val label: String,
    val region: ProviderRegion,
    val pdfSupport: PdfSupport,
    val enabled: Boolean,
    val hasKeyOnServer: Boolean, // viene del GET a ai-proxy.php, nunca se guarda clave en el cliente
)

enum class PdfSupport { NATIVE_VISION, TEXT_ONLY, NONE }

/** Plantilla guardada: huella de contrato → mapeo de nombres reales a claves canónicas (CANON_KEYS). */
data class ContractTemplate(
    val id: String,
    val fingerprint: String, // nº páginas + nombres de campo normalizados, ver templateFingerprint()
    val fieldMapping: Map<String, String>, // clave canónica -> nombre real del AcroForm
    val label: String,
)

data class ContractHistoryEntry(
    val id: String,
    val label: String,
    val fingerprint: String,
    val fieldValues: Map<String, String>,
    val savedAtEpochMillis: Long,
)

data class SavedSignature(
    val id: String,
    val imagePath: String, // ruta en almacenamiento interno, NO blob en Room — ver auditoría §04.8
    val aspectRatio: Float,
    val savedAtEpochMillis: Long,
)

/** Estado persistido en DataStore: tema, modo UE, consentimiento — ver auditoría §04.9. */
data class UserPreferences(
    val darkTheme: Boolean?, // null = seguir al sistema
    val euOnlyMode: Boolean,
    val consentRemembered: Boolean,
)
