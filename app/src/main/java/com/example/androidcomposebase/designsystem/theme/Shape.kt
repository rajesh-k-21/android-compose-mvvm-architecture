package com.example.androidcomposebase.designsystem.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

/**
 * App Shapes
 * 
 * Consistent shape definitions for the Design System.
 * Based on Material 3 shape system.
 * 
 * Usage:
 * ```
 * Modifier.clip(AppTheme.shapes.medium)
 * Card(shape = AppTheme.shapes.large)
 * ```
 */

@Immutable
data class AppShapesScheme(
    /** No corner rounding */
    val none: Shape,
    
    /** 4.dp - Subtle rounding for small elements */
    val extraSmall: Shape,
    
    /** 8.dp - Default rounding for buttons, cards */
    val small: Shape,
    
    /** 12.dp - Medium rounding */
    val medium: Shape,
    
    /** 16.dp - Large rounding for dialogs, sheets */
    val large: Shape,
    
    /** 24.dp - Extra large rounding */
    val extraLarge: Shape,
    
    /** Full rounding for pills and circles */
    val full: Shape,
    
    // ============================================================
    // COMPONENT-SPECIFIC SHAPES
    // ============================================================
    
    /** Shape for buttons */
    val button: Shape,
    
    /** Shape for text fields */
    val textField: Shape,
    
    /** Shape for cards */
    val card: Shape,
    
    /** Shape for dialogs */
    val dialog: Shape,
    
    /** Shape for bottom sheets */
    val bottomSheet: Shape,
    
    /** Shape for chips */
    val chip: Shape,
    
    /** Shape for FAB */
    val fab: Shape,
    
    /** Shape for snackbars */
    val snackbar: Shape,
    
    /** Shape for tooltips */
    val tooltip: Shape
)

// ============================================================
// DEFAULT SHAPES
// ============================================================

val AppShapes = AppShapesScheme(
    // Base shapes
    none = RoundedCornerShape(0.dp),
    extraSmall = RoundedCornerShape(4.dp),
    small = RoundedCornerShape(8.dp),
    medium = RoundedCornerShape(12.dp),
    large = RoundedCornerShape(16.dp),
    extraLarge = RoundedCornerShape(24.dp),
    full = RoundedCornerShape(percent = 50),
    
    // Component shapes
    button = RoundedCornerShape(8.dp),
    textField = RoundedCornerShape(8.dp),
    card = RoundedCornerShape(12.dp),
    dialog = RoundedCornerShape(16.dp),
    bottomSheet = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
    chip = RoundedCornerShape(8.dp),
    fab = RoundedCornerShape(16.dp),
    snackbar = RoundedCornerShape(8.dp),
    tooltip = RoundedCornerShape(4.dp)
)

// ============================================================
// COMPOSITION LOCAL
// ============================================================

val LocalAppShapes = staticCompositionLocalOf { AppShapes }
