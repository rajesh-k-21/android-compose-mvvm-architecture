package com.example.androidcomposebase.designsystem.feedback

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarData
import androidx.compose.material3.SnackbarDefaults
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import com.example.androidcomposebase.designsystem.theme.AppTheme

/**
 * App Snackbar
 * 
 * A themed snackbar component.
 */
@Composable
fun AppSnackbar(
    snackbarData: SnackbarData,
    modifier: Modifier = Modifier,
    actionOnNewLine: Boolean = false,
    shape: Shape = AppTheme.shapes.snackbar,
    containerColor: Color = AppTheme.colors.surfaceElevated,
    contentColor: Color = AppTheme.colors.textPrimary,
    actionColor: Color = AppTheme.colors.primary
) {
    Snackbar(
        snackbarData = snackbarData,
        modifier = modifier,
        actionOnNewLine = actionOnNewLine,
        shape = shape,
        containerColor = containerColor,
        contentColor = contentColor,
        actionColor = actionColor,
        actionContentColor = actionColor,
        dismissActionContentColor = AppTheme.colors.textSecondary
    )
}

/**
 * App Snackbar Host
 * 
 * A themed snackbar host.
 */
@Composable
fun AppSnackbarHost(
    hostState: SnackbarHostState,
    modifier: Modifier = Modifier
) {
    SnackbarHost(
        hostState = hostState,
        modifier = modifier
    ) { data ->
        AppSnackbar(snackbarData = data)
    }
}

/**
 * Success Snackbar
 * 
 * A snackbar styled for success messages.
 */
@Composable
fun SuccessSnackbar(
    snackbarData: SnackbarData,
    modifier: Modifier = Modifier
) {
    Snackbar(
        snackbarData = snackbarData,
        modifier = modifier,
        shape = AppTheme.shapes.snackbar,
        containerColor = AppTheme.colors.successContainer,
        contentColor = AppTheme.colors.success,
        actionColor = AppTheme.colors.success
    )
}

/**
 * Error Snackbar
 * 
 * A snackbar styled for error messages.
 */
@Composable
fun ErrorSnackbar(
    snackbarData: SnackbarData,
    modifier: Modifier = Modifier
) {
    Snackbar(
        snackbarData = snackbarData,
        modifier = modifier,
        shape = AppTheme.shapes.snackbar,
        containerColor = AppTheme.colors.errorContainer,
        contentColor = AppTheme.colors.error,
        actionColor = AppTheme.colors.error
    )
}

// ============================================================
// PREVIEWS
// ============================================================

@Preview(showBackground = true)
@Composable
private fun AppSnackbarPreview() {
    AppTheme {
        Snackbar(
            modifier = Modifier.padding(AppTheme.dimensions.spacingMd),
            containerColor = AppTheme.colors.surfaceElevated,
            contentColor = AppTheme.colors.textPrimary,
            action = {
                TextButton(onClick = {}) {
                    Text("Undo", color = AppTheme.colors.primary)
                }
            }
        ) {
            Text("Item deleted")
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SuccessSnackbarPreview() {
    AppTheme {
        Snackbar(
            modifier = Modifier.padding(AppTheme.dimensions.spacingMd),
            containerColor = AppTheme.colors.successContainer,
            contentColor = AppTheme.colors.success
        ) {
            Text("Successfully saved!")
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ErrorSnackbarPreview() {
    AppTheme {
        Snackbar(
            modifier = Modifier.padding(AppTheme.dimensions.spacingMd),
            containerColor = AppTheme.colors.errorContainer,
            contentColor = AppTheme.colors.error
        ) {
            Text("Something went wrong")
        }
    }
}
