package com.example.androidcomposebase.designsystem.bottomsheet

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import com.example.androidcomposebase.designsystem.theme.AppTheme

/**
 * App Modal Bottom Sheet
 * 
 * A modal bottom sheet that overlays content with a scrim.
 * Use for menus, selection dialogs, and additional actions.
 * 
 * @param isVisible Whether the sheet is visible
 * @param onDismiss Callback when sheet is dismissed
 * @param modifier Modifier to be applied
 * @param sheetState State of the bottom sheet
 * @param title Optional title text
 * @param showDragHandle Whether to show the drag handle
 * @param containerColor Background color
 * @param shape Sheet shape
 * @param content Sheet content
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppModalBottomSheet(
    isVisible: Boolean,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
    sheetState: SheetState = rememberModalBottomSheetState(),
    title: String? = null,
    showDragHandle: Boolean = true,
    containerColor: Color = AppTheme.colors.surface,
    shape: Shape = AppTheme.shapes.bottomSheet,
    content: @Composable ColumnScope.() -> Unit
) {
    if (isVisible) {
        ModalBottomSheet(
            onDismissRequest = onDismiss,
            modifier = modifier,
            sheetState = sheetState,
            shape = shape,
            containerColor = containerColor,
            contentColor = AppTheme.colors.onSurface,
            scrimColor = AppTheme.colors.scrim,
            dragHandle = if (showDragHandle) {
                { BottomSheetDefaults.DragHandle() }
            } else null
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .navigationBarsPadding()
            ) {
                // Title
                if (!title.isNullOrEmpty()) {
                    Text(
                        text = title,
                        style = AppTheme.typography.titleMedium,
                        color = AppTheme.colors.textPrimary,
                        modifier = Modifier.padding(
                            horizontal = AppTheme.dimensions.spacingLg,
                            vertical = AppTheme.dimensions.spacingSm
                        )
                    )
                    HorizontalDivider(color = AppTheme.colors.divider)
                }
                
                // Content
                content()
                
                // Bottom padding
                Spacer(modifier = Modifier.height(AppTheme.dimensions.spacingSm))
            }
        }
    }
}

// ============================================================
// PREVIEWS
// ============================================================

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
private fun AppModalBottomSheetPreview() {
    AppTheme {
        // Note: Preview only shows content, actual sheet needs isVisible = true
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(AppTheme.dimensions.spacingLg)
        ) {
            Text(
                text = "Select Option",
                style = AppTheme.typography.titleMedium
            )
            HorizontalDivider(
                modifier = Modifier.padding(vertical = AppTheme.dimensions.spacingSm),
                color = AppTheme.colors.divider
            )
            androidx.compose.material3.ListItem(
                headlineContent = { Text("Option 1") },
                modifier = Modifier.fillMaxWidth()
            )
            androidx.compose.material3.ListItem(
                headlineContent = { Text("Option 2") },
                modifier = Modifier.fillMaxWidth()
            )
            androidx.compose.material3.ListItem(
                headlineContent = { Text("Option 3") },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}
