package com.example.androidcomposebase.designsystem.input

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import com.example.androidcomposebase.designsystem.theme.AppTheme

/**
 * App Text Field
 * 
 * A customizable text input field with support for:
 * - Label and placeholder
 * - Error state with message
 * - Leading and trailing icons
 * - Various keyboard options
 * - Single/multi-line modes
 * 
 * @param value Current text value
 * @param onValueChange Callback when text changes
 * @param modifier Modifier to be applied
 * @param label Optional label text
 * @param placeholder Optional placeholder text
 * @param isError Whether field is in error state
 * @param errorMessage Optional error message to display
 * @param leadingIcon Optional leading icon
 * @param trailingIcon Optional trailing icon
 * @param onTrailingIconClick Callback for trailing icon click
 * @param keyboardOptions Keyboard configuration
 * @param keyboardActions Keyboard action handlers
 * @param singleLine Whether field is single line
 * @param maxLines Maximum number of lines
 * @param enabled Whether field is enabled
 * @param readOnly Whether field is read-only
 * @param visualTransformation Visual transformation (e.g., password masking)
 * @param showClearButton Whether to show clear button when text is not empty
 */
@Composable
fun AppTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String? = null,
    placeholder: String? = null,
    isError: Boolean = false,
    errorMessage: String? = null,
    leadingIcon: ImageVector? = null,
    trailingIcon: ImageVector? = null,
    onTrailingIconClick: (() -> Unit)? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = true,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    minLines: Int = 1,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    showClearButton: Boolean = false
) {
    Column(modifier = modifier) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            readOnly = readOnly,
            label = label?.let { { Text(text = it, style = AppTheme.typography.bodyMedium) } },
            placeholder = placeholder?.let {
                { Text(text = it, style = AppTheme.typography.bodyMedium) }
            },
            leadingIcon = leadingIcon?.let {
                {
                    Icon(
                        imageVector = it,
                        contentDescription = null,
                        modifier = Modifier.size(AppTheme.dimensions.iconSizeMedium),
                        tint = if (isError) AppTheme.colors.error else AppTheme.colors.textSecondary
                    )
                }
            },
            trailingIcon = {
                when {
                    showClearButton && value.isNotEmpty() -> {
                        IconButton(onClick = { onValueChange("") }) {
                            Icon(
                                imageVector = Icons.Default.Clear,
                                contentDescription = "Clear",
                                modifier = Modifier.size(AppTheme.dimensions.iconSizeMedium),
                                tint = AppTheme.colors.textSecondary
                            )
                        }
                    }
                    trailingIcon != null -> {
                        IconButton(
                            onClick = { onTrailingIconClick?.invoke() },
                            enabled = onTrailingIconClick != null
                        ) {
                            Icon(
                                imageVector = trailingIcon,
                                contentDescription = null,
                                modifier = Modifier.size(AppTheme.dimensions.iconSizeMedium),
                                tint = if (isError) AppTheme.colors.error else AppTheme.colors.textSecondary
                            )
                        }
                    }
                }
            },
            isError = isError,
            visualTransformation = visualTransformation,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            singleLine = singleLine,
            maxLines = maxLines,
            minLines = minLines,
            textStyle = AppTheme.typography.bodyLarge,
            shape = AppTheme.shapes.textField,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = AppTheme.colors.borderFocused,
                unfocusedBorderColor = AppTheme.colors.border,
                errorBorderColor = AppTheme.colors.borderError,
                focusedLabelColor = AppTheme.colors.primary,
                unfocusedLabelColor = AppTheme.colors.textSecondary,
                errorLabelColor = AppTheme.colors.error,
                cursorColor = AppTheme.colors.primary,
                errorCursorColor = AppTheme.colors.error,
                focusedTextColor = AppTheme.colors.textPrimary,
                unfocusedTextColor = AppTheme.colors.textPrimary,
                disabledTextColor = AppTheme.colors.textDisabled,
                focusedPlaceholderColor = AppTheme.colors.textTertiary,
                unfocusedPlaceholderColor = AppTheme.colors.textTertiary
            )
        )
        
        // Error message
        AnimatedVisibility(visible = isError && !errorMessage.isNullOrEmpty()) {
            Text(
                text = errorMessage ?: "",
                style = AppTheme.typography.bodySmall,
                color = AppTheme.colors.error,
                modifier = Modifier.padding(
                    start = AppTheme.dimensions.spacingMd,
                    top = AppTheme.dimensions.spacingXs
                )
            )
        }
    }
}

/**
 * App Password Field
 * 
 * A password input field with visibility toggle.
 * 
 * @param value Current password value
 * @param onValueChange Callback when password changes
 * @param modifier Modifier to be applied
 * @param label Optional label text
 * @param placeholder Optional placeholder text
 * @param isError Whether field is in error state
 * @param errorMessage Optional error message
 * @param enabled Whether field is enabled
 * @param keyboardActions Keyboard action handlers
 */
@Composable
fun AppPasswordField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String? = "Password",
    placeholder: String? = null,
    isError: Boolean = false,
    errorMessage: String? = null,
    enabled: Boolean = true,
    keyboardActions: KeyboardActions = KeyboardActions.Default
) {
    var passwordVisible by remember { mutableStateOf(false) }
    
    Column(modifier = modifier) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            label = label?.let { { Text(text = it, style = AppTheme.typography.bodyMedium) } },
            placeholder = placeholder?.let {
                { Text(text = it, style = AppTheme.typography.bodyMedium) }
            },
            trailingIcon = {
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(
                        imageVector = if (passwordVisible) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                        contentDescription = if (passwordVisible) "Hide password" else "Show password",
                        modifier = Modifier.size(AppTheme.dimensions.iconSizeMedium),
                        tint = AppTheme.colors.textSecondary
                    )
                }
            },
            isError = isError,
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            keyboardActions = keyboardActions,
            singleLine = true,
            textStyle = AppTheme.typography.bodyLarge,
            shape = AppTheme.shapes.textField,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = AppTheme.colors.borderFocused,
                unfocusedBorderColor = AppTheme.colors.border,
                errorBorderColor = AppTheme.colors.borderError,
                focusedLabelColor = AppTheme.colors.primary,
                unfocusedLabelColor = AppTheme.colors.textSecondary,
                errorLabelColor = AppTheme.colors.error,
                cursorColor = AppTheme.colors.primary,
                focusedTextColor = AppTheme.colors.textPrimary,
                unfocusedTextColor = AppTheme.colors.textPrimary
            )
        )
        
        // Error message
        AnimatedVisibility(visible = isError && !errorMessage.isNullOrEmpty()) {
            Text(
                text = errorMessage ?: "",
                style = AppTheme.typography.bodySmall,
                color = AppTheme.colors.error,
                modifier = Modifier.padding(
                    start = AppTheme.dimensions.spacingMd,
                    top = AppTheme.dimensions.spacingXs
                )
            )
        }
    }
}

// ============================================================
// PREVIEWS
// ============================================================

@Preview(showBackground = true)
@Composable
private fun AppTextFieldPreview() {
    AppTheme {
        AppTextField(
            value = "",
            onValueChange = {},
            label = "Email",
            placeholder = "Enter your email"
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun AppTextFieldWithValuePreview() {
    AppTheme {
        AppTextField(
            value = "john@example.com",
            onValueChange = {},
            label = "Email",
            showClearButton = true
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun AppTextFieldErrorPreview() {
    AppTheme {
        AppTextField(
            value = "invalid",
            onValueChange = {},
            label = "Email",
            isError = true,
            errorMessage = "Please enter a valid email address"
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun AppPasswordFieldPreview() {
    AppTheme {
        AppPasswordField(
            value = "password123",
            onValueChange = {},
            label = "Password"
        )
    }
}
