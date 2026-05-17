package com.luizfit.plan3meses.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

private val LightColors = lightColorScheme(
    primary = Color(0xFF1D6B4F),
    onPrimary = Color.White,
    primaryContainer = Color(0xFFE7F3ED),
    onPrimaryContainer = Color(0xFF153B2E),
    secondary = Color(0xFF235F85),
    secondaryContainer = Color(0xFFE8F2F7),
    background = Color(0xFFFBFAF6),
    surface = Color.White,
    onSurface = Color(0xFF202124),
    outline = Color(0xFFDFE6E2)
)

private val AppShapes = Shapes(
    small = RoundedCornerShape(8.dp),
    medium = RoundedCornerShape(8.dp),
    large = RoundedCornerShape(10.dp)
)

@Composable
fun PlanoTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = LightColors,
        shapes = AppShapes,
        content = content
    )
}
