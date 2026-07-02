package com.mejoresiagratis.rellenadorpdv.designsystem.theme

import androidx.compose.ui.graphics.Color

/**
 * Paleta MásOrange · Material 3 Expressive.
 *
 * Mismos valores hex que ya se validaron visualmente en el mockup HTML
 * (mockup-visual-app-android.html) — no se re-inventa la paleta aquí,
 * solo se traduce a tokens Compose para que Theme.kt monte el ColorScheme.
 *
 * Nota heredada del proyecto web (ver ESTADO_Y_GUIA_DE_CONTINUIDAD.md §13):
 * el naranja `#FF7900` es el histórico de Orange/MásOrange, confirmado y
 * estable. El resto de la paleta (negro/dorado) es una extensión razonada
 * en clave M3 — no un brandbook oficial de MásOrange. Si Pablo consigue
 * el brandbook real, este es el único archivo que hay que tocar.
 */

// ── Light ──
val PrimaryLight = Color(0xFFFF7900)
val OnPrimaryLight = Color(0xFF2E1500)
val PrimaryContainerLight = Color(0xFFFFDBB8)
val OnPrimaryContainerLight = Color(0xFF301A00)

val SecondaryLight = Color(0xFF7A5900)
val OnSecondaryLight = Color(0xFFFFFFFF)
val SecondaryContainerLight = Color(0xFFFFE293)
val OnSecondaryContainerLight = Color(0xFF261A00)

val TertiaryLight = Color(0xFF3E4759)
val OnTertiaryLight = Color(0xFFFFFFFF)
val TertiaryContainerLight = Color(0xFFD7E3F8)
val OnTertiaryContainerLight = Color(0xFF04102A)

val BackgroundLight = Color(0xFFFFF8F4)
val OnBackgroundLight = Color(0xFF221A12)
val SurfaceLight = Color(0xFFFFF8F4)
val OnSurfaceLight = Color(0xFF221A12)
val SurfaceVariantLight = Color(0xFFF1E0D0)
val OnSurfaceVariantLight = Color(0xFF53443A)

val SurfaceContainerLowestLight = Color(0xFFFFFFFF)
val SurfaceContainerLowLight = Color(0xFFFFF1E8)
val SurfaceContainerLight = Color(0xFFFCE3CF)
val SurfaceContainerHighLight = Color(0xFFF8D9BE)
val SurfaceContainerHighestLight = Color(0xFFF1CFAF)

val OutlineLight = Color(0xFF867568)
val OutlineVariantLight = Color(0xFFD9C4B4)

val ErrorLight = Color(0xFFBA1A1A)
val OnErrorLight = Color(0xFFFFFFFF)
val ErrorContainerLight = Color(0xFFFFDAD6)
val OnErrorContainerLight = Color(0xFF410002)

/** Rol "éxito" — M3 no lo trae de serie; se añade como extensión (ver Theme.kt → LocalExtraColors). */
val SuccessLight = Color(0xFF1B7A4B)
val OnSuccessLight = Color(0xFFFFFFFF)
val SuccessContainerLight = Color(0xFFC6F2D9)
val OnSuccessContainerLight = Color(0xFF00210F)

// ── Dark ──
val PrimaryDark = Color(0xFFFFB877)
val OnPrimaryDark = Color(0xFF4A2700)
val PrimaryContainerDark = Color(0xFF6B3900)
val OnPrimaryContainerDark = Color(0xFFFFDBB8)

val SecondaryDark = Color(0xFFE9C46A)
val OnSecondaryDark = Color(0xFF3F2D00)
val SecondaryContainerDark = Color(0xFF5C4300)
val OnSecondaryContainerDark = Color(0xFFFFE293)

val TertiaryDark = Color(0xFFB7C8E8)
val OnTertiaryDark = Color(0xFF17324A)
val TertiaryContainerDark = Color(0xFF263041)
val OnTertiaryContainerDark = Color(0xFFD7E3F8)

val BackgroundDark = Color(0xFF18120C)
val OnBackgroundDark = Color(0xFFEEE0D4)
val SurfaceDark = Color(0xFF18120C)
val OnSurfaceDark = Color(0xFFEEE0D4)
val SurfaceVariantDark = Color(0xFF524337)
val OnSurfaceVariantDark = Color(0xFFD6C3B2)

val SurfaceContainerLowestDark = Color(0xFF120D08)
val SurfaceContainerLowDark = Color(0xFF231A11)
val SurfaceContainerDark = Color(0xFF2A2015)
val SurfaceContainerHighDark = Color(0xFF352718)
val SurfaceContainerHighestDark = Color(0xFF402E1B)

val OutlineDark = Color(0xFF9F8D7D)
val OutlineVariantDark = Color(0xFF524337)

val ErrorDark = Color(0xFFFFB4AB)
val OnErrorDark = Color(0xFF690005)
val ErrorContainerDark = Color(0xFF93000A)
val OnErrorContainerDark = Color(0xFFFFDAD6)

val SuccessDark = Color(0xFF83DDA6)
val OnSuccessDark = Color(0xFF00391D)
val SuccessContainerDark = Color(0xFF0C3B23)
val OnSuccessContainerDark = Color(0xFF9FF8BF)
