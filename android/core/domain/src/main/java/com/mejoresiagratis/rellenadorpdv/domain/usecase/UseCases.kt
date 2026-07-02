package com.mejoresiagratis.rellenadorpdv.domain.usecase

import com.mejoresiagratis.rellenadorpdv.domain.model.*
import com.mejoresiagratis.rellenadorpdv.domain.repository.AiProxyRepository
import com.mejoresiagratis.rellenadorpdv.domain.repository.DocumentPayload
import com.mejoresiagratis.rellenadorpdv.domain.repository.PdfRepository
import javax.inject.Inject

/**
 * ── Fase 1: solo contratos ──
 * Las clases de este archivo están listas para inyección (Hilt las resolverá
 * en cuanto se usen desde un ViewModel) pero su lógica de negocio es la
 * tarea de la Fase 2, tal y como fija el plan de ruta modular. Portar aquí
 * el prompt de extracción o las reglas fiscal/comercio antes de tener los
 * tests JUnit de esta fase sería saltarse el propio plan que se aprobó.
 *
 * Cada firma de función ya refleja la lógica real auditada en
 * rellenador-pro.html (nombre de función original entre paréntesis) para
 * que la Fase 2 sea "rellenar el cuerpo", no "redecidir la forma".
 */

/** Origen: validDNI()/validNIEp()/validCIF() — funciones puras, riesgo de migración prácticamente nulo. */
class ValidateIdentificationUseCase @Inject constructor() {
    fun validateDni(value: String): Boolean = TODO("Fase 2 — portar validDNI() de rellenador-pro.html")
    fun validateNie(value: String): Boolean = TODO("Fase 2 — portar validNIEp() de rellenador-pro.html")
    fun validateCif(value: String): Boolean = TODO("Fase 2 — portar validCIF() de rellenador-pro.html")
    fun validateAny(value: String): Boolean = TODO("Fase 2 — portar validIdAny() de rellenador-pro.html")
}

/** Origen: validIBAN() — dígitos de control ES incluidos. */
class ValidateIbanUseCase @Inject constructor() {
    operator fun invoke(value: String): Boolean = TODO("Fase 2 — portar validIBAN() de rellenador-pro.html")
}

/**
 * Origen: el prompt de extracción en español (~65 líneas) + callProvider()/
 * callViaProxy(). No se toca ni una coma del prompt real — se porta literal
 * en Fase 2, ver auditoría §04.5.
 */
class ExtractDataFromDocumentUseCase @Inject constructor(
    private val aiProxyRepository: AiProxyRepository,
) {
    suspend operator fun invoke(
        providerId: String,
        documentPayload: DocumentPayload,
        knownFieldNames: List<String>,
        templateHint: Map<String, String>?,
    ): Result<ExtractionResult> = TODO("Fase 2 — portar el prompt real + parseAIJson()")
}

/** Origen: mergeOne()/normVal() — fusiona resultados de varios motores sin duplicar candidatos. */
class MergeExtractionResultsUseCase @Inject constructor() {
    operator fun invoke(results: List<ExtractionResult>, knownFields: List<PdfField>): List<FieldFindings> =
        TODO("Fase 2 — portar mergeOne()/normVal()")
}

/** Origen: buildSignPages() + applyKnownContractFixes() — incluye el fix de la página 24 sin AcroForm. */
class DetectSignatureGapsUseCase @Inject constructor(
    private val pdfRepository: PdfRepository,
) {
    suspend operator fun invoke(): Result<List<SignatureGap>> =
        TODO("Fase 2 — portar buildSignPages()/applyKnownContractFixes()")
}

/** Origen: templateFingerprint()/detectTemplate()/applyTemplateMapping(). */
class ApplyContractTemplateUseCase @Inject constructor() {
    fun fingerprint(fields: List<PdfField>, totalPages: Int): String =
        TODO("Fase 2 — portar templateFingerprint()")
}

/** Origen: fillFormFields()/fitFontSize()/readFontModel() — respeta el tamaño de fuente que define el AcroForm. */
class FillPdfFieldsUseCase @Inject constructor(
    private val pdfRepository: PdfRepository,
) {
    suspend operator fun invoke(values: Map<String, String>): Result<ByteArray> =
        TODO("Fase 2 — portar fillFormFields()/fitFontSize()")
}

/** Origen: autoFillDates() — día/mes/año autorrellenados al cargar el PDF. */
class AutoFillDatesUseCase @Inject constructor() {
    operator fun invoke(today: java.time.LocalDate = java.time.LocalDate.now()): Map<String, String> =
        TODO("Fase 2 — portar autoFillDates()")
}
