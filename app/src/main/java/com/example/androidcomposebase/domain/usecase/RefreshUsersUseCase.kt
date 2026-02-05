package com.example.androidcomposebase.domain.usecase

import com.example.androidcomposebase.core.util.Resource
import com.example.androidcomposebase.domain.model.User
import com.example.androidcomposebase.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Use case for refreshing user data from remote source.
 * 
 * @param userRepository The repository to refresh users from
 */
class RefreshUsersUseCase @Inject constructor(
    private val userRepository: UserRepository
) : NoParamsUseCase<Flow<Resource<List<User>>>>() {
    
    /**
     * Executes the use case to refresh all users.
     * 
     * @return A [Flow] emitting [Resource] with the refreshed list of users
     */
    override suspend fun execute(): Flow<Resource<List<User>>> {
        return userRepository.refreshUsers()
    }
}
