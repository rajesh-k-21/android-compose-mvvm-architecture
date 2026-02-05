package com.example.androidcomposebase.feature.userlist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.androidcomposebase.domain.model.User
import com.example.androidcomposebase.feature.userlist.components.UserItem
import com.example.androidcomposebase.ui.theme.AndroidComposeBaseTheme
import kotlinx.coroutines.flow.collectLatest

/**
 * User List Screen - Entry point composable that connects to the ViewModel.
 * 
 * This composable handles:
 * - State collection using [collectAsStateWithLifecycle]
 * - Effect handling using [LaunchedEffect]
 * - Connecting UI events to the ViewModel
 * 
 * @param viewModel The ViewModel instance (injected by Hilt)
 * @param onNavigateToUserDetail Callback for navigating to user detail
 */
@Composable
fun UserListScreen(
    viewModel: UserListViewModel = hiltViewModel(),
    onNavigateToUserDetail: (Int) -> Unit = {}
) {
    // Collect state with lifecycle awareness
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    
    // Remember snackbar host state
    val snackbarHostState = remember { SnackbarHostState() }
    
    // Remember list state for scrolling effects
    val listState = rememberLazyListState()
    
    // Handle effects
    LaunchedEffect(key1 = Unit) {
        viewModel.effect.collectLatest { effect ->
            when (effect) {
                is UserListUiEffect.NavigateToUserDetail -> {
                    onNavigateToUserDetail(effect.userId)
                }
                is UserListUiEffect.ShowSnackbar -> {
                    snackbarHostState.showSnackbar(effect.message)
                }
                is UserListUiEffect.ScrollToTop -> {
                    listState.animateScrollToItem(0)
                }
            }
        }
    }
    
    // Delegate to stateless content composable
    UserListContent(
        uiState = uiState,
        snackbarHostState = snackbarHostState,
        listState = listState,
        onEvent = viewModel::onEvent
    )
}

/**
 * Stateless content composable for the User List screen.
 * 
 * This composable is fully stateless and receives all data and callbacks
 * from its parent. This makes it easy to preview and test.
 * 
 * @param uiState The current UI state
 * @param snackbarHostState State for the snackbar
 * @param listState State for the lazy list
 * @param onEvent Callback for sending events to the ViewModel
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun UserListContent(
    uiState: UserListUiState,
    snackbarHostState: SnackbarHostState,
    listState: LazyListState,
    onEvent: (UserListUiEvent) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Users") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                ),
                actions = {
                    IconButton(
                        onClick = { onEvent(UserListUiEvent.RefreshUsers) },
                        enabled = !uiState.isLoading && !uiState.isRefreshing
                    ) {
                        Icon(
                            imageVector = Icons.Default.Refresh,
                            contentDescription = "Refresh users"
                        )
                    }
                }
            )
        },
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            when {
                // Show loading indicator for initial load
                uiState.isLoading && uiState.users.isEmpty() -> {
                    LoadingState()
                }
                // Show error state when there's an error and no data
                uiState.error != null && uiState.users.isEmpty() -> {
                    ErrorState(
                        error = uiState.error,
                        onRetry = { onEvent(UserListUiEvent.RetryClicked) }
                    )
                }
                // Show empty state when no users
                uiState.users.isEmpty() -> {
                    EmptyState()
                }
                // Show user list
                else -> {
                    PullToRefreshBox(
                        isRefreshing = uiState.isRefreshing,
                        onRefresh = { onEvent(UserListUiEvent.RefreshUsers) }
                    ) {
                        UserList(
                            users = uiState.users,
                            listState = listState,
                            onUserClick = { user -> 
                                onEvent(UserListUiEvent.UserClicked(user)) 
                            }
                        )
                    }
                }
            }
        }
    }
}

/**
 * Composable that displays the list of users.
 */
@Composable
private fun UserList(
    users: List<User>,
    listState: LazyListState,
    onUserClick: (User) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        state = listState,
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(
            items = users,
            key = { user -> user.id }
        ) { user ->
            UserItem(
                user = user,
                onClick = { onUserClick(user) }
            )
        }
    }
}

/**
 * Loading state composable.
 */
@Composable
private fun LoadingState(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            CircularProgressIndicator(
                modifier = Modifier.size(48.dp),
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Loading users...",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

/**
 * Error state composable.
 */
@Composable
private fun ErrorState(
    error: String,
    onRetry: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(32.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Warning,
                contentDescription = "Error",
                modifier = Modifier.size(64.dp),
                tint = MaterialTheme.colorScheme.error
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Something went wrong",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = error,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(24.dp))
            Button(onClick = onRetry) {
                Text("Retry")
            }
        }
    }
}

/**
 * Empty state composable.
 */
@Composable
private fun EmptyState(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "No users found",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Pull down to refresh",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun UserListContentPreview() {
    AndroidComposeBaseTheme {
        UserListContent(
            uiState = UserListUiState(
                users = listOf(
                    User(1, "Alice", "alice@example.com", bio = "Developer"),
                    User(2, "Bob", "bob@example.com", bio = "Designer", isActive = false)
                )
            ),
            snackbarHostState = remember { SnackbarHostState() },
            listState = rememberLazyListState(),
            onEvent = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun LoadingStatePreview() {
    AndroidComposeBaseTheme {
        LoadingState()
    }
}

@Preview(showBackground = true)
@Composable
private fun ErrorStatePreview() {
    AndroidComposeBaseTheme {
        ErrorState(
            error = "Network connection failed",
            onRetry = {}
        )
    }
}
