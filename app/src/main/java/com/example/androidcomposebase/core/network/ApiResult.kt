package com.example.androidcomposebase.core.network

/**
 * Represents the result of a network operation.
 * 
 * Unlike [Resource] which is for UI state, [ApiResult] is specifically
 * for network layer operations and can be easily mapped to [Resource].
 * 
 * @param T The type of data returned on success
 */
sealed interface ApiResult<out T> {
    
    /**
     * Successful network response with data.
     */
    data class Success<T>(val data: T) : ApiResult<T>
    
    /**
     * Network error with message and optional error code.
     */
    data class Error(
        val message: String,
        val code: Int? = null,
        val exception: Throwable? = null
    ) : ApiResult<Nothing>
}

/**
 * Executes a suspending network call safely and wraps the result.
 * 
 * Example:
 * ```
 * suspend fun getUsers(): ApiResult<List<User>> = safeApiCall {
 *     api.getUsers()
 * }
 * ```
 */
suspend inline fun <T> safeApiCall(
    crossinline call: suspend () -> T
): ApiResult<T> {
    return try {
        ApiResult.Success(call())
    } catch (e: retrofit2.HttpException) {
        ApiResult.Error(
            message = e.message() ?: "HTTP Error",
            code = e.code(),
            exception = e
        )
    } catch (e: java.io.IOException) {
        ApiResult.Error(
            message = "Network error. Please check your connection.",
            exception = e
        )
    } catch (e: Exception) {
        ApiResult.Error(
            message = e.message ?: "Unknown error occurred",
            exception = e
        )
    }
}

/**
 * Maps ApiResult to Resource for UI consumption.
 */
fun <T> ApiResult<T>.toResource(): com.example.androidcomposebase.core.util.Resource<T> {
    return when (this) {
        is ApiResult.Success -> com.example.androidcomposebase.core.util.Resource.Success(data)
        is ApiResult.Error -> com.example.androidcomposebase.core.util.Resource.Error(message)
    }
}

/**
 * Maps the success data to a different type.
 */
inline fun <T, R> ApiResult<T>.map(transform: (T) -> R): ApiResult<R> {
    return when (this) {
        is ApiResult.Success -> ApiResult.Success(transform(data))
        is ApiResult.Error -> this
    }
}
