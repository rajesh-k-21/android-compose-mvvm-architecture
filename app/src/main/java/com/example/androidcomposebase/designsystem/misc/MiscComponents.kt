package com.example.androidcomposebase.designsystem.misc

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Badge
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import com.example.androidcomposebase.designsystem.theme.AppTheme

/**
 * App Divider
 * 
 * A horizontal divider line.
 */
@Composable
fun AppDivider(
    modifier: Modifier = Modifier,
    thickness: Dp = AppTheme.dimensions.dividerThickness,
    color: Color = AppTheme.colors.divider
) {
    HorizontalDivider(
        modifier = modifier.fillMaxWidth(),
        thickness = thickness,
        color = color
    )
}

/**
 * App Vertical Divider
 * 
 * A vertical divider line.
 */
@Composable
fun AppVerticalDivider(
    modifier: Modifier = Modifier,
    thickness: Dp = AppTheme.dimensions.dividerThickness,
    color: Color = AppTheme.colors.divider
) {
    VerticalDivider(
        modifier = modifier.fillMaxHeight(),
        thickness = thickness,
        color = color
    )
}

/**
 * App Spacer - Vertical
 * 
 * A vertical spacer using theme dimensions.
 */
@Composable
fun VerticalSpacer(
    height: Dp = AppTheme.dimensions.spacingMd
) {
    Spacer(modifier = Modifier.height(height))
}

/**
 * App Spacer - Horizontal
 * 
 * A horizontal spacer using theme dimensions.
 */
@Composable
fun HorizontalSpacer(
    width: Dp = AppTheme.dimensions.spacingMd
) {
    Spacer(modifier = Modifier.width(width))
}

/**
 * App Badge
 * 
 * A badge/counter indicator.
 */
@Composable
fun AppBadge(
    count: Int,
    modifier: Modifier = Modifier,
    maxCount: Int = 99,
    showZero: Boolean = false,
    containerColor: Color = AppTheme.colors.error,
    contentColor: Color = AppTheme.colors.onError
) {
    if (count > 0 || showZero) {
        Badge(
            modifier = modifier,
            containerColor = containerColor,
            contentColor = contentColor
        ) {
            Text(
                text = if (count > maxCount) "$maxCount+" else count.toString(),
                style = AppTheme.typography.labelSmall
            )
        }
    }
}

/**
 * App Dot
 * 
 * A simple dot indicator.
 */
@Composable
fun AppDot(
    modifier: Modifier = Modifier,
    size: Dp = AppTheme.dimensions.spacingSm,
    color: Color = AppTheme.colors.error
) {
    Box(
        modifier = modifier
            .size(size)
            .background(color, CircleShape)
    )
}

/**
 * Status Indicator
 * 
 * A status indicator with color and optional label.
 */
@Composable
fun StatusIndicator(
    color: Color,
    modifier: Modifier = Modifier,
    label: String? = null
) {
    Box(
        modifier = modifier
            .background(
                color = color.copy(alpha = 0.1f),
                shape = AppTheme.shapes.extraSmall
            )
            .padding(
                horizontal = AppTheme.dimensions.spacingMd,
                vertical = AppTheme.dimensions.spacingXs
            ),
        contentAlignment = Alignment.Center
    ) {
        if (label != null) {
            Text(
                text = label,
                style = AppTheme.typography.labelMedium,
                color = color
            )
        }
    }
}

// ============================================================
// PREVIEWS
// ============================================================

@Preview(showBackground = true)
@Composable
private fun AppDividerPreview() {
    AppTheme {
        AppDivider(modifier = Modifier.padding(AppTheme.dimensions.spacingLg))
    }
}

@Preview(showBackground = true)
@Composable
private fun AppBadgePreview() {
    AppTheme {
        Box(modifier = Modifier.padding(AppTheme.dimensions.spacingLg)) {
            AppBadge(count = 5)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun AppDotPreview() {
    AppTheme {
        Box(modifier = Modifier.padding(AppTheme.dimensions.spacingLg)) {
            AppDot()
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun StatusIndicatorPreview() {
    AppTheme {
        Box(modifier = Modifier.padding(AppTheme.dimensions.spacingLg)) {
            StatusIndicator(
                color = AppTheme.colors.success,
                label = "Active"
            )
        }
    }
}
