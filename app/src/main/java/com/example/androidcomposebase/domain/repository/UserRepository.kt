package com.example.androidcomposebase.domain.repository

import com.example.androidcomposebase.core.util.Resource
import com.example.androidcomposebase.domain.model.User
import kotlinx.coroutines.flow.Flow

/**
 * Repository interface for User data operations.
 * 
 * This interface defines the contract for user data access,
 * abstracting the data sources from the domain layer.
 * The implementation details (API, database, cache) are hidden
 * behind this interface, allowing for easy testing and flexibility.
 * 
 * All methods return [Flow] to support reactive streams and
 * allow for loading/error state emissions.
 */
interface UserRepository {
    
    /**
     * Retrieves a list of all users.
     * 
     * @return A [Flow] emitting [Resource] with list of users
     */
    fun getUsers(): Flow<Resource<List<User>>>
    
    /**
     * Retrieves a specific user by their ID.
     * 
     * @param userId The unique identifier of the user
     * @return A [Flow] emitting [Resource] with the user or error
     */
    fun getUserById(userId: Int): Flow<Resource<User>>
    
    /**
     * Refreshes the user data from the remote source.
     * 
     * @return A [Flow] emitting [Resource] with refreshed list of users
     */
    fun refreshUsers(): Flow<Resource<List<User>>>
}
