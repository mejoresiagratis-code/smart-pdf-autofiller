package com.mejoresiagratis.rellenadorpdv.data.repository

import com.mejoresiagratis.rellenadorpdv.domain.model.PdfField
import com.mejoresiagratis.rellenadorpdv.domain.model.SignatureGap
import com.mejoresiagratis.rellenadorpdv.domain.repository.PdfRepository
import com.mejoresiagratis.rellenadorpdv.pdf.PdfBoxEngine
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Implementación real de PdfRepository. `@Singleton` + `PdfBoxEngine`
 * también Singleton: un único documento en memoria en toda la app, tal y
 * como fija la Fase 1 del plan de ruta ("evita duplicar instancias de
 * memoria al procesar archivos grandes").
 *
 * Los StateFlow arrancan vacíos a propósito — se rellenan en Fase 2 cuando
 * loadDocument() tenga cuerpo real (loadPdf()/widgetPages() portados).
 */
@Singleton
class PdfRepositoryImpl @Inject constructor(
    private val engine: PdfBoxEngine,
) : PdfRepository {

    private val _fields = MutableStateFlow<List<PdfField>>(emptyList())
    override val fields = _fields.asStateFlow()

    private val _signatureGaps = MutableStateFlow<List<SignatureGap>>(emptyList())
    override val signatureGaps = _signatureGaps.asStateFlow()

    private val _totalPages = MutableStateFlow(0)
    override val totalPages = _totalPages.asStateFlow()

    override suspend fun loadDocument(bytes: ByteArray, sourceName: String): Result<Unit> {
        val opened = engine.open(bytes)
        // TODO Fase 2: poblar _fields/_signatureGaps/_totalPages a partir del
        // AcroForm real (loadPdf() + widgetPages() + buildSignPages() +
        // applyKnownContractFixes() + detectTemplate() + autoFillDates()).
        return opened
    }

    override suspend fun updateFieldValue(fieldId: String, value: String) {
        _fields.value = _fields.value.map { if (it.id == fieldId) it.copy(value = value) else it }
    }

    override suspend fun buildFilledBytes(): Result<ByteArray> =
        Result.failure(NotImplementedError("Fase 2 — portar fillFormFields()/buildPreviewBytes()"))

    override suspend fun stampSignature(
        pageIndex: Int, signatureImage: ByteArray, x: Float, y: Float, width: Float, height: Float,
    ): Result<Unit> =
        Result.failure(NotImplementedError("Fase 4 — módulo de firma"))

    override fun clear() {
        engine.close()
        _fields.value = emptyList()
        _signatureGaps.value = emptyList()
        _totalPages.value = 0
    }
}
