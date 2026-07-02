// Build script raíz — solo declara plugins por versión (apply false).
// Cada módulo los aplica según lo que necesite. Ver /docs/FASE_1_ENTREGA.md
// para el porqué de cada elección de esta Fase 1.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.jvm) apply false
    alias(libs.plugins.kotlin.serialization) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.hilt) apply false
}

tasks.register("clean", Delete::class) {
    delete(rootProject.layout.buildDirectory)
}
