package com.example.androidcomposebase.designsystem.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import com.example.androidcomposebase.designsystem.theme.AppTheme

/**
 * App List Item
 * 
 * A Material 3 list item with support for:
 * - Headline and supporting text
 * - Leading and trailing content
 * - Click handling
 * - Divider option
 * 
 * @param headlineText Primary text
 * @param modifier Modifier to be applied
 * @param supportingText Secondary text
 * @param overlineText Text above headline
 * @param leadingContent Leading composable (icon, avatar)
 * @param trailingContent Trailing composable (icon, text)
 * @param onClick Optional click handler
 * @param showDivider Whether to show divider below
 */
@Composable
fun AppListItem(
    headlineText: String,
    modifier: Modifier = Modifier,
    supportingText: String? = null,
    overlineText: String? = null,
    leadingContent: @Composable (() -> Unit)? = null,
    trailingContent: @Composable (() -> Unit)? = null,
    onClick: (() -> Unit)? = null,
    showDivider: Boolean = false,
    enabled: Boolean = true,
    containerColor: Color = Color.Transparent
) {
    Column(modifier = modifier) {
        ListItem(
            headlineContent = {
                Text(
                    text = headlineText,
                    style = AppTheme.typography.bodyLarge,
                    color = if (enabled) AppTheme.colors.textPrimary else AppTheme.colors.textDisabled
                )
            },
            modifier = if (onClick != null && enabled) {
                Modifier.clickable(onClick = onClick)
            } else Modifier,
            supportingContent = supportingText?.let {
                {
                    Text(
                        text = it,
                        style = AppTheme.typography.bodyMedium,
                        color = AppTheme.colors.textSecondary
                    )
                }
            },
            overlineContent = overlineText?.let {
                {
                    Text(
                        text = it,
                        style = AppTheme.typography.labelSmall,
                        color = AppTheme.colors.textTertiary
                    )
                }
            },
            leadingContent = leadingContent,
            trailingContent = trailingContent,
            colors = ListItemDefaults.colors(
                containerColor = containerColor
            )
        )
        
        if (showDivider) {
            HorizontalDivider(
                color = AppTheme.colors.divider,
                thickness = AppTheme.dimensions.dividerThickness
            )
        }
    }
}

/**
 * App List Item with Icon
 * 
 * A convenience wrapper with leading icon.
 */
@Composable
fun AppListItemWithIcon(
    headlineText: String,
    icon: ImageVector,
    modifier: Modifier = Modifier,
    supportingText: String? = null,
    onClick: (() -> Unit)? = null,
    showChevron: Boolean = true,
    showDivider: Boolean = false
) {
    AppListItem(
        headlineText = headlineText,
        modifier = modifier,
        supportingText = supportingText,
        leadingContent = {
            Icon(
                imageVector = icon,
                contentDescription = null,
                modifier = Modifier.size(AppTheme.dimensions.iconSizeMedium),
                tint = AppTheme.colors.primary
            )
        },
        trailingContent = if (showChevron && onClick != null) {
            {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                    contentDescription = null,
                    modifier = Modifier.size(AppTheme.dimensions.iconSizeMedium),
                    tint = AppTheme.colors.textTertiary
                )
            }
        } else null,
        onClick = onClick,
        showDivider = showDivider
    )
}

// ============================================================
// PREVIEWS
// ============================================================

@Preview(showBackground = true)
@Composable
private fun AppListItemPreview() {
    AppTheme {
        Column {
            AppListItem(
                headlineText = "List Item",
                supportingText = "Supporting text",
                showDivider = true
            )
            AppListItem(
                headlineText = "Clickable Item",
                onClick = {}
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun AppListItemWithIconPreview() {
    AppTheme {
        Column {
            AppListItemWithIcon(
                headlineText = "Profile",
                icon = Icons.Default.Person,
                supportingText = "View your profile",
                onClick = {},
                showDivider = true
            )
            AppListItemWithIcon(
                headlineText = "Settings",
                icon = Icons.Default.Person,
                onClick = {}
            )
        }
    }
}
