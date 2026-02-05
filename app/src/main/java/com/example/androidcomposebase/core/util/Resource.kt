package com.example.androidcomposebase.core.util

/**
 * A sealed class that represents the result of an operation.
 * 
 * This is a wrapper for data that can be in one of three states:
 * - [Success]: Operation completed successfully with data
 * - [Error]: Operation failed with an error message
 * - [Loading]: Operation is in progress
 * 
 * Example usage:
 * ```
 * when (result) {
 *     is Resource.Success -> handleSuccess(result.data)
 *     is Resource.Error -> handleError(result.message)
 *     is Resource.Loading -> showLoading()
 * }
 * ```
 * 
 * @param T The type of data this Resource holds
 */
sealed class Resource<out T> {
    
    /**
     * Represents a successful operation with data.
     * 
     * @param data The data returned from the operation
     */
    data class Success<T>(val data: T) : Resource<T>()
    
    /**
     * Represents a failed operation.
     * 
     * @param message The error message describing what went wrong
     * @param data Optional data that might still be available (e.g., from cache)
     */
    data class Error<T>(
        val message: String,
        val data: T? = null
    ) : Resource<T>()
    
    /**
     * Represents an ongoing operation.
     * 
     * @param data Optional data that might be available while loading (e.g., from cache)
     */
    data class Loading<T>(val data: T? = null) : Resource<T>()
    
    /**
     * Returns true if this Resource is a Success.
     */
    val isSuccess: Boolean
        get() = this is Success
    
    /**
     * Returns true if this Resource is an Error.
     */
    val isError: Boolean
        get() = this is Error
    
    /**
     * Returns true if this Resource is Loading.
     */
    val isLoading: Boolean
        get() = this is Loading
    
    /**
     * Returns the data if this is a Success, null otherwise.
     */
    fun getOrNull(): T? = when (this) {
        is Success -> data
        is Error -> data
        is Loading -> data
    }
    
    /**
     * Maps the data to a different type.
     * 
     * @param transform The transformation function
     * @return A new Resource with the transformed data
     */
    inline fun <R> map(transform: (T) -> R): Resource<R> = when (this) {
        is Success -> Success(transform(data))
        is Error -> Error(message, data?.let(transform))
        is Loading -> Loading(data?.let(transform))
    }
}

/**
 * Extension function to convert any value to a Success Resource.
 */
fun <T> T.toSuccess(): Resource<T> = Resource.Success(this)

/**
 * Extension function to create an Error Resource.
 */
fun <T> String.toError(data: T? = null): Resource<T> = Resource.Error(this, data)
