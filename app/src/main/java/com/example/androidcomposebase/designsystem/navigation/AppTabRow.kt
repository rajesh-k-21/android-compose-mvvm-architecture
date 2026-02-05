package com.example.androidcomposebase.designsystem.navigation

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androidcomposebase.designsystem.theme.AppTheme

/**
 * App Tab Row
 * 
 * A Material 3 tab row for switching between content.
 * 
 * @param tabs List of tab labels
 * @param selectedTabIndex Currently selected tab index
 * @param onTabSelected Callback when tab is selected
 * @param modifier Modifier to be applied
 * @param scrollable Whether the tabs should be scrollable
 */
@Composable
fun AppTabRow(
    tabs: List<String>,
    selectedTabIndex: Int,
    onTabSelected: (Int) -> Unit,
    modifier: Modifier = Modifier,
    scrollable: Boolean = false,
    containerColor: Color = AppTheme.colors.surface,
    contentColor: Color = AppTheme.colors.onSurface
) {
    if (scrollable) {
        ScrollableTabRow(
            selectedTabIndex = selectedTabIndex,
            modifier = modifier.fillMaxWidth(),
            containerColor = containerColor,
            contentColor = contentColor,
            edgePadding = AppTheme.dimensions.spacingLg,
            indicator = { tabPositions ->
                if (selectedTabIndex < tabPositions.size) {
                    TabRowDefaults.SecondaryIndicator(
                        modifier = Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex]),
                        color = AppTheme.colors.primary
                    )
                }
            },
            divider = {}
        ) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    selected = selectedTabIndex == index,
                    onClick = { onTabSelected(index) },
                    text = {
                        Text(
                            text = title,
                            style = AppTheme.typography.labelLarge,
                            color = if (selectedTabIndex == index) {
                                AppTheme.colors.primary
                            } else {
                                AppTheme.colors.textSecondary
                            }
                        )
                    }
                )
            }
        }
    } else {
        TabRow(
            selectedTabIndex = selectedTabIndex,
            modifier = modifier.fillMaxWidth(),
            containerColor = containerColor,
            contentColor = contentColor,
            indicator = { tabPositions ->
                if (selectedTabIndex < tabPositions.size) {
                    TabRowDefaults.SecondaryIndicator(
                        modifier = Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex]),
                        color = AppTheme.colors.primary
                    )
                }
            },
            divider = {}
        ) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    selected = selectedTabIndex == index,
                    onClick = { onTabSelected(index) },
                    text = {
                        Text(
                            text = title,
                            style = AppTheme.typography.labelLarge,
                            color = if (selectedTabIndex == index) {
                                AppTheme.colors.primary
                            } else {
                                AppTheme.colors.textSecondary
                            }
                        )
                    }
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
private fun AppTabRowPreview() {
    AppTheme {
        AppTabRow(
            tabs = listOf("Overview", "Details", "Reviews"),
            selectedTabIndex = 0,
            onTabSelected = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun AppScrollableTabRowPreview() {
    AppTheme {
        AppTabRow(
            tabs = listOf("All", "Active", "Completed", "Pending", "Archived"),
            selectedTabIndex = 0,
            onTabSelected = {},
            scrollable = true
        )
    }
}
