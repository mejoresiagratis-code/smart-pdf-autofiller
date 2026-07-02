package com.mejoresiagratis.rellenadorpdv.domain.model

/**
 * Equivalente a una entrada de `S.fields` en rellenador-pro.html.
 * `name` conserva el nombre LITERAL del AcroForm (incluidos los dobles
 * espacios reales del contrato, ej. "Nombre  Razón Social" — ver
 * auditoría §08) porque el relleno final tiene que casar exacto con el PDF.
 */
data class PdfField(
    val id: String,
    val name: String,
    val type: PdfFieldType,
    val pageIndices: List<Int>,
    val value: String? = null,
    val source: String? = null, // motor de IA que propuso el valor actual, si aplica
)

enum class PdfFieldType { TEXT, CHECKBOX }

/**
 * Hueco de firma detectado. `hasFormField = false` es el caso especial de
 * la página 24 del contrato PdV jun26 (bloque "EL DISTRIBUIDOR" sin ningún
 * campo AcroForm) — ver applyKnownContractFixes() en la auditoría §04.1.
 */
data class SignatureGap(
    val pageIndex: Int,
    val label: String,
    val hasFormField: Boolean,
    val anchorField: String? = null,
)

/** Una propuesta de valor para un campo, con qué motor(es) de IA la encontraron. */
data class FieldCandidate(
    val value: String,
    val sources: List<String>,
)

/** Bloque de datos relacionados que se aplican de una vez (dirección, empresa, persona, banco). */
data class DataPackage(
    val type: PackageType,
    val label: String,
    val source: String,
    val data: Map<String, String>, // claves = nombres canónicos SIN sufijo, ver CANON_KEYS en la auditoría
)

enum class PackageType { DIRECCION, DIRECCION_COMERCIO, EMPRESA, PERSONA, BANCO }

enum class IdentificationType { NIF, NIE, CIF }

/** Resultado crudo de un único motor de IA para un único documento — antes de fusionar (mergeOne). */
data class ExtractionResult(
    val providerId: String,
    val suggestions: Map<String, String>,
    val alternatives: Map<String, List<FieldCandidate>>,
    val packages: List<DataPackage>,
    val identificationType: IdentificationType?,
)

/** Vista fusionada por campo tras combinar todos los motores — lo que ve el paso 3 (Revisión). */
data class FieldFindings(
    val fieldId: String,
    val fieldName: String,
    val options: List<FieldCandidate>,
)
