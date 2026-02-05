package com.example.androidcomposebase.designsystem.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

/**
 * AppTheme - Design System Theme
 * 
 * Central theme accessor for all design tokens.
 * Provides direct access to colors, typography, dimensions, and shapes.
 * 
 * Usage:
 * ```
 * @Composable
 * fun MyScreen() {
 *     Text(
 *         text = "Hello",
 *         style = AppTheme.typography.headlineMedium,
 *         color = AppTheme.colors.textPrimary
 *     )
 *     
 *     Box(
 *         modifier = Modifier
 *             .background(AppTheme.colors.surface)
 *             .padding(AppTheme.dimensions.spacingLg)
 *     )
 * }
 * ```
 */
object AppTheme {
    
    /**
     * Access to the current color scheme.
     * Automatically selects light/dark based on system settings.
     */
    val colors: AppColorScheme
        @Composable
        @ReadOnlyComposable
        get() = LocalAppColors.current
    
    /**
     * Access to typography styles.
     */
    val typography: AppTypographyScheme
        @Composable
        @ReadOnlyComposable
        get() = LocalAppTypography.current
    
    /**
     * Access to dimension values (spacing, sizes, etc.).
     */
    val dimensions: AppDimensionsScheme
        @Composable
        @ReadOnlyComposable
        get() = LocalAppDimensions.current
    
    /**
     * Access to shape definitions.
     */
    val shapes: AppShapesScheme
        @Composable
        @ReadOnlyComposable
        get() = LocalAppShapes.current
}

// ============================================================
// MATERIAL 3 COLOR SCHEME MAPPING
// ============================================================

private fun AppColorScheme.toMaterialLightScheme() = lightColorScheme(
    primary = primary,
    onPrimary = onPrimary,
    primaryContainer = primaryContainer,
    onPrimaryContainer = textPrimary,
    secondary = secondary,
    onSecondary = onSecondary,
    secondaryContainer = secondaryContainer,
    onSecondaryContainer = textPrimary,
    error = error,
    onError = onError,
    errorContainer = errorContainer,
    onErrorContainer = textPrimary,
    background = background,
    onBackground = onBackground,
    surface = surface,
    onSurface = onSurface,
    surfaceVariant = surfaceVariant,
    onSurfaceVariant = onSurfaceVariant,
    outline = border,
    outlineVariant = divider
)

private fun AppColorScheme.toMaterialDarkScheme() = darkColorScheme(
    primary = primary,
    onPrimary = onPrimary,
    primaryContainer = primaryContainer,
    onPrimaryContainer = textPrimary,
    secondary = secondary,
    onSecondary = onSecondary,
    secondaryContainer = secondaryContainer,
    onSecondaryContainer = textPrimary,
    error = error,
    onError = onError,
    errorContainer = errorContainer,
    onErrorContainer = textPrimary,
    background = background,
    onBackground = onBackground,
    surface = surface,
    onSurface = onSurface,
    surfaceVariant = surfaceVariant,
    onSurfaceVariant = onSurfaceVariant,
    outline = border,
    outlineVariant = divider
)

// ============================================================
// MATERIAL 3 TYPOGRAPHY MAPPING
// ============================================================

private fun AppTypographyScheme.toMaterialTypography() = androidx.compose.material3.Typography(
    displayLarge = displayLarge,
    displayMedium = displayMedium,
    displaySmall = displaySmall,
    headlineLarge = headlineLarge,
    headlineMedium = headlineMedium,
    headlineSmall = headlineSmall,
    titleLarge = titleLarge,
    titleMedium = titleMedium,
    titleSmall = titleSmall,
    bodyLarge = bodyLarge,
    bodyMedium = bodyMedium,
    bodySmall = bodySmall,
    labelLarge = labelLarge,
    labelMedium = labelMedium,
    labelSmall = labelSmall
)

// ============================================================
// APP THEME COMPOSABLE
// ============================================================

/**
 * AppTheme composable wrapper.
 * 
 * Wraps the app content with the design system theme.
 * Provides both custom design tokens and Material 3 theming.
 * 
 * @param darkTheme Whether to use dark theme. Defaults to system setting.
 * @param content The content to be themed.
 */
@Composable
fun AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
    val materialColorScheme = if (darkTheme) {
        colorScheme.toMaterialDarkScheme()
    } else {
        colorScheme.toMaterialLightScheme()
    }
    
    // Update system bars
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.background.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }
    
    CompositionLocalProvider(
        LocalAppColors provides colorScheme,
        LocalAppTypography provides AppTypography,
        LocalAppDimensions provides AppDimensions,
        LocalAppShapes provides AppShapes
    ) {
        MaterialTheme(
            colorScheme = materialColorScheme,
            typography = AppTypography.toMaterialTypography(),
            content = content
        )
    }
}
