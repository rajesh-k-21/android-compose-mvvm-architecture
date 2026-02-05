package com.example.androidcomposebase.designsystem.input

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androidcomposebase.designsystem.theme.AppTheme

/**
 * App Search Field
 * 
 * A specialized search input field with:
 * - Search icon
 * - Clear button
 * - Rounded styling
 * - Keyboard search action
 * 
 * @param query Current search query
 * @param onQueryChange Callback when query changes
 * @param modifier Modifier to be applied
 * @param placeholder Placeholder text
 * @param onSearch Callback when search action is triggered
 * @param enabled Whether field is enabled
 */
@Composable
fun AppSearchField(
    query: String,
    onQueryChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String = "Search...",
    onSearch: ((String) -> Unit)? = null,
    enabled: Boolean = true
) {
    OutlinedTextField(
        value = query,
        onValueChange = onQueryChange,
        modifier = modifier.fillMaxWidth(),
        enabled = enabled,
        placeholder = {
            Text(
                text = placeholder,
                style = AppTheme.typography.bodyMedium,
                color = AppTheme.colors.textTertiary
            )
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search",
                modifier = Modifier.size(AppTheme.dimensions.iconSizeMedium),
                tint = AppTheme.colors.textSecondary
            )
        },
        trailingIcon = {
            if (query.isNotEmpty()) {
                IconButton(onClick = { onQueryChange("") }) {
                    Icon(
                        imageVector = Icons.Default.Clear,
                        contentDescription = "Clear search",
                        modifier = Modifier.size(AppTheme.dimensions.iconSizeMedium),
                        tint = AppTheme.colors.textSecondary
                    )
                }
            }
        },
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Search
        ),
        keyboardActions = KeyboardActions(
            onSearch = { onSearch?.invoke(query) }
        ),
        singleLine = true,
        textStyle = AppTheme.typography.bodyMedium,
        shape = RoundedCornerShape(24.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = AppTheme.colors.borderFocused,
            unfocusedBorderColor = AppTheme.colors.border,
            focusedContainerColor = AppTheme.colors.surface,
            unfocusedContainerColor = AppTheme.colors.surfaceVariant,
            cursorColor = AppTheme.colors.primary,
            focusedTextColor = AppTheme.colors.textPrimary,
            unfocusedTextColor = AppTheme.colors.textPrimary
        )
    )
}

// ============================================================
// PREVIEWS
// ============================================================

@Preview(showBackground = true)
@Composable
private fun AppSearchFieldEmptyPreview() {
    AppTheme {
        AppSearchField(
            query = "",
            onQueryChange = {},
            placeholder = "Search users..."
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun AppSearchFieldWithQueryPreview() {
    AppTheme {
        AppSearchField(
            query = "John Doe",
            onQueryChange = {}
        )
    }
}
