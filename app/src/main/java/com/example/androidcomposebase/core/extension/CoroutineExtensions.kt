package com.example.androidcomposebase.core.extension

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * Launches a coroutine with IO dispatcher.
 * 
 * Example:
 * ```
 * viewModelScope.launchIO {
 *     val result = repository.fetchData()
 *     // Handle result
 * }
 * ```
 */
fun CoroutineScope.launchIO(
    onError: (Throwable) -> Unit = {},
    block: suspend CoroutineScope.() -> Unit
): Job {
    val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError(throwable)
    }
    return launch(Dispatchers.IO + exceptionHandler, block = block)
}

/**
 * Launches a coroutine with Main dispatcher.
 */
fun CoroutineScope.launchMain(
    onError: (Throwable) -> Unit = {},
    block: suspend CoroutineScope.() -> Unit
): Job {
    val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError(throwable)
    }
    return launch(Dispatchers.Main + exceptionHandler, block = block)
}

/**
 * Switches to IO context for the block execution.
 * 
 * Example:
 * ```
 * val result = withIO {
 *     heavyComputation()
 * }
 * ```
 */
suspend fun <T> withIO(block: suspend CoroutineScope.() -> T): T {
    return withContext(Dispatchers.IO, block)
}

/**
 * Switches to Main context for UI updates.
 */
suspend fun <T> withMain(block: suspend CoroutineScope.() -> T): T {
    return withContext(Dispatchers.Main, block)
}

/**
 * Safe launch extension for ViewModel that handles errors.
 * 
 * Example:
 * ```
 * safeLaunch(
 *     onError = { updateState { copy(error = it.message) } }
 * ) {
 *     val data = repository.getData()
 *     updateState { copy(data = data) }
 * }
 * ```
 */
fun ViewModel.safeLaunch(
    dispatcher: CoroutineDispatcher = Dispatchers.IO,
    onError: (Throwable) -> Unit = {},
    block: suspend CoroutineScope.() -> Unit
): Job {
    val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError(throwable)
    }
    return viewModelScope.launch(dispatcher + exceptionHandler, block = block)
}
