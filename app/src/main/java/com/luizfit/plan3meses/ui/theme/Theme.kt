package com.luizfit.plan3meses.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat

// Vibrant fitness-green palette with teal accents. Generated as a balanced
// Material 3 tonal palette so containers / on-* colors stay readable.
private val LightColors = lightColorScheme(
    primary = Color(0xFF006D43),
    onPrimary = Color.White,
    primaryContainer = Color(0xFF8FF8B7),
    onPrimaryContainer = Color(0xFF002111),
    secondary = Color(0xFF4F6353),
    onSecondary = Color.White,
    secondaryContainer = Color(0xFFD2E8D4),
    onSecondaryContainer = Color(0xFF0C1F13),
    tertiary = Color(0xFF3B6470),
    onTertiary = Color.White,
    tertiaryContainer = Color(0xFFBFE9F8),
    onTertiaryContainer = Color(0xFF001F27),
    error = Color(0xFFBA1A1A),
    onError = Color.White,
    errorContainer = Color(0xFFFFDAD6),
    onErrorContainer = Color(0xFF410002),
    background = Color(0xFFF6FBF4),
    onBackground = Color(0xFF181D18),
    surface = Color(0xFFF6FBF4),
    onSurface = Color(0xFF181D18),
    surfaceVariant = Color(0xFFDDE5DB),
    onSurfaceVariant = Color(0xFF414941),
    outline = Color(0xFF717971),
    outlineVariant = Color(0xFFC1C9BF),
    surfaceContainerLowest = Color(0xFFFFFFFF),
    surfaceContainerLow = Color(0xFFF0F5EE),
    surfaceContainer = Color(0xFFEAF0E7),
    surfaceContainerHigh = Color(0xFFE4EAE1),
    surfaceContainerHighest = Color(0xFFDFE4DC)
)

private val DarkColors = darkColorScheme(
    primary = Color(0xFF72DB9C),
    onPrimary = Color(0xFF003920),
    primaryContainer = Color(0xFF005232),
    onPrimaryContainer = Color(0xFF8FF8B7),
    secondary = Color(0xFFB6CCB9),
    onSecondary = Color(0xFF223527),
    secondaryContainer = Color(0xFF384B3C),
    onSecondaryContainer = Color(0xFFD2E8D4),
    tertiary = Color(0xFFA3CDDB),
    onTertiary = Color(0xFF023542),
    tertiaryContainer = Color(0xFF214C58),
    onTertiaryContainer = Color(0xFFBFE9F8),
    error = Color(0xFFFFB4AB),
    onError = Color(0xFF690005),
    errorContainer = Color(0xFF93000A),
    onErrorContainer = Color(0xFFFFDAD6),
    background = Color(0xFF101510),
    onBackground = Color(0xFFE0E4DD),
    surface = Color(0xFF101510),
    onSurface = Color(0xFFE0E4DD),
    surfaceVariant = Color(0xFF414941),
    onSurfaceVariant = Color(0xFFC1C9BF),
    outline = Color(0xFF8B938A),
    outlineVariant = Color(0xFF414941),
    surfaceContainerLowest = Color(0xFF0B100B),
    surfaceContainerLow = Color(0xFF181D18),
    surfaceContainer = Color(0xFF1D211C),
    surfaceContainerHigh = Color(0xFF272B26),
    surfaceContainerHighest = Color(0xFF323631)
)

private val PlanTypography = Typography(
    displayLarge = TextStyle(fontSize = 40.sp, fontWeight = FontWeight.ExtraBold, letterSpacing = (-0.5).sp, lineHeight = 48.sp),
    displayMedium = TextStyle(fontSize = 34.sp, fontWeight = FontWeight.ExtraBold, letterSpacing = (-0.4).sp, lineHeight = 42.sp),
    displaySmall = TextStyle(fontSize = 28.sp, fontWeight = FontWeight.Bold, letterSpacing = (-0.3).sp, lineHeight = 36.sp),
    headlineLarge = TextStyle(fontSize = 26.sp, fontWeight = FontWeight.Bold, letterSpacing = (-0.2).sp, lineHeight = 32.sp),
    headlineMedium = TextStyle(fontSize = 22.sp, fontWeight = FontWeight.Bold, lineHeight = 28.sp),
    headlineSmall = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold, lineHeight = 26.sp),
    titleLarge = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.SemiBold, lineHeight = 24.sp),
    titleMedium = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.SemiBold, lineHeight = 22.sp),
    titleSmall = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.SemiBold, lineHeight = 20.sp),
    bodyLarge = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Normal, lineHeight = 24.sp),
    bodyMedium = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Normal, lineHeight = 20.sp),
    bodySmall = TextStyle(fontSize = 13.sp, fontWeight = FontWeight.Normal, lineHeight = 18.sp),
    labelLarge = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.SemiBold, letterSpacing = 0.1.sp),
    labelMedium = TextStyle(fontSize = 12.sp, fontWeight = FontWeight.SemiBold, letterSpacing = 0.5.sp),
    labelSmall = TextStyle(fontSize = 11.sp, fontWeight = FontWeight.Bold, letterSpacing = 0.8.sp)
)

private val PlanShapes = Shapes(
    extraSmall = RoundedCornerShape(8.dp),
    small = RoundedCornerShape(12.dp),
    medium = RoundedCornerShape(18.dp),
    large = RoundedCornerShape(24.dp),
    extraLarge = RoundedCornerShape(32.dp)
)

@Composable
fun PlanoTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val context = LocalContext.current
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S ->
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        darkTheme -> DarkColors
        else -> LightColors
    }

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            val insets = WindowCompat.getInsetsController(window, view)
            insets.isAppearanceLightStatusBars = !darkTheme
            insets.isAppearanceLightNavigationBars = !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = PlanTypography,
        shapes = PlanShapes,
        content = content
    )
}
