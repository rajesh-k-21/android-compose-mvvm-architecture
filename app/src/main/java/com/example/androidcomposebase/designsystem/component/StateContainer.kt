package com.example.androidcomposebase.designsystem.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.androidcomposebase.core.util.Resource
import com.example.androidcomposebase.designsystem.progress.FullScreenLoading

/**
 * State Container
 * 
 * A wrapper component that handles Loading/Error/Empty/Content states.
 * Simplifies state management in screens.
 * 
 * @param state The current Resource state
 * @param modifier Modifier to be applied
 * @param onRetry Retry action for error state
 * @param isEmpty Lambda to check if content is empty
 * @param emptyTitle Title for empty state
 * @param emptyDescription Description for empty state
 * @param loadingMessage Optional loading message
 * @param content Content to display on success
 */
@Composable
fun <T> StateContainer(
    state: Resource<T>,
    modifier: Modifier = Modifier,
    onRetry: (() -> Unit)? = null,
    isEmpty: ((T) -> Boolean)? = null,
    emptyTitle: String = "No data available",
    emptyDescription: String? = null,
    loadingMessage: String? = null,
    content: @Composable (T) -> Unit
) {
    Box(modifier = modifier.fillMaxSize()) {
        when (state) {
            is Resource.Loading -> {
                FullScreenLoading(
                    message = loadingMessage
                )
            }
            
            is Resource.Error -> {
                ErrorView(
                    message = state.message,
                    onRetry = onRetry
                )
            }
            
            is Resource.Success -> {
                val data = state.data
                if (isEmpty != null && isEmpty(data)) {
                    EmptyView(
                        title = emptyTitle,
                        description = emptyDescription
                    )
                } else {
                    content(data)
                }
            }
        }
    }
}

/**
 * Simple State Container
 * 
 * A simplified version for basic loading/content/error states.
 * 
 * @param isLoading Whether content is loading
 * @param error Error message (null if no error)
 * @param modifier Modifier to be applied
 * @param onRetry Retry action
 * @param content Content to display
 */
@Composable
fun SimpleStateContainer(
    isLoading: Boolean,
    error: String?,
    modifier: Modifier = Modifier,
    onRetry: (() -> Unit)? = null,
    content: @Composable () -> Unit
) {
    Box(modifier = modifier.fillMaxSize()) {
        when {
            isLoading -> {
                FullScreenLoading()
            }
            
            error != null -> {
                ErrorView(
                    message = error,
                    onRetry = onRetry
                )
            }
            
            else -> {
                content()
            }
        }
    }
}
