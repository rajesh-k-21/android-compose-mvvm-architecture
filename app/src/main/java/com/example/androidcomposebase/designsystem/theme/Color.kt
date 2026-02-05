package com.example.androidcomposebase.designsystem.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

/**
 * App Color Palette
 * 
 * A comprehensive semantic color system for the Design System.
 * Colors are organized by purpose, not by hue.
 * 
 * Usage:
 * ```
 * Text(color = AppTheme.colors.textPrimary)
 * Box(modifier = Modifier.background(AppTheme.colors.success))
 * ```
 */

// ============================================================
// BRAND COLORS
// ============================================================

object BrandColors {
    val Primary = Color(0xFF6366F1)        // Indigo 500
    val PrimaryLight = Color(0xFF818CF8)   // Indigo 400
    val PrimaryDark = Color(0xFF4F46E5)    // Indigo 600
    val PrimaryContainer = Color(0xFFE0E7FF) // Indigo 100
    
    val Secondary = Color(0xFF06B6D4)      // Cyan 500
    val SecondaryLight = Color(0xFF22D3EE) // Cyan 400
    val SecondaryDark = Color(0xFF0891B2)  // Cyan 600
    val SecondaryContainer = Color(0xFFCFFAFE) // Cyan 100
}

// ============================================================
// SEMANTIC COLORS
// ============================================================

object SemanticColors {
    // Success
    val Success = Color(0xFF22C55E)        // Green 500
    val SuccessLight = Color(0xFF4ADE80)   // Green 400
    val SuccessDark = Color(0xFF16A34A)    // Green 600
    val SuccessContainer = Color(0xFFDCFCE7) // Green 100
    
    // Warning
    val Warning = Color(0xFFF59E0B)        // Amber 500
    val WarningLight = Color(0xFFFBBF24)   // Amber 400
    val WarningDark = Color(0xFFD97706)    // Amber 600
    val WarningContainer = Color(0xFFFEF3C7) // Amber 100
    
    // Error
    val Error = Color(0xFFEF4444)          // Red 500
    val ErrorLight = Color(0xFFF87171)     // Red 400
    val ErrorDark = Color(0xFFDC2626)      // Red 600
    val ErrorContainer = Color(0xFFFEE2E2) // Red 100
    
    // Info
    val Info = Color(0xFF3B82F6)           // Blue 500
    val InfoLight = Color(0xFF60A5FA)      // Blue 400
    val InfoDark = Color(0xFF2563EB)       // Blue 600
    val InfoContainer = Color(0xFFDBEAFE)  // Blue 100
}

// ============================================================
// APP COLOR SCHEME
// ============================================================

@Immutable
data class AppColorScheme(
    // Brand
    val primary: Color,
    val primaryVariant: Color,
    val primaryContainer: Color,
    val onPrimary: Color,
    val secondary: Color,
    val secondaryVariant: Color,
    val secondaryContainer: Color,
    val onSecondary: Color,
    
    // Semantic
    val success: Color,
    val successContainer: Color,
    val onSuccess: Color,
    val warning: Color,
    val warningContainer: Color,
    val onWarning: Color,
    val error: Color,
    val errorContainer: Color,
    val onError: Color,
    val info: Color,
    val infoContainer: Color,
    val onInfo: Color,
    
    // Text
    val textPrimary: Color,
    val textSecondary: Color,
    val textTertiary: Color,
    val textDisabled: Color,
    val textLink: Color,
    
    // Background & Surface
    val background: Color,
    val onBackground: Color,
    val surface: Color,
    val surfaceVariant: Color,
    val surfaceElevated: Color,
    val onSurface: Color,
    val onSurfaceVariant: Color,
    
    // Border
    val border: Color,
    val borderFocused: Color,
    val borderError: Color,
    
    // Misc
    val divider: Color,
    val overlay: Color,
    val scrim: Color,
    val shimmer: Color,
    
    // Component specific
    val ripple: Color,
    val disabled: Color,
    val disabledContent: Color
)

// ============================================================
// LIGHT THEME COLORS
// ============================================================

val LightColorScheme = AppColorScheme(
    // Brand
    primary = BrandColors.Primary,
    primaryVariant = BrandColors.PrimaryDark,
    primaryContainer = BrandColors.PrimaryContainer,
    onPrimary = Color.White,
    secondary = BrandColors.Secondary,
    secondaryVariant = BrandColors.SecondaryDark,
    secondaryContainer = BrandColors.SecondaryContainer,
    onSecondary = Color.White,
    
    // Semantic
    success = SemanticColors.Success,
    successContainer = SemanticColors.SuccessContainer,
    onSuccess = Color.White,
    warning = SemanticColors.Warning,
    warningContainer = SemanticColors.WarningContainer,
    onWarning = Color.White,
    error = SemanticColors.Error,
    errorContainer = SemanticColors.ErrorContainer,
    onError = Color.White,
    info = SemanticColors.Info,
    infoContainer = SemanticColors.InfoContainer,
    onInfo = Color.White,
    
    // Text
    textPrimary = Color(0xFF1F2937),       // Gray 800
    textSecondary = Color(0xFF6B7280),     // Gray 500
    textTertiary = Color(0xFF9CA3AF),      // Gray 400
    textDisabled = Color(0xFFD1D5DB),      // Gray 300
    textLink = SemanticColors.Info,
    
    // Background & Surface
    background = Color(0xFFF9FAFB),        // Gray 50
    onBackground = Color(0xFF1F2937),
    surface = Color.White,
    surfaceVariant = Color(0xFFF3F4F6),    // Gray 100
    surfaceElevated = Color(0xFFE5E7EB),   // Gray 200
    onSurface = Color(0xFF1F2937),
    onSurfaceVariant = Color(0xFF6B7280),
    
    // Border
    border = Color(0xFFE5E7EB),            // Gray 200
    borderFocused = BrandColors.Primary,
    borderError = SemanticColors.Error,
    
    // Misc
    divider = Color(0xFFE5E7EB),
    overlay = Color(0x80000000),
    scrim = Color(0x52000000),
    shimmer = Color(0xFFE5E7EB),
    
    // Component
    ripple = Color(0x1F000000),
    disabled = Color(0xFFE5E7EB),
    disabledContent = Color(0xFF9CA3AF)
)

// ============================================================
// DARK THEME COLORS
// ============================================================

val DarkColorScheme = AppColorScheme(
    // Brand
    primary = BrandColors.PrimaryLight,
    primaryVariant = BrandColors.Primary,
    primaryContainer = Color(0xFF3730A3),  // Indigo 800
    onPrimary = Color(0xFF1E1B4B),
    secondary = BrandColors.SecondaryLight,
    secondaryVariant = BrandColors.Secondary,
    secondaryContainer = Color(0xFF155E75), // Cyan 800
    onSecondary = Color(0xFF083344),
    
    // Semantic
    success = SemanticColors.SuccessLight,
    successContainer = Color(0xFF166534),  // Green 800
    onSuccess = Color(0xFF052E16),
    warning = SemanticColors.WarningLight,
    warningContainer = Color(0xFF92400E),  // Amber 800
    onWarning = Color(0xFF451A03),
    error = SemanticColors.ErrorLight,
    errorContainer = Color(0xFF991B1B),    // Red 800
    onError = Color(0xFF450A0A),
    info = SemanticColors.InfoLight,
    infoContainer = Color(0xFF1E40AF),     // Blue 800
    onInfo = Color(0xFF1E3A8A),
    
    // Text
    textPrimary = Color(0xFFF9FAFB),       // Gray 50
    textSecondary = Color(0xFFD1D5DB),     // Gray 300
    textTertiary = Color(0xFF9CA3AF),      // Gray 400
    textDisabled = Color(0xFF6B7280),      // Gray 500
    textLink = SemanticColors.InfoLight,
    
    // Background & Surface
    background = Color(0xFF111827),        // Gray 900
    onBackground = Color(0xFFF9FAFB),
    surface = Color(0xFF1F2937),           // Gray 800
    surfaceVariant = Color(0xFF374151),    // Gray 700
    surfaceElevated = Color(0xFF4B5563),   // Gray 600
    onSurface = Color(0xFFF9FAFB),
    onSurfaceVariant = Color(0xFFD1D5DB),
    
    // Border
    border = Color(0xFF374151),            // Gray 700
    borderFocused = BrandColors.PrimaryLight,
    borderError = SemanticColors.ErrorLight,
    
    // Misc
    divider = Color(0xFF374151),
    overlay = Color(0xB3000000),
    scrim = Color(0x99000000),
    shimmer = Color(0xFF374151),
    
    // Component
    ripple = Color(0x1FFFFFFF),
    disabled = Color(0xFF374151),
    disabledContent = Color(0xFF6B7280)
)

// ============================================================
// COMPOSITION LOCAL
// ============================================================

val LocalAppColors = staticCompositionLocalOf { LightColorScheme }
