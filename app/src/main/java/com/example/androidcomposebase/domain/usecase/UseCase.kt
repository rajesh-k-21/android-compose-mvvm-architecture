package com.example.androidcomposebase.domain.usecase

/**
 * Base UseCase class that provides a standardized interface for
 * executing business logic operations.
 * 
 * Use cases encapsulate a single piece of business logic and
 * can be easily tested in isolation. They serve as the primary
 * entry point from ViewModels into the domain layer.
 * 
 * @param P The input parameter type
 * @param R The result type
 * 
 * Example usage:
 * ```
 * class GetUserByIdUseCase @Inject constructor(
 *     private val repository: UserRepository
 * ) : UseCase<Int, Flow<Resource<User>>>() {
 *     override suspend fun execute(params: Int): Flow<Resource<User>> {
 *         return repository.getUserById(params)
 *     }
 * }
 * ```
 */
abstract class UseCase<in P, out R> {
    
    /**
     * Executes the use case with the given parameters.
     * 
     * @param params The input parameters
     * @return The result of the operation
     */
    protected abstract suspend fun execute(params: P): R
    
    /**
     * Operator function that allows calling the use case like a function.
     * 
     * Example:
     * ```
     * val result = getUsersUseCase(Unit)
     * ```
     */
    suspend operator fun invoke(params: P): R = execute(params)
}

/**
 * Base UseCase class for operations that don't require input parameters.
 * 
 * @param R The result type
 * 
 * Example usage:
 * ```
 * class GetAllUsersUseCase @Inject constructor(
 *     private val repository: UserRepository
 * ) : NoParamsUseCase<Flow<Resource<List<User>>>>() {
 *     override suspend fun execute(): Flow<Resource<List<User>>> {
 *         return repository.getUsers()
 *     }
 * }
 * ```
 */
abstract class NoParamsUseCase<out R> {
    
    /**
     * Executes the use case without parameters.
     * 
     * @return The result of the operation
     */
    protected abstract suspend fun execute(): R
    
    /**
     * Operator function that allows calling the use case like a function.
     * 
     * Example:
     * ```
     * val result = getUsersUseCase()
     * ```
     */
    suspend operator fun invoke(): R = execute()
}
