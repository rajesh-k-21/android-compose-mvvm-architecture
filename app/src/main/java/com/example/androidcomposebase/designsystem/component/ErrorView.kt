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
import androidx.compose.material.icons.filled.Warning
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
import com.example.androidcomposebase.designsystem.button.PrimaryButton
import com.example.androidcomposebase.designsystem.theme.AppTheme

/**
 * Error View
 * 
 * A reusable error display component with:
 * - Icon
 * - Title
 * - Description
 * - Retry button
 * 
 * @param message The error message to display
 * @param modifier Modifier to be applied
 * @param title Optional title (defaults to "Something went wrong")
 * @param icon Error icon
 * @param iconTint Icon color
 * @param onRetry Optional retry action
 * @param retryText Retry button text
 */
@Composable
fun ErrorView(
    message: String,
    modifier: Modifier = Modifier,
    title: String = "Something went wrong",
    icon: ImageVector = Icons.Default.Warning,
    iconTint: Color = AppTheme.colors.error,
    onRetry: (() -> Unit)? = null,
    retryText: String = "Try Again"
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
                contentDescription = "Error",
                modifier = Modifier.size(64.dp),
                tint = iconTint
            )
            
            Spacer(modifier = Modifier.height(AppTheme.dimensions.spacingLg))
            
            // Title
            Text(
                text = title,
                style = AppTheme.typography.titleLarge,
                color = AppTheme.colors.textPrimary,
                textAlign = TextAlign.Center
            )
            
            Spacer(modifier = Modifier.height(AppTheme.dimensions.spacingSm))
            
            // Message
            Text(
                text = message,
                style = AppTheme.typography.bodyMedium,
                color = AppTheme.colors.textSecondary,
                textAlign = TextAlign.Center
            )
            
            // Retry button
            if (onRetry != null) {
                Spacer(modifier = Modifier.height(AppTheme.dimensions.spacingXl))
                PrimaryButton(
                    text = retryText,
                    onClick = onRetry
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
private fun ErrorViewPreview() {
    AppTheme {
        ErrorView(
            message = "Failed to load data. Please check your connection.",
            onRetry = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ErrorViewNoRetryPreview() {
    AppTheme {
        ErrorView(
            message = "This feature is not available."
        )
    }
}
