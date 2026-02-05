package com.example.androidcomposebase.feature.sample

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.androidcomposebase.designsystem.card.AppCard
import com.example.androidcomposebase.designsystem.component.EmptyView
import com.example.androidcomposebase.designsystem.component.ErrorView
import com.example.androidcomposebase.designsystem.input.AppSearchField
import com.example.androidcomposebase.designsystem.navigation.AppTopBar
import com.example.androidcomposebase.designsystem.progress.FullScreenLoading
import com.example.androidcomposebase.designsystem.text.BodyText
import com.example.androidcomposebase.designsystem.text.TitleText
import com.example.androidcomposebase.designsystem.theme.AppTheme
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/**
 * Sample Screen
 * 
 * Demonstrates a typical screen with:
 * - AppBar with actions
 * - Search functionality
 * - Pull-to-refresh
 * - Loading/Error/Empty states
 * - List with items
 * - Effects handling (snackbar, navigation)
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SampleScreen(
    onNavigateToDetail: (String) -> Unit,
    onNavigateBack: () -> Unit,
    viewModel: SampleViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }
    val listState = rememberLazyListState()
    val scope = rememberCoroutineScope()
    
    // Handle effects
    LaunchedEffect(Unit) {
        viewModel.effect.collectLatest { effect ->
            when (effect) {
                is SampleEffect.NavigateToDetail -> {
                    onNavigateToDetail(effect.itemId)
                }
                is SampleEffect.NavigateBack -> {
                    onNavigateBack()
                }
                is SampleEffect.ShowSuccessMessage -> {
                    snackbarHostState.showSnackbar(effect.message)
                }
                is SampleEffect.ShowErrorMessage -> {
                    snackbarHostState.showSnackbar(effect.message)
                }
                is SampleEffect.ScrollToTop -> {
                    scope.launch { listState.animateScrollToItem(0) }
                }
            }
        }
    }
    
    Scaffold(
        topBar = {
            AppTopBar(
                title = "Sample Feature",
                actions = {
                    IconButton(
                        onClick = { viewModel.onEvent(SampleEvent.Refresh) }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Refresh,
                            contentDescription = "Refresh"
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
                // Loading state
                state.isLoading && !state.hasData -> {
                    FullScreenLoading(message = "Loading items...")
                }
                
                // Error state
                state.error != null && !state.hasData -> {
                    ErrorView(
                        message = state.error ?: "Unknown error",
                        onRetry = { viewModel.onEvent(SampleEvent.Retry) }
                    )
                }
                
                // Content
                else -> {
                    SampleContent(
                        state = state,
                        listState = listState,
                        onEvent = viewModel::onEvent
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SampleContent(
    state: SampleState,
    listState: androidx.compose.foundation.lazy.LazyListState,
    onEvent: (SampleEvent) -> Unit
) {
    PullToRefreshBox(
        isRefreshing = state.isRefreshing,
        onRefresh = { onEvent(SampleEvent.Refresh) }
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            // Search bar
            AppSearchField(
                query = state.searchQuery,
                onQueryChange = { onEvent(SampleEvent.SearchQueryChanged(it)) },
                placeholder = "Search items...",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(AppTheme.dimensions.spacingMd)
            )
            
            when {
                // Empty state after search
                state.isEmpty -> {
                    EmptyView(
                        title = if (state.searchQuery.isNotBlank()) {
                            "No results found"
                        } else {
                            "No items yet"
                        },
                        description = if (state.searchQuery.isNotBlank()) {
                            "Try a different search term"
                        } else {
                            "Add your first item to get started"
                        }
                    )
                }
                
                // Items list
                else -> {
                    LazyColumn(
                        state = listState,
                        contentPadding = PaddingValues(AppTheme.dimensions.spacingMd),
                        verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.spacingSm)
                    ) {
                        items(
                            items = state.filteredItems,
                            key = { it.id }
                        ) { item ->
                            SampleItemCard(
                                item = item,
                                onClick = { onEvent(SampleEvent.ItemClicked(item)) }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun SampleItemCard(
    item: SampleItem,
    onClick: () -> Unit
) {
    AppCard(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(AppTheme.dimensions.spacingLg)
        ) {
            TitleText(text = item.title)
            BodyText(
                text = item.description,
                color = AppTheme.colors.textSecondary,
                maxLines = 2
            )
        }
    }
}
