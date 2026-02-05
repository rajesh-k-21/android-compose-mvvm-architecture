package com.example.androidcomposebase.designsystem.button

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androidcomposebase.designsystem.theme.AppTheme

/**
 * Primary Button
 * 
 * The main call-to-action button with filled style.
 * Supports loading state, icons, and full width option.
 * 
 * @param text Button label text
 * @param onClick Click handler
 * @param modifier Modifier to be applied
 * @param enabled Whether the button is enabled
 * @param loading Whether to show loading indicator
 * @param fullWidth Whether button should fill max width
 * @param leadingIcon Optional icon before text
 * @param trailingIcon Optional icon after text
 * @param contentDescription Accessibility description
 */
@Composable
fun PrimaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    loading: Boolean = false,
    fullWidth: Boolean = false,
    leadingIcon: ImageVector? = null,
    trailingIcon: ImageVector? = null,
    contentDescription: String? = null
) {
    val buttonModifier = modifier
        .defaultMinSize(minHeight = AppTheme.dimensions.buttonHeightMedium)
        .then(if (fullWidth) Modifier.fillMaxWidth() else Modifier)
        .semantics {
            contentDescription?.let { this.contentDescription = it }
        }
    
    Button(
        onClick = { if (!loading) onClick() },
        modifier = buttonModifier,
        enabled = enabled && !loading,
        shape = AppTheme.shapes.button,
        colors = ButtonDefaults.buttonColors(
            containerColor = AppTheme.colors.primary,
            contentColor = AppTheme.colors.onPrimary,
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
                color = AppTheme.colors.onPrimary,
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
 * Large Primary Button
 * 
 * A larger variant of the primary button for prominent CTAs.
 */
@Composable
fun PrimaryButtonLarge(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    loading: Boolean = false,
    fullWidth: Boolean = true,
    leadingIcon: ImageVector? = null,
    trailingIcon: ImageVector? = null
) {
    Button(
        onClick = { if (!loading) onClick() },
        modifier = modifier
            .height(AppTheme.dimensions.buttonHeightLarge)
            .then(if (fullWidth) Modifier.fillMaxWidth() else Modifier),
        enabled = enabled && !loading,
        shape = AppTheme.shapes.button,
        colors = ButtonDefaults.buttonColors(
            containerColor = AppTheme.colors.primary,
            contentColor = AppTheme.colors.onPrimary,
            disabledContainerColor = AppTheme.colors.disabled,
            disabledContentColor = AppTheme.colors.disabledContent
        ),
        contentPadding = PaddingValues(
            horizontal = AppTheme.dimensions.spacingXl,
            vertical = AppTheme.dimensions.spacingMd
        )
    ) {
        if (loading) {
            CircularProgressIndicator(
                modifier = Modifier.size(24.dp),
                color = AppTheme.colors.onPrimary,
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
                        modifier = Modifier.size(AppTheme.dimensions.iconSizeMedium)
                    )
                    Spacer(modifier = Modifier.width(AppTheme.dimensions.spacingSm))
                }
                
                Text(
                    text = text,
                    style = AppTheme.typography.titleSmall
                )
                
                trailingIcon?.let { icon ->
                    Spacer(modifier = Modifier.width(AppTheme.dimensions.spacingSm))
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        modifier = Modifier.size(AppTheme.dimensions.iconSizeMedium)
                    )
                }
            }
        }
    }
}

// ============================================================
// PREVIEWS
// ============================================================

@Preview(showBackground = true)
@Composable
private fun PrimaryButtonPreview() {
    AppTheme {
        PrimaryButton(
            text = "Primary Button",
            onClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PrimaryButtonLoadingPreview() {
    AppTheme {
        PrimaryButton(
            text = "Loading",
            onClick = {},
            loading = true
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PrimaryButtonDisabledPreview() {
    AppTheme {
        PrimaryButton(
            text = "Disabled",
            onClick = {},
            enabled = false
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PrimaryButtonFullWidthPreview() {
    AppTheme {
        PrimaryButtonLarge(
            text = "Full Width Button",
            onClick = {},
            fullWidth = true
        )
    }
}
