package com.example.androidcomposebase.designsystem.chip

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.InputChip
import androidx.compose.material3.InputChipDefaults
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androidcomposebase.designsystem.theme.AppTheme

/**
 * App Chip
 * 
 * A basic chip for displaying information or triggering actions.
 * 
 * @param label Chip text
 * @param onClick Click handler
 * @param modifier Modifier to be applied
 * @param leadingIcon Optional leading icon
 * @param enabled Whether the chip is enabled
 */
@Composable
fun AppChip(
    label: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    leadingIcon: ImageVector? = null,
    enabled: Boolean = true
) {
    AssistChip(
        onClick = onClick,
        label = {
            Text(
                text = label,
                style = AppTheme.typography.labelMedium
            )
        },
        modifier = modifier,
        enabled = enabled,
        leadingIcon = leadingIcon?.let {
            {
                Icon(
                    imageVector = it,
                    contentDescription = null,
                    modifier = Modifier.size(18.dp)
                )
            }
        },
        shape = AppTheme.shapes.chip,
        colors = AssistChipDefaults.assistChipColors(
            containerColor = AppTheme.colors.surfaceVariant,
            labelColor = AppTheme.colors.textPrimary,
            leadingIconContentColor = AppTheme.colors.primary,
            disabledContainerColor = AppTheme.colors.disabled,
            disabledLabelColor = AppTheme.colors.disabledContent
        ),
        border = AssistChipDefaults.assistChipBorder(
            borderColor = AppTheme.colors.border,
            disabledBorderColor = AppTheme.colors.disabled,
            enabled = enabled
        )
    )
}

/**
 * App Filter Chip
 * 
 * A selectable chip for filtering content.
 * 
 * @param label Chip text
 * @param selected Whether the chip is selected
 * @param onSelectedChange Callback when selection changes
 * @param modifier Modifier to be applied
 * @param leadingIcon Optional leading icon (shown when not selected)
 * @param enabled Whether the chip is enabled
 */
@Composable
fun AppFilterChip(
    label: String,
    selected: Boolean,
    onSelectedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    leadingIcon: ImageVector? = null,
    enabled: Boolean = true
) {
    FilterChip(
        selected = selected,
        onClick = { onSelectedChange(!selected) },
        label = {
            Text(
                text = label,
                style = AppTheme.typography.labelMedium
            )
        },
        modifier = modifier,
        enabled = enabled,
        leadingIcon = if (selected) {
            {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = null,
                    modifier = Modifier.size(18.dp)
                )
            }
        } else leadingIcon?.let {
            {
                Icon(
                    imageVector = it,
                    contentDescription = null,
                    modifier = Modifier.size(18.dp)
                )
            }
        },
        shape = AppTheme.shapes.chip,
        colors = FilterChipDefaults.filterChipColors(
            containerColor = AppTheme.colors.surfaceVariant,
            labelColor = AppTheme.colors.textPrimary,
            iconColor = AppTheme.colors.textSecondary,
            selectedContainerColor = AppTheme.colors.primaryContainer,
            selectedLabelColor = AppTheme.colors.primary,
            selectedLeadingIconColor = AppTheme.colors.primary,
            disabledContainerColor = AppTheme.colors.disabled,
            disabledLabelColor = AppTheme.colors.disabledContent
        ),
        border = FilterChipDefaults.filterChipBorder(
            borderColor = AppTheme.colors.border,
            selectedBorderColor = AppTheme.colors.primary,
            disabledBorderColor = AppTheme.colors.disabled,
            enabled = enabled,
            selected = selected
        )
    )
}

/**
 * App Input Chip
 * 
 * A chip representing user input that can be dismissed.
 * Commonly used for tags or selected items.
 * 
 * @param label Chip text
 * @param onDismiss Callback when chip is dismissed
 * @param modifier Modifier to be applied
 * @param avatar Optional avatar composable
 * @param enabled Whether the chip is enabled
 */
@Composable
fun AppInputChip(
    label: String,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
    avatar: @Composable (() -> Unit)? = null,
    enabled: Boolean = true
) {
    InputChip(
        selected = false,
        onClick = {},
        label = {
            Text(
                text = label,
                style = AppTheme.typography.labelMedium
            )
        },
        modifier = modifier,
        enabled = enabled,
        avatar = avatar,
        trailingIcon = {
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = "Remove",
                modifier = Modifier
                    .size(18.dp)
                    .then(
                        if (enabled) Modifier.clickable { onDismiss() } else Modifier
                    )
            )
        },
        shape = AppTheme.shapes.chip,
        colors = InputChipDefaults.inputChipColors(
            containerColor = AppTheme.colors.surfaceVariant,
            labelColor = AppTheme.colors.textPrimary,
            trailingIconColor = AppTheme.colors.textSecondary,
            disabledContainerColor = AppTheme.colors.disabled,
            disabledLabelColor = AppTheme.colors.disabledContent,
            disabledTrailingIconColor = AppTheme.colors.disabledContent
        ),
        border = InputChipDefaults.inputChipBorder(
            borderColor = AppTheme.colors.border,
            disabledBorderColor = AppTheme.colors.disabled,
            enabled = enabled,
            selected = false
        )
    )
}

/**
 * App Suggestion Chip
 * 
 * A chip for suggesting options.
 */
@Composable
fun AppSuggestionChip(
    label: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    SuggestionChip(
        onClick = onClick,
        label = {
            Text(
                text = label,
                style = AppTheme.typography.labelMedium
            )
        },
        modifier = modifier,
        enabled = enabled,
        shape = AppTheme.shapes.chip,
        colors = AssistChipDefaults.assistChipColors(
            containerColor = AppTheme.colors.primaryContainer,
            labelColor = AppTheme.colors.primary
        )
    )
}

// ============================================================
// PREVIEWS
// ============================================================

@Preview(showBackground = true)
@Composable
private fun AppChipPreview() {
    AppTheme {
        AppChip(
            label = "Chip",
            onClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun AppFilterChipPreview() {
    AppTheme {
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            AppFilterChip(
                label = "Selected",
                selected = true,
                onSelectedChange = {}
            )
            AppFilterChip(
                label = "Unselected",
                selected = false,
                onSelectedChange = {}
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun AppInputChipPreview() {
    AppTheme {
        AppInputChip(
            label = "Tag",
            onDismiss = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun AppSuggestionChipPreview() {
    AppTheme {
        AppSuggestionChip(
            label = "Suggestion",
            onClick = {}
        )
    }
}
