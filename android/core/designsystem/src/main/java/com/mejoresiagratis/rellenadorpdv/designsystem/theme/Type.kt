package com.mejoresiagratis.rellenadorpdv.designsystem.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

/**
 * Pairing tipográfico: Space Grotesk (display, geométrica y con carácter,
 * igual que en el mockup HTML) + la fuente de sistema para cuerpo/labels,
 * donde la legibilidad en textos largos importa más que la personalidad.
 *
 * Los .ttf reales de Space Grotesk (Medium/SemiBold/Bold) van en
 * core/designsystem/src/main/res/font/ — no incluidos en este scaffold de
 * Fase 1 por peso del entregable; el proyecto compila igualmente con la
 * fuente de sistema como fallback hasta que se añadan los binarios.
 */
private val displayFontFamily = FontFamily.Default // TODO Fase 3: sustituir por Space Grotesk real (res/font/)
private val bodyFontFamily = FontFamily.Default

val RellenadorTypography = Typography(
    displayLarge = TextStyle(fontFamily = displayFontFamily, fontWeight = FontWeight.Bold, fontSize = 40.sp, lineHeight = 46.sp, letterSpacing = (-0.3).sp),
    displayMedium = TextStyle(fontFamily = displayFontFamily, fontWeight = FontWeight.Bold, fontSize = 32.sp, lineHeight = 38.sp, letterSpacing = (-0.2).sp),
    displaySmall = TextStyle(fontFamily = displayFontFamily, fontWeight = FontWeight.Bold, fontSize = 26.sp, lineHeight = 32.sp),

    headlineLarge = TextStyle(fontFamily = displayFontFamily, fontWeight = FontWeight.Bold, fontSize = 23.sp, lineHeight = 28.sp, letterSpacing = (-0.1).sp),
    headlineMedium = TextStyle(fontFamily = displayFontFamily, fontWeight = FontWeight.SemiBold, fontSize = 20.sp, lineHeight = 26.sp),
    headlineSmall = TextStyle(fontFamily = displayFontFamily, fontWeight = FontWeight.SemiBold, fontSize = 17.sp, lineHeight = 22.sp),

    titleLarge = TextStyle(fontFamily = displayFontFamily, fontWeight = FontWeight.SemiBold, fontSize = 16.sp, lineHeight = 22.sp),
    titleMedium = TextStyle(fontFamily = bodyFontFamily, fontWeight = FontWeight.Bold, fontSize = 14.sp, lineHeight = 20.sp),
    titleSmall = TextStyle(fontFamily = bodyFontFamily, fontWeight = FontWeight.Bold, fontSize = 12.5.sp, lineHeight = 18.sp),

    bodyLarge = TextStyle(fontFamily = bodyFontFamily, fontWeight = FontWeight.Normal, fontSize = 15.sp, lineHeight = 22.sp),
    bodyMedium = TextStyle(fontFamily = bodyFontFamily, fontWeight = FontWeight.Normal, fontSize = 13.5.sp, lineHeight = 20.sp),
    bodySmall = TextStyle(fontFamily = bodyFontFamily, fontWeight = FontWeight.Normal, fontSize = 12.sp, lineHeight = 17.sp),

    labelLarge = TextStyle(fontFamily = bodyFontFamily, fontWeight = FontWeight.Bold, fontSize = 13.sp, lineHeight = 18.sp),
    labelMedium = TextStyle(fontFamily = bodyFontFamily, fontWeight = FontWeight.Bold, fontSize = 11.5.sp, lineHeight = 16.sp, letterSpacing = 0.3.sp),
    labelSmall = TextStyle(fontFamily = bodyFontFamily, fontWeight = FontWeight.Bold, fontSize = 10.sp, lineHeight = 14.sp, letterSpacing = 0.4.sp),
)
