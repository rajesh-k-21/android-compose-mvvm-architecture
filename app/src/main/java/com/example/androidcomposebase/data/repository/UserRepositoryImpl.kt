package com.example.androidcomposebase.data.repository

import com.example.androidcomposebase.core.util.Resource
import com.example.androidcomposebase.data.source.FakeUserDataSource
import com.example.androidcomposebase.domain.model.User
import com.example.androidcomposebase.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Implementation of [UserRepository] that fetches user data from the data source.
 * 
 * This implementation wraps all data operations in [Resource] to provide
 * consistent loading, success, and error states to the domain layer.
 * 
 * In a real application, this would coordinate between remote API calls
 * and local database caching (offline-first architecture).
 * 
 * @param dataSource The data source to fetch users from
 */
@Singleton
class UserRepositoryImpl @Inject constructor(
    private val dataSource: FakeUserDataSource
) : UserRepository {
    
    /**
     * Retrieves all users from the data source.
     * 
     * Emits [Resource.Loading] first, then [Resource.Success] or [Resource.Error].
     */
    override fun getUsers(): Flow<Resource<List<User>>> = flow {
        emit(Resource.Loading())
        
        try {
            val users = dataSource.getUsers()
            emit(Resource.Success(users))
        } catch (e: Exception) {
            emit(Resource.Error(
                message = e.message ?: "An unexpected error occurred",
                data = null
            ))
        }
    }
    
    /**
     * Retrieves a specific user by their ID.
     * 
     * Emits [Resource.Loading] first, then [Resource.Success] or [Resource.Error].
     */
    override fun getUserById(userId: Int): Flow<Resource<User>> = flow {
        emit(Resource.Loading())
        
        try {
            val user = dataSource.getUserById(userId)
            if (user != null) {
                emit(Resource.Success(user))
            } else {
                emit(Resource.Error(
                    message = "User not found",
                    data = null
                ))
            }
        } catch (e: Exception) {
            emit(Resource.Error(
                message = e.message ?: "An unexpected error occurred",
                data = null
            ))
        }
    }
    
    /**
     * Refreshes user data from the remote source.
     * 
     * Emits [Resource.Loading] first, then [Resource.Success] or [Resource.Error].
     */
    override fun refreshUsers(): Flow<Resource<List<User>>> = flow {
        emit(Resource.Loading())
        
        try {
            val users = dataSource.refreshUsers()
            emit(Resource.Success(users))
        } catch (e: Exception) {
            emit(Resource.Error(
                message = e.message ?: "Failed to refresh users",
                data = null
            ))
        }
    }
}
