pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        // PdfBox-Android se publica en Maven Central desde la 2.x — sin repos de terceros necesarios.
    }
}

rootProject.name = "rellenador-pdv"

include(":app")

include(":core:domain")
include(":core:data")
include(":core:pdf")
include(":core:designsystem")

include(":feature:contrato")
include(":feature:documentacion")
include(":feature:revision")
include(":feature:firma")
