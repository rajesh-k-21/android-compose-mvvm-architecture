package com.example.androidcomposebase.designsystem.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * App Dimensions
 * 
 * Consistent spacing, sizing, and dimension values for the Design System.
 * Use these instead of hardcoded dp values to maintain consistency.
 * 
 * Usage:
 * ```
 * Modifier.padding(AppTheme.dimensions.spacingMd)
 * Modifier.height(AppTheme.dimensions.buttonHeightMedium)
 * ```
 */

@Immutable
data class AppDimensionsScheme(
    // ============================================================
    // SPACING
    // ============================================================
    
    /** 2.dp - Minimal spacing */
    val spacingXxs: Dp,
    
    /** 4.dp - Extra small spacing */
    val spacingXs: Dp,
    
    /** 8.dp - Small spacing */
    val spacingSm: Dp,
    
    /** 12.dp - Medium spacing */
    val spacingMd: Dp,
    
    /** 16.dp - Large spacing */
    val spacingLg: Dp,
    
    /** 24.dp - Extra large spacing */
    val spacingXl: Dp,
    
    /** 32.dp - 2X large spacing */
    val spacingXxl: Dp,
    
    /** 48.dp - 3X large spacing */
    val spacing3xl: Dp,
    
    /** 64.dp - 4X large spacing */
    val spacing4xl: Dp,
    
    // ============================================================
    // ICON SIZES
    // ============================================================
    
    /** 16.dp - Small icon */
    val iconSizeSmall: Dp,
    
    /** 20.dp - Medium-small icon */
    val iconSizeMediumSmall: Dp,
    
    /** 24.dp - Medium icon (default) */
    val iconSizeMedium: Dp,
    
    /** 32.dp - Large icon */
    val iconSizeLarge: Dp,
    
    /** 48.dp - Extra large icon */
    val iconSizeXl: Dp,
    
    /** 64.dp - 2X large icon */
    val iconSizeXxl: Dp,
    
    // ============================================================
    // BUTTON HEIGHTS
    // ============================================================
    
    /** 32.dp - Small button */
    val buttonHeightSmall: Dp,
    
    /** 40.dp - Medium button (default) */
    val buttonHeightMedium: Dp,
    
    /** 48.dp - Large button */
    val buttonHeightLarge: Dp,
    
    /** 56.dp - Extra large button */
    val buttonHeightXl: Dp,
    
    // ============================================================
    // CORNER RADIUS
    // ============================================================
    
    /** 4.dp - Small radius */
    val radiusSmall: Dp,
    
    /** 8.dp - Medium radius */
    val radiusMedium: Dp,
    
    /** 12.dp - Large radius */
    val radiusLarge: Dp,
    
    /** 16.dp - Extra large radius */
    val radiusXl: Dp,
    
    /** 24.dp - 2X large radius */
    val radiusXxl: Dp,
    
    /** 999.dp - Full/pill radius */
    val radiusFull: Dp,
    
    // ============================================================
    // ELEVATION
    // ============================================================
    
    /** 0.dp - No elevation */
    val elevationNone: Dp,
    
    /** 1.dp - Extra small elevation */
    val elevationXs: Dp,
    
    /** 2.dp - Small elevation */
    val elevationSm: Dp,
    
    /** 4.dp - Medium elevation */
    val elevationMd: Dp,
    
    /** 8.dp - Large elevation */
    val elevationLg: Dp,
    
    /** 16.dp - Extra large elevation */
    val elevationXl: Dp,
    
    // ============================================================
    // COMPONENT SIZES
    // ============================================================
    
    /** 40.dp - Default avatar size */
    val avatarSizeSmall: Dp,
    
    /** 48.dp - Medium avatar size */
    val avatarSizeMedium: Dp,
    
    /** 64.dp - Large avatar size */
    val avatarSizeLarge: Dp,
    
    /** 96.dp - Extra large avatar size */
    val avatarSizeXl: Dp,
    
    /** 1.dp - Divider thickness */
    val dividerThickness: Dp,
    
    /** 56.dp - Bottom navigation height */
    val bottomNavHeight: Dp,
    
    /** 64.dp - Top app bar height */
    val topBarHeight: Dp,
    
    /** 80.dp - Extended top bar height */
    val topBarHeightExtended: Dp,
    
    /** 48.dp - Tab height */
    val tabHeight: Dp,
    
    /** 56.dp - FAB size */
    val fabSize: Dp,
    
    /** 40.dp - Small FAB size */
    val fabSizeSmall: Dp
)

// ============================================================
// DEFAULT VALUES
// ============================================================

val AppDimensions = AppDimensionsScheme(
    // Spacing
    spacingXxs = 2.dp,
    spacingXs = 4.dp,
    spacingSm = 8.dp,
    spacingMd = 12.dp,
    spacingLg = 16.dp,
    spacingXl = 24.dp,
    spacingXxl = 32.dp,
    spacing3xl = 48.dp,
    spacing4xl = 64.dp,
    
    // Icon Sizes
    iconSizeSmall = 16.dp,
    iconSizeMediumSmall = 20.dp,
    iconSizeMedium = 24.dp,
    iconSizeLarge = 32.dp,
    iconSizeXl = 48.dp,
    iconSizeXxl = 64.dp,
    
    // Button Heights
    buttonHeightSmall = 32.dp,
    buttonHeightMedium = 40.dp,
    buttonHeightLarge = 48.dp,
    buttonHeightXl = 56.dp,
    
    // Corner Radius
    radiusSmall = 4.dp,
    radiusMedium = 8.dp,
    radiusLarge = 12.dp,
    radiusXl = 16.dp,
    radiusXxl = 24.dp,
    radiusFull = 999.dp,
    
    // Elevation
    elevationNone = 0.dp,
    elevationXs = 1.dp,
    elevationSm = 2.dp,
    elevationMd = 4.dp,
    elevationLg = 8.dp,
    elevationXl = 16.dp,
    
    // Component Sizes
    avatarSizeSmall = 40.dp,
    avatarSizeMedium = 48.dp,
    avatarSizeLarge = 64.dp,
    avatarSizeXl = 96.dp,
    dividerThickness = 1.dp,
    bottomNavHeight = 56.dp,
    topBarHeight = 64.dp,
    topBarHeightExtended = 80.dp,
    tabHeight = 48.dp,
    fabSize = 56.dp,
    fabSizeSmall = 40.dp
)

// ============================================================
// COMPOSITION LOCAL
// ============================================================

val LocalAppDimensions = staticCompositionLocalOf { AppDimensions }
