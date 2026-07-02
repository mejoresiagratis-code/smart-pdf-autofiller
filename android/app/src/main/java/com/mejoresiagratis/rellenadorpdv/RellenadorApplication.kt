package com.mejoresiagratis.rellenadorpdv

import android.app.Application
import com.tom_roush.pdfbox.android.PDFBoxResourceLoader
import dagger.hilt.android.HiltAndroidApp

/**
 * Raíz del grafo de Hilt. Aquí, y solo aquí, se inicializa PDFBox-Android:
 * necesita el contexto de la app una vez al arrancar (carga sus recursos
 * de fuentes/cmaps embebidos) antes de que cualquier PdfRepository lo use.
 *
 * Ver docs/FASE_1_ENTREGA.md → "Por qué PDFBoxResourceLoader va aquí y no
 * en el propio repositorio": evita inicializarlo más de una vez si en el
 * futuro hay más de un punto de entrada (widgets, share targets, etc.).
 */
@HiltAndroidApp
class RellenadorApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        PDFBoxResourceLoader.init(applicationContext)
    }
}
