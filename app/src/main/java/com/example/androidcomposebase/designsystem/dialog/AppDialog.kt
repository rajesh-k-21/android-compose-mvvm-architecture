package com.example.androidcomposebase.designsystem.dialog

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.androidcomposebase.designsystem.button.PrimaryButton
import com.example.androidcomposebase.designsystem.button.SecondaryButton
import com.example.androidcomposebase.designsystem.theme.AppTheme

/**
 * App Dialog
 * 
 * A customizable alert dialog with:
 * - Title and message
 * - Positive and negative actions
 * - Optional icon
 * - Custom content slot
 * 
 * @param isVisible Whether the dialog is visible
 * @param onDismiss Callback when dialog is dismissed
 * @param title Dialog title
 * @param message Dialog message
 * @param positiveButtonText Text for positive action
 * @param onPositiveClick Callback for positive action
 * @param negativeButtonText Text for negative action (optional)
 * @param onNegativeClick Callback for negative action (optional)
 * @param icon Optional icon to display
 * @param iconTint Icon color
 * @param dismissOnBackPress Whether to dismiss on back press
 * @param dismissOnClickOutside Whether to dismiss on click outside
 */
@Composable
fun AppDialog(
    isVisible: Boolean,
    onDismiss: () -> Unit,
    title: String,
    message: String? = null,
    positiveButtonText: String = "OK",
    onPositiveClick: () -> Unit = onDismiss,
    negativeButtonText: String? = null,
    onNegativeClick: (() -> Unit)? = null,
    icon: ImageVector? = null,
    iconTint: Color = AppTheme.colors.primary,
    dismissOnBackPress: Boolean = true,
    dismissOnClickOutside: Boolean = true
) {
    if (isVisible) {
        AlertDialog(
            onDismissRequest = {
                if (dismissOnClickOutside) onDismiss()
            },
            icon = icon?.let {
                {
                    Icon(
                        imageVector = it,
                        contentDescription = null,
                        modifier = Modifier.size(AppTheme.dimensions.iconSizeLarge),
                        tint = iconTint
                    )
                }
            },
            title = {
                Text(
                    text = title,
                    style = AppTheme.typography.titleLarge,
                    color = AppTheme.colors.textPrimary
                )
            },
            text = message?.let {
                {
                    Text(
                        text = it,
                        style = AppTheme.typography.bodyMedium,
                        color = AppTheme.colors.textSecondary
                    )
                }
            },
            confirmButton = {
                TextButton(onClick = onPositiveClick) {
                    Text(
                        text = positiveButtonText,
                        style = AppTheme.typography.labelLarge,
                        color = AppTheme.colors.primary
                    )
                }
            },
            dismissButton = negativeButtonText?.let {
                {
                    TextButton(onClick = { onNegativeClick?.invoke() ?: onDismiss() }) {
                        Text(
                            text = it,
                            style = AppTheme.typography.labelLarge,
                            color = AppTheme.colors.textSecondary
                        )
                    }
                }
            },
            shape = AppTheme.shapes.dialog,
            containerColor = AppTheme.colors.surface,
            titleContentColor = AppTheme.colors.textPrimary,
            textContentColor = AppTheme.colors.textSecondary,
            properties = DialogProperties(
                dismissOnBackPress = dismissOnBackPress,
                dismissOnClickOutside = dismissOnClickOutside
            )
        )
    }
}

/**
 * App Confirmation Dialog
 * 
 * A dialog for confirming destructive actions.
 */
@Composable
fun AppConfirmationDialog(
    isVisible: Boolean,
    onDismiss: () -> Unit,
    title: String,
    message: String,
    confirmButtonText: String = "Confirm",
    cancelButtonText: String = "Cancel",
    onConfirm: () -> Unit,
    isDestructive: Boolean = false
) {
    if (isVisible) {
        Dialog(onDismissRequest = onDismiss) {
            Surface(
                shape = AppTheme.shapes.dialog,
                color = AppTheme.colors.surface
            ) {
                Column(
                    modifier = Modifier.padding(AppTheme.dimensions.spacingXl)
                ) {
                    Text(
                        text = title,
                        style = AppTheme.typography.titleLarge,
                        color = AppTheme.colors.textPrimary
                    )
                    
                    Spacer(modifier = Modifier.height(AppTheme.dimensions.spacingMd))
                    
                    Text(
                        text = message,
                        style = AppTheme.typography.bodyMedium,
                        color = AppTheme.colors.textSecondary
                    )
                    
                    Spacer(modifier = Modifier.height(AppTheme.dimensions.spacingXl))
                    
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        SecondaryButton(
                            text = cancelButtonText,
                            onClick = onDismiss
                        )
                        
                        Spacer(modifier = Modifier.width(AppTheme.dimensions.spacingSm))
                        
                        if (isDestructive) {
                            com.example.androidcomposebase.designsystem.button.DangerButton(
                                text = confirmButtonText,
                                onClick = {
                                    onConfirm()
                                    onDismiss()
                                }
                            )
                        } else {
                            PrimaryButton(
                                text = confirmButtonText,
                                onClick = {
                                    onConfirm()
                                    onDismiss()
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

/**
 * App Custom Dialog
 * 
 * A dialog with fully custom content.
 */
@Composable
fun AppCustomDialog(
    isVisible: Boolean,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
    dismissOnBackPress: Boolean = true,
    dismissOnClickOutside: Boolean = true,
    content: @Composable () -> Unit
) {
    if (isVisible) {
        Dialog(
            onDismissRequest = {
                if (dismissOnClickOutside) onDismiss()
            },
            properties = DialogProperties(
                dismissOnBackPress = dismissOnBackPress,
                dismissOnClickOutside = dismissOnClickOutside
            )
        ) {
            Surface(
                modifier = modifier,
                shape = AppTheme.shapes.dialog,
                color = AppTheme.colors.surface
            ) {
                content()
            }
        }
    }
}

// ============================================================
// PREVIEWS
// ============================================================

@Preview(showBackground = true)
@Composable
private fun AppDialogPreview() {
    AppTheme {
        // Dialog preview - shown as surface for preview
        Surface(
            shape = AppTheme.shapes.dialog,
            color = AppTheme.colors.surface
        ) {
            Column(
                modifier = Modifier.padding(AppTheme.dimensions.spacingXl),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Dialog Title",
                    style = AppTheme.typography.titleLarge
                )
                Spacer(modifier = Modifier.height(AppTheme.dimensions.spacingMd))
                Text(
                    text = "This is the dialog message.",
                    style = AppTheme.typography.bodyMedium,
                    color = AppTheme.colors.textSecondary
                )
                Spacer(modifier = Modifier.height(AppTheme.dimensions.spacingLg))
                Row(horizontalArrangement = Arrangement.End) {
                    TextButton(onClick = {}) {
                        Text("Cancel", color = AppTheme.colors.textSecondary)
                    }
                    TextButton(onClick = {}) {
                        Text("OK", color = AppTheme.colors.primary)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun AppConfirmationDialogPreview() {
    AppTheme {
        Surface(
            shape = AppTheme.shapes.dialog,
            color = AppTheme.colors.surface
        ) {
            Column(
                modifier = Modifier.padding(AppTheme.dimensions.spacingXl)
            ) {
                Icon(
                    imageVector = Icons.Default.Warning,
                    contentDescription = null,
                    modifier = Modifier.size(AppTheme.dimensions.iconSizeLarge),
                    tint = AppTheme.colors.warning
                )
                Spacer(modifier = Modifier.height(AppTheme.dimensions.spacingMd))
                Text(
                    text = "Delete Item?",
                    style = AppTheme.typography.titleLarge
                )
                Spacer(modifier = Modifier.height(AppTheme.dimensions.spacingSm))
                Text(
                    text = "This action cannot be undone.",
                    style = AppTheme.typography.bodyMedium,
                    color = AppTheme.colors.textSecondary
                )
            }
        }
    }
}
