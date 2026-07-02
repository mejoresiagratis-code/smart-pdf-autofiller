plugins {
    alias(libs.plugins.kotlin.jvm)
}

// Módulo deliberadamente sin el plugin de Android: el dominio no debe
// poder importar nada de android.* por accidente. Así se cumple "Domain ·
// Kotlin puro (sin Android SDK, testeable con JUnit)" del informe de
// arquitectura sin depender de disciplina manual — el propio build falla
// si alguien intenta usar Context, Bitmap, etc. aquí.

dependencies {
    implementation(libs.kotlinx.coroutines.core) // solo por Flow/suspend; sin dependencias de plataforma Android
    implementation(libs.javax.inject) // @Inject en los UseCase sin acoplar el dominio a Hilt/Android
    testImplementation(libs.junit)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.turbine)
    testImplementation(libs.mockk)
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}
