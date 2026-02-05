package com.example.androidcomposebase.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.example.androidcomposebase.feature.sample.SampleScreen
import com.example.androidcomposebase.feature.userlist.UserListScreen

/**
 * Navigation 3 setup for the application.
 *
 * Navigation 3 uses a Compose-first approach with:
 * - SnapshotStateList for back stack management
 * - NavDisplay for rendering destinations
 * - entryProvider DSL for mapping keys to composable content
 * - Type-safe navigation keys
 */

/**
 * Main navigation composable using Navigation 3.
 *
 * Features:
 * - Direct back stack control via SnapshotStateList
 * - Type-safe navigation with sealed classes
 * - SavedState handling for process death
 *
 * @param modifier Optional modifier for the NavDisplay
 */
@Composable
fun AppNavigation(
    modifier: Modifier = Modifier
) {
    // Create and remember the back stack as a mutable state list
    // Start with SampleKey to demonstrate the Sample feature
    val backStack = remember { mutableStateListOf<Any>(SampleKey) }

    // Navigation helper functions
    val onNavigate: (Any) -> Unit = { key ->
        backStack.add(key)
    }

    val onNavigateBack: () -> Unit = {
        if (backStack.size > 1) {
            backStack.removeAt(backStack.lastIndex)
        }
    }

    // Entry decorator for SavedState support
    val saveableStateDecorator = rememberSaveableStateHolderNavEntryDecorator<Any>()

    // NavDisplay observes the back stack and renders appropriate content
    NavDisplay<Any>(
        backStack = backStack.toList(),
        entryDecorators = listOf(saveableStateDecorator),
        onBack = onNavigateBack,
        entryProvider = { key ->
            when (key) {
                is UserListKey -> NavEntry(key) {
                    UserListScreen(
                        onNavigateToUserDetail = { userId ->
                            onNavigate(UserDetailKey(userId))
                        }
                    )
                }

                is UserDetailKey -> NavEntry(key) {
                    UserDetailScreen(
                        userId = key.userId,
                        onNavigateBack = onNavigateBack
                    )
                }

                is SettingsKey -> NavEntry(key) {
                    SettingsScreen(
                        onNavigateBack = onNavigateBack
                    )
                }
                
                is SampleKey -> NavEntry(key) {
                    SampleScreen(
                        onNavigateToDetail = { itemId ->
                            onNavigate(SampleDetailKey(itemId))
                        },
                        onNavigateBack = onNavigateBack
                    )
                }
                
                is SampleDetailKey -> NavEntry(key) {
                    SampleDetailScreen(
                        itemId = key.itemId,
                        onNavigateBack = onNavigateBack
                    )
                }

                else -> NavEntry(key) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("Unknown destination")
                    }
                }
            }
        },
        modifier = modifier
    )
}

/**
 * User Detail screen composable.
 *
 * @param userId The ID of the user to display
 * @param onNavigateBack Callback to navigate back
 */
@Composable
fun UserDetailScreen(
    userId: Int,
    onNavigateBack: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "User Detail",
                style = MaterialTheme.typography.headlineMedium
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "User ID: $userId",
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = onNavigateBack) {
                Text("Go Back")
            }
        }
    }
}

/**
 * Settings screen composable.
 *
 * @param onNavigateBack Callback to navigate back
 */
@Composable
fun SettingsScreen(
    onNavigateBack: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Settings",
                style = MaterialTheme.typography.headlineMedium
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = onNavigateBack) {
                Text("Go Back")
            }
        }
    }
}

/**
 * Sample Detail screen composable.
 *
 * @param itemId The ID of the item to display
 * @param onNavigateBack Callback to navigate back
 */
@Composable
fun SampleDetailScreen(
    itemId: String,
    onNavigateBack: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Sample Detail",
                style = MaterialTheme.typography.headlineMedium
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Item ID: $itemId",
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = onNavigateBack) {
                Text("Go Back")
            }
        }
    }
}
