package com.example.androidcomposebase.core.extension

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp

/**
 * Clickable without ripple effect.
 * Useful for custom clickable areas.
 */
fun Modifier.clickableNoRipple(
    enabled: Boolean = true,
    onClick: () -> Unit
): Modifier = composed {
    clickable(
        enabled = enabled,
        indication = null,
        interactionSource = remember { MutableInteractionSource() },
        onClick = onClick
    )
}

/**
 * Conditional modifier application.
 * 
 * Example:
 * ```
 * Modifier
 *     .conditional(isSelected) {
 *         background(Color.Blue)
 *     }
 * ```
 */
inline fun Modifier.conditional(
    condition: Boolean,
    modifier: Modifier.() -> Modifier
): Modifier = if (condition) then(modifier()) else this

/**
 * Applies modifier only if value is not null.
 * 
 * Example:
 * ```
 * Modifier.ifNotNull(backgroundColor) {
 *     background(it)
 * }
 * ```
 */
inline fun <T> Modifier.ifNotNull(
    value: T?,
    modifier: Modifier.(T) -> Modifier
): Modifier = if (value != null) then(modifier(value)) else this

/**
 * Draws a bottom border line.
 * 
 * Useful for list item dividers.
 */
fun Modifier.bottomBorder(
    strokeWidth: Dp,
    color: Color
): Modifier = composed {
    val density = LocalDensity.current
    val strokeWidthPx = with(density) { strokeWidth.toPx() }
    
    drawBehind {
        val y = size.height - strokeWidthPx / 2
        drawLine(
            color = color,
            start = Offset(0f, y),
            end = Offset(size.width, y),
            strokeWidth = strokeWidthPx
        )
    }
}
