package com.example.androidcomposebase.designsystem.button

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androidcomposebase.designsystem.theme.AppTheme

/**
 * Secondary Button - Outlined Variant
 * 
 * A secondary action button with outlined style.
 * Use for less prominent actions or as alternative to primary buttons.
 * 
 * @param text Button label text
 * @param onClick Click handler
 * @param modifier Modifier to be applied
 * @param enabled Whether the button is enabled
 * @param loading Whether to show loading indicator
 * @param fullWidth Whether button should fill max width
 * @param leadingIcon Optional icon before text
 * @param trailingIcon Optional icon after text
 */
@Composable
fun SecondaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    loading: Boolean = false,
    fullWidth: Boolean = false,
    leadingIcon: ImageVector? = null,
    trailingIcon: ImageVector? = null
) {
    val buttonModifier = modifier
        .defaultMinSize(minHeight = AppTheme.dimensions.buttonHeightMedium)
        .then(if (fullWidth) Modifier.fillMaxWidth() else Modifier)
    
    OutlinedButton(
        onClick = { if (!loading) onClick() },
        modifier = buttonModifier,
        enabled = enabled && !loading,
        shape = AppTheme.shapes.button,
        border = BorderStroke(
            width = 1.dp,
            color = if (enabled) AppTheme.colors.primary else AppTheme.colors.disabled
        ),
        colors = ButtonDefaults.outlinedButtonColors(
            contentColor = AppTheme.colors.primary,
            disabledContentColor = AppTheme.colors.disabledContent
        ),
        contentPadding = PaddingValues(
            horizontal = AppTheme.dimensions.spacingLg,
            vertical = AppTheme.dimensions.spacingMd
        )
    ) {
        if (loading) {
            CircularProgressIndicator(
                modifier = Modifier.size(20.dp),
                color = AppTheme.colors.primary,
                strokeWidth = 2.dp
            )
        } else {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                leadingIcon?.let { icon ->
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        modifier = Modifier.size(AppTheme.dimensions.iconSizeMediumSmall)
                    )
                    Spacer(modifier = Modifier.width(AppTheme.dimensions.spacingSm))
                }
                
                Text(
                    text = text,
                    style = AppTheme.typography.labelLarge
                )
                
                trailingIcon?.let { icon ->
                    Spacer(modifier = Modifier.width(AppTheme.dimensions.spacingSm))
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        modifier = Modifier.size(AppTheme.dimensions.iconSizeMediumSmall)
                    )
                }
            }
        }
    }
}

/**
 * Text Button
 * 
 * A minimal button with no background.
 * Use for tertiary actions or navigation.
 * 
 * @param text Button label text
 * @param onClick Click handler
 * @param modifier Modifier to be applied
 * @param enabled Whether the button is enabled
 * @param leadingIcon Optional icon before text
 * @param trailingIcon Optional icon after text
 */
@Composable
fun AppTextButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    leadingIcon: ImageVector? = null,
    trailingIcon: ImageVector? = null
) {
    TextButton(
        onClick = onClick,
        modifier = modifier.defaultMinSize(minHeight = AppTheme.dimensions.buttonHeightMedium),
        enabled = enabled,
        shape = AppTheme.shapes.button,
        colors = ButtonDefaults.textButtonColors(
            contentColor = AppTheme.colors.primary,
            disabledContentColor = AppTheme.colors.disabledContent
        ),
        contentPadding = PaddingValues(
            horizontal = AppTheme.dimensions.spacingMd,
            vertical = AppTheme.dimensions.spacingSm
        )
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            leadingIcon?.let { icon ->
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    modifier = Modifier.size(AppTheme.dimensions.iconSizeMediumSmall)
                )
                Spacer(modifier = Modifier.width(AppTheme.dimensions.spacingXs))
            }
            
            Text(
                text = text,
                style = AppTheme.typography.labelLarge
            )
            
            trailingIcon?.let { icon ->
                Spacer(modifier = Modifier.width(AppTheme.dimensions.spacingXs))
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    modifier = Modifier.size(AppTheme.dimensions.iconSizeMediumSmall)
                )
            }
        }
    }
}

/**
 * Danger Button
 * 
 * A destructive action button with error/danger styling.
 * Use for delete, remove, or other destructive actions.
 */
@Composable
fun DangerButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    loading: Boolean = false,
    outlined: Boolean = false
) {
    if (outlined) {
        OutlinedButton(
            onClick = { if (!loading) onClick() },
            modifier = modifier.defaultMinSize(minHeight = AppTheme.dimensions.buttonHeightMedium),
            enabled = enabled && !loading,
            shape = AppTheme.shapes.button,
            border = BorderStroke(
                width = 1.dp,
                color = if (enabled) AppTheme.colors.error else AppTheme.colors.disabled
            ),
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = AppTheme.colors.error,
                disabledContentColor = AppTheme.colors.disabledContent
            ),
            contentPadding = PaddingValues(
                horizontal = AppTheme.dimensions.spacingLg,
                vertical = AppTheme.dimensions.spacingMd
            )
        ) {
            if (loading) {
                CircularProgressIndicator(
                    modifier = Modifier.size(20.dp),
                    color = AppTheme.colors.error,
                    strokeWidth = 2.dp
                )
            } else {
                Text(text = text, style = AppTheme.typography.labelLarge)
            }
        }
    } else {
        androidx.compose.material3.Button(
            onClick = { if (!loading) onClick() },
            modifier = modifier.defaultMinSize(minHeight = AppTheme.dimensions.buttonHeightMedium),
            enabled = enabled && !loading,
            shape = AppTheme.shapes.button,
            colors = ButtonDefaults.buttonColors(
                containerColor = AppTheme.colors.error,
                contentColor = AppTheme.colors.onError,
                disabledContainerColor = AppTheme.colors.disabled,
                disabledContentColor = AppTheme.colors.disabledContent
            ),
            contentPadding = PaddingValues(
                horizontal = AppTheme.dimensions.spacingLg,
                vertical = AppTheme.dimensions.spacingMd
            )
        ) {
            if (loading) {
                CircularProgressIndicator(
                    modifier = Modifier.size(20.dp),
                    color = AppTheme.colors.onError,
                    strokeWidth = 2.dp
                )
            } else {
                Text(text = text, style = AppTheme.typography.labelLarge)
            }
        }
    }
}

// ============================================================
// PREVIEWS
// ============================================================

@Preview(showBackground = true)
@Composable
private fun SecondaryButtonPreview() {
    AppTheme {
        SecondaryButton(
            text = "Secondary Button",
            onClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SecondaryButtonLoadingPreview() {
    AppTheme {
        SecondaryButton(
            text = "Loading",
            onClick = {},
            loading = true
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun TextButtonPreview() {
    AppTheme {
        AppTextButton(
            text = "Text Button",
            onClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun DangerButtonPreview() {
    AppTheme {
        DangerButton(
            text = "Delete",
            onClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun DangerButtonOutlinedPreview() {
    AppTheme {
        DangerButton(
            text = "Remove",
            onClick = {},
            outlined = true
        )
    }
}
