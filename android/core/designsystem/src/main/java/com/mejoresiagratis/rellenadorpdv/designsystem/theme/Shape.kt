package com.mejoresiagratis.rellenadorpdv.designsystem.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

/**
 * M3 Expressive se apoya en formas más grandes y redondeadas que el M3
 * "clásico" — mismos radios que ya se probaron en el mockup HTML
 * (.card 26px, .hero-card 30px, .field .box 18px, .fab 24px).
 */
val RellenadorShapes = Shapes(
    extraSmall = RoundedCornerShape(10.dp),
    small = RoundedCornerShape(14.dp),
    medium = RoundedCornerShape(18.dp),
    large = RoundedCornerShape(26.dp),
    extraLarge = RoundedCornerShape(32.dp),
)

/** Radios puntuales que no encajan en la escala de Shapes estándar de M3. */
object ExtraShapes {
    val Fab = RoundedCornerShape(24.dp)
    val Chip = RoundedCornerShape(100)
    val NavIndicator = RoundedCornerShape(16.dp)
    val BottomSheet = RoundedCornerShape(topStart = 28.dp, topEnd = 28.dp)
}
