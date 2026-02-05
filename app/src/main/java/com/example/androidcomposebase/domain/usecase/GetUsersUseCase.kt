package com.example.androidcomposebase.domain.usecase

import com.example.androidcomposebase.core.util.Resource
import com.example.androidcomposebase.domain.model.User
import com.example.androidcomposebase.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Use case for retrieving a list of all users.
 * 
 * This use case encapsulates the business logic for fetching users.
 * It acts as an intermediary between the ViewModel and the Repository,
 * allowing for additional business rules to be applied if needed.
 * 
 * @param userRepository The repository to fetch users from
 */
class GetUsersUseCase @Inject constructor(
    private val userRepository: UserRepository
) : NoParamsUseCase<Flow<Resource<List<User>>>>() {
    
    /**
     * Executes the use case to retrieve all users.
     * 
     * @return A [Flow] emitting [Resource] with the list of users
     */
    override suspend fun execute(): Flow<Resource<List<User>>> {
        return userRepository.getUsers()
    }
}
