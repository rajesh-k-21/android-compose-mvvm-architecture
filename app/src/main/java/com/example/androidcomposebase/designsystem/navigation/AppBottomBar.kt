package com.example.androidcomposebase.designsystem.navigation

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import com.example.androidcomposebase.designsystem.theme.AppTheme

/**
 * Bottom Navigation Item
 * 
 * Data class representing a bottom navigation item.
 */
data class BottomNavItem(
    val label: String,
    val icon: ImageVector,
    val selectedIcon: ImageVector = icon,
    val badge: String? = null,
    val showBadge: Boolean = false
)

/**
 * App Bottom Navigation Bar
 * 
 * A Material 3 bottom navigation bar.
 * 
 * @param items List of navigation items
 * @param selectedIndex Currently selected index
 * @param onItemSelected Callback when item is selected
 * @param modifier Modifier to be applied
 */
@Composable
fun AppBottomBar(
    items: List<BottomNavItem>,
    selectedIndex: Int,
    onItemSelected: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    NavigationBar(
        modifier = modifier,
        containerColor = AppTheme.colors.surface,
        contentColor = AppTheme.colors.onSurface
    ) {
        items.forEachIndexed { index, item ->
            val selected = index == selectedIndex
            
            NavigationBarItem(
                selected = selected,
                onClick = { onItemSelected(index) },
                icon = {
                    if (item.showBadge || item.badge != null) {
                        BadgedBox(
                            badge = {
                                if (item.badge != null) {
                                    Badge {
                                        Text(
                                            text = item.badge,
                                            style = AppTheme.typography.labelSmall
                                        )
                                    }
                                } else if (item.showBadge) {
                                    Badge()
                                }
                            }
                        ) {
                            Icon(
                                imageVector = if (selected) item.selectedIcon else item.icon,
                                contentDescription = item.label,
                                modifier = Modifier.size(AppTheme.dimensions.iconSizeMedium)
                            )
                        }
                    } else {
                        Icon(
                            imageVector = if (selected) item.selectedIcon else item.icon,
                            contentDescription = item.label,
                            modifier = Modifier.size(AppTheme.dimensions.iconSizeMedium)
                        )
                    }
                },
                label = {
                    Text(
                        text = item.label,
                        style = AppTheme.typography.labelSmall
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = AppTheme.colors.primary,
                    selectedTextColor = AppTheme.colors.primary,
                    unselectedIconColor = AppTheme.colors.textSecondary,
                    unselectedTextColor = AppTheme.colors.textSecondary,
                    indicatorColor = AppTheme.colors.primaryContainer
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
private fun AppBottomBarPreview() {
    AppTheme {
        val items = listOf(
            BottomNavItem(
                label = "Home",
                icon = Icons.Outlined.Home,
                selectedIcon = Icons.Filled.Home
            ),
            BottomNavItem(
                label = "Favorites",
                icon = Icons.Outlined.FavoriteBorder,
                selectedIcon = Icons.Filled.Favorite,
                badge = "3"
            ),
            BottomNavItem(
                label = "Profile",
                icon = Icons.Outlined.Person,
                selectedIcon = Icons.Filled.Person,
                showBadge = true
            ),
            BottomNavItem(
                label = "Settings",
                icon = Icons.Outlined.Settings,
                selectedIcon = Icons.Filled.Settings
            )
        )
        
        AppBottomBar(
            items = items,
            selectedIndex = 0,
            onItemSelected = {}
        )
    }
}
