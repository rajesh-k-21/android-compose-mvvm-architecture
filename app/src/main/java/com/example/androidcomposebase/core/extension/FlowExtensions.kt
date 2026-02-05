package com.example.androidcomposebase.core.extension

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flow

/**
 * Collects a Flow in a lifecycle-aware manner.
 * 
 * Example:
 * ```
 * viewModel.effect.collectWithLifecycle { effect ->
 *     when (effect) {
 *         is Effect.Navigate -> navigator.navigate(effect.route)
 *         is Effect.ShowSnackbar -> snackbarHostState.showSnackbar(effect.message)
 *     }
 * }
 * ```
 */
@Composable
fun <T> Flow<T>.collectWithLifecycle(
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
    minActiveState: Lifecycle.State = Lifecycle.State.STARTED,
    action: suspend (T) -> Unit
) {
    LaunchedEffect(this, lifecycleOwner) {
        lifecycleOwner.lifecycle.repeatOnLifecycle(minActiveState) {
            collect { action(it) }
        }
    }
}

/**
 * Throttles a flow, emitting only the first value
 * in each time window.
 * 
 * Useful for preventing rapid button clicks.
 * 
 * @param windowDuration The time window in milliseconds
 */
fun <T> Flow<T>.throttleFirst(windowDuration: Long): Flow<T> = flow {
    var lastEmissionTime = 0L
    collect { value ->
        val currentTime = System.currentTimeMillis()
        if (currentTime - lastEmissionTime >= windowDuration) {
            lastEmissionTime = currentTime
            emit(value)
        }
    }
}

/**
 * Creates a debounced SharedFlow for search queries.
 * 
 * Example:
 * ```
 * private val searchQuery = MutableSharedFlow<String>()
 * val debouncedQuery = searchQuery.debounceSearch(300)
 * ```
 */
fun MutableSharedFlow<String>.debounceSearch(
    timeoutMillis: Long = 300L
): Flow<String> = this.debounce(timeoutMillis)
