package com.mejoresiagratis.rellenadorpdv.pdf

import com.tom_roush.pdfbox.pdmodel.PDDocument
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Único punto del proyecto que importa `com.tom_roush.pdfbox.*` — decisión
 * de arquitectura de la auditoría (§06, módulo `:core:pdf` dedicado):
 * si algún día hay que cambiar a iText 7 u otra librería, el cambio queda
 * contenido aquí y no se propaga a `core:data` ni a las features.
 *
 * `@Singleton`: PDFBox mantiene handles nativos sobre el documento cargado;
 * tener más de una instancia abierta a la vez con un contrato de 54 páginas
 * es exactamente el problema de memoria que señala la auditoría (§07,
 * "documentos grandes en memoria").
 *
 * Implementación real (leer AcroForm, listar campos, rellenar respetando
 * /DA, mapear campo→páginas) es tarea de Fase 2 — aquí solo el contrato
 * y el ciclo de vida del PDDocument, que sí es infraestructura de Fase 1.
 */
@Singleton
class PdfBoxEngine @Inject constructor() {

    private var openDocument: PDDocument? = null

    fun open(bytes: ByteArray): Result<Unit> = runCatching {
        close() // nunca más de un documento abierto a la vez
        openDocument = PDDocument.load(bytes)
    }

    fun isOpen(): Boolean = openDocument != null

    fun close() {
        openDocument?.close()
        openDocument = null
    }

    // TODO Fase 2 — equivalentes a:
    //   fun listFields(): List<PdfField>          (origen: loadPdf() + widgetPages())
    //   fun fillField(name: String, value: String) (origen: fillFormFields())
    //   fun save(): ByteArray                       (origen: buildPreviewBytes())
    //   fun pageCount(): Int
}
