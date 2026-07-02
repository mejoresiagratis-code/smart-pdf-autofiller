import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
}

// Lee local.properties (nunca versionado) para inyectar config vía BuildConfig,
// siguiendo el mismo patrón .env que pide el protocolo del proyecto.
val localProps = Properties().apply {
    val f = rootProject.file("local.properties")
    if (f.exists()) f.inputStream().use { load(it) }
}
fun localProp(key: String, default: String) = (localProps.getProperty(key) ?: default)

android {
    namespace = "com.mejoresiagratis.rellenadorpdv"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.mejoresiagratis.rellenadorpdv"
        minSdk = 26 // AcroForm + Canvas de firma + CameraX cómodos desde Android 8.0
        targetSdk = 35
        versionCode = 1
        versionName = "0.1.0-fase1"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        buildConfigField(
            "String", "AI_PROXY_BASE_URL",
            "\"${localProp("AI_PROXY_BASE_URL", "https://mejoresiagratis.com/pdf/")}\""
        )
        buildConfigField(
            "String", "BASE_CONTRACT_URL",
            "\"${localProp("BASE_CONTRACT_URL", "https://mejoresiagratis.com/pdf/contrato-base.pdf")}\""
        )
    }

    buildTypes {
        debug {
            buildConfigField("Boolean", "NETWORK_LOGGING", localProp("DEBUG_NETWORK_LOGGING", "true"))
        }
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            buildConfigField("Boolean", "NETWORK_LOGGING", "false")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions { jvmTarget = "17" }

    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.composeCompiler.get()
    }
    packaging {
        resources.excludes.add("/META-INF/{AL2.0,LGPL2.1}")
    }
}

dependencies {
    // Módulos internos
    implementation(project(":core:domain"))
    implementation(project(":core:data"))
    implementation(project(":core:pdf"))
    implementation(project(":core:designsystem"))
    implementation(project(":feature:contrato"))
    implementation(project(":feature:documentacion"))
    implementation(project(":feature:revision"))
    implementation(project(":feature:firma"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)

    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.material.icons.extended)
    implementation(libs.androidx.navigation.compose)

    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)
    implementation(libs.hilt.navigation.compose)

    debugImplementation(libs.androidx.compose.ui.tooling)

    testImplementation(libs.junit)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.turbine)
    testImplementation(libs.mockk)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.androidx.test.espresso.core)
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
}
