package com.example.androidcomposebase.designsystem.card

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import com.example.androidcomposebase.designsystem.theme.AppTheme

/**
 * App Card
 * 
 * A standard card component with:
 * - Click support
 * - Customizable elevation
 * - Border option
 * - Theme-aware styling
 * 
 * @param modifier Modifier to be applied
 * @param onClick Optional click handler
 * @param enabled Whether the card is enabled
 * @param shape Card shape
 * @param containerColor Background color
 * @param contentColor Content color
 * @param elevation Card elevation
 * @param border Optional border
 * @param content Card content
 */
@Composable
fun AppCard(
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    enabled: Boolean = true,
    shape: Shape = AppTheme.shapes.card,
    containerColor: Color = AppTheme.colors.surface,
    contentColor: Color = AppTheme.colors.onSurface,
    elevation: Dp = AppTheme.dimensions.elevationXs,
    border: BorderStroke? = null,
    content: @Composable ColumnScope.() -> Unit
) {
    if (onClick != null) {
        Card(
            onClick = onClick,
            modifier = modifier,
            enabled = enabled,
            shape = shape,
            colors = CardDefaults.cardColors(
                containerColor = containerColor,
                contentColor = contentColor,
                disabledContainerColor = AppTheme.colors.disabled,
                disabledContentColor = AppTheme.colors.disabledContent
            ),
            elevation = CardDefaults.cardElevation(defaultElevation = elevation),
            border = border,
            content = content
        )
    } else {
        Card(
            modifier = modifier,
            shape = shape,
            colors = CardDefaults.cardColors(
                containerColor = containerColor,
                contentColor = contentColor
            ),
            elevation = CardDefaults.cardElevation(defaultElevation = elevation),
            border = border,
            content = content
        )
    }
}

/**
 * App Elevated Card
 * 
 * A card with more prominent elevation.
 * Use for featured or highlighted content.
 */
@Composable
fun AppElevatedCard(
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    enabled: Boolean = true,
    shape: Shape = AppTheme.shapes.card,
    containerColor: Color = AppTheme.colors.surface,
    contentColor: Color = AppTheme.colors.onSurface,
    content: @Composable ColumnScope.() -> Unit
) {
    if (onClick != null) {
        ElevatedCard(
            onClick = onClick,
            modifier = modifier,
            enabled = enabled,
            shape = shape,
            colors = CardDefaults.elevatedCardColors(
                containerColor = containerColor,
                contentColor = contentColor,
                disabledContainerColor = AppTheme.colors.disabled,
                disabledContentColor = AppTheme.colors.disabledContent
            ),
            elevation = CardDefaults.elevatedCardElevation(
                defaultElevation = AppTheme.dimensions.elevationMd
            ),
            content = content
        )
    } else {
        ElevatedCard(
            modifier = modifier,
            shape = shape,
            colors = CardDefaults.elevatedCardColors(
                containerColor = containerColor,
                contentColor = contentColor
            ),
            elevation = CardDefaults.elevatedCardElevation(
                defaultElevation = AppTheme.dimensions.elevationMd
            ),
            content = content
        )
    }
}

/**
 * App Outlined Card
 * 
 * A card with a border instead of elevation.
 * Use for content that doesn't need visual prominence.
 */
@Composable
fun AppOutlinedCard(
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    enabled: Boolean = true,
    shape: Shape = AppTheme.shapes.card,
    containerColor: Color = AppTheme.colors.surface,
    contentColor: Color = AppTheme.colors.onSurface,
    borderColor: Color = AppTheme.colors.border,
    content: @Composable ColumnScope.() -> Unit
) {
    if (onClick != null) {
        OutlinedCard(
            onClick = onClick,
            modifier = modifier,
            enabled = enabled,
            shape = shape,
            colors = CardDefaults.outlinedCardColors(
                containerColor = containerColor,
                contentColor = contentColor,
                disabledContainerColor = AppTheme.colors.disabled,
                disabledContentColor = AppTheme.colors.disabledContent
            ),
            border = BorderStroke(
                width = AppTheme.dimensions.dividerThickness,
                color = borderColor
            ),
            content = content
        )
    } else {
        OutlinedCard(
            modifier = modifier,
            shape = shape,
            colors = CardDefaults.outlinedCardColors(
                containerColor = containerColor,
                contentColor = contentColor
            ),
            border = BorderStroke(
                width = AppTheme.dimensions.dividerThickness,
                color = borderColor
            ),
            content = content
        )
    }
}

// ============================================================
// PREVIEWS
// ============================================================

@Preview(showBackground = true)
@Composable
private fun AppCardPreview() {
    AppTheme {
        AppCard(
            modifier = Modifier
                .fillMaxWidth()
                .padding(AppTheme.dimensions.spacingMd)
        ) {
            Column(modifier = Modifier.padding(AppTheme.dimensions.spacingLg)) {
                Text(
                    text = "Card Title",
                    style = AppTheme.typography.titleMedium
                )
                Text(
                    text = "This is card content",
                    style = AppTheme.typography.bodyMedium,
                    color = AppTheme.colors.textSecondary
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun AppElevatedCardPreview() {
    AppTheme {
        AppElevatedCard(
            modifier = Modifier
                .fillMaxWidth()
                .padding(AppTheme.dimensions.spacingMd),
            onClick = {}
        ) {
            Column(modifier = Modifier.padding(AppTheme.dimensions.spacingLg)) {
                Text(
                    text = "Elevated Card",
                    style = AppTheme.typography.titleMedium
                )
                Text(
                    text = "Click me!",
                    style = AppTheme.typography.bodyMedium,
                    color = AppTheme.colors.textSecondary
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun AppOutlinedCardPreview() {
    AppTheme {
        AppOutlinedCard(
            modifier = Modifier
                .fillMaxWidth()
                .padding(AppTheme.dimensions.spacingMd)
        ) {
            Column(modifier = Modifier.padding(AppTheme.dimensions.spacingLg)) {
                Text(
                    text = "Outlined Card",
                    style = AppTheme.typography.titleMedium
                )
                Text(
                    text = "With border styling",
                    style = AppTheme.typography.bodyMedium,
                    color = AppTheme.colors.textSecondary
                )
            }
        }
    }
}
