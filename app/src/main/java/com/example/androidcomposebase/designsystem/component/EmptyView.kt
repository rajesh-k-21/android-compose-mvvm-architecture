package com.example.androidcomposebase.designsystem.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Inbox
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androidcomposebase.designsystem.button.SecondaryButton
import com.example.androidcomposebase.designsystem.theme.AppTheme

/**
 * Empty View
 * 
 * A reusable empty state component with:
 * - Icon
 * - Title
 * - Description
 * - Optional action button
 * 
 * @param title The title text
 * @param modifier Modifier to be applied
 * @param description Optional description
 * @param icon Empty state icon
 * @param iconTint Icon color
 * @param actionText Optional action button text
 * @param onAction Optional action callback
 */
@Composable
fun EmptyView(
    title: String,
    modifier: Modifier = Modifier,
    description: String? = null,
    icon: ImageVector = Icons.Default.Inbox,
    iconTint: Color = AppTheme.colors.textTertiary,
    actionText: String? = null,
    onAction: (() -> Unit)? = null
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(AppTheme.dimensions.spacingXl)
        ) {
            // Icon
            Icon(
                imageVector = icon,
                contentDescription = null,
                modifier = Modifier.size(72.dp),
                tint = iconTint
            )
            
            Spacer(modifier = Modifier.height(AppTheme.dimensions.spacingLg))
            
            // Title
            Text(
                text = title,
                style = AppTheme.typography.titleMedium,
                color = AppTheme.colors.textPrimary,
                textAlign = TextAlign.Center
            )
            
            // Description
            if (description != null) {
                Spacer(modifier = Modifier.height(AppTheme.dimensions.spacingSm))
                Text(
                    text = description,
                    style = AppTheme.typography.bodyMedium,
                    color = AppTheme.colors.textSecondary,
                    textAlign = TextAlign.Center
                )
            }
            
            // Action button
            if (actionText != null && onAction != null) {
                Spacer(modifier = Modifier.height(AppTheme.dimensions.spacingXl))
                SecondaryButton(
                    text = actionText,
                    onClick = onAction
                )
            }
        }
    }
}

// ============================================================
// PREVIEWS
// ============================================================

@Preview(showBackground = true)
@Composable
private fun EmptyViewPreview() {
    AppTheme {
        EmptyView(
            title = "No items found",
            description = "Start by adding your first item"
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun EmptyViewWithActionPreview() {
    AppTheme {
        EmptyView(
            title = "No results",
            description = "Try adjusting your search filters",
            actionText = "Clear Filters",
            onAction = {}
        )
    }
}
