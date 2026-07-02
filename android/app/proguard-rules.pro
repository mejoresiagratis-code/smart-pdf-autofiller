# Reglas específicas del proyecto. Las reglas por defecto de AGP ya cubren
# Compose/Kotlin — aquí solo lo que necesitan las librerías de Fase 1+.

# PdfBox-Android incluye recursos de fuentes/cmaps que R8 no debe tocar.
-keep class com.tom_roush.pdfbox.** { *; }
-keep class com.tom_roush.fontbox.** { *; }
-dontwarn com.tom_roush.**

# kotlinx.serialization: mantiene los @Serializable de los DTO del proxy.
-keepattributes *Annotation*, InnerClasses
-keep,includedescriptorclasses class com.mejoresiagratis.rellenadorpdv.data.remote.dto.**$$serializer { *; }
-keepclassmembers class com.mejoresiagratis.rellenadorpdv.data.remote.dto.** {
    *** Companion;
}
-keepclasseswithmembers class com.mejoresiagratis.rellenadorpdv.data.remote.dto.** {
    kotlinx.serialization.KSerializer serializer(...);
}
