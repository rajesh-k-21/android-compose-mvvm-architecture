package com.example.androidcomposebase.domain.usecase

import com.example.androidcomposebase.core.util.Resource
import com.example.androidcomposebase.domain.model.User
import com.example.androidcomposebase.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Use case for retrieving a specific user by their ID.
 * 
 * @param userRepository The repository to fetch user from
 */
class GetUserByIdUseCase @Inject constructor(
    private val userRepository: UserRepository
) : UseCase<Int, Flow<Resource<User>>>() {
    
    /**
     * Executes the use case to retrieve a user by ID.
     * 
     * @param params The user ID to fetch
     * @return A [Flow] emitting [Resource] with the user or error
     */
    override suspend fun execute(params: Int): Flow<Resource<User>> {
        return userRepository.getUserById(params)
    }
}
