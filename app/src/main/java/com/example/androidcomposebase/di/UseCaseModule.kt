package com.example.androidcomposebase.di

import com.example.androidcomposebase.domain.repository.UserRepository
import com.example.androidcomposebase.domain.usecase.GetUserByIdUseCase
import com.example.androidcomposebase.domain.usecase.GetUsersUseCase
import com.example.androidcomposebase.domain.usecase.RefreshUsersUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

/**
 * Hilt module for providing UseCase dependencies.
 * 
 * Use cases are scoped to [ViewModelComponent] to ensure they are
 * created per ViewModel instance and cleaned up with the ViewModel.
 * 
 * Note: Since use cases in this project use constructor injection
 * with @Inject, this module could be simplified. However, it's kept
 * here to demonstrate explicit provision and for cases where more
 * complex instantiation logic might be needed.
 */
@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {
    
    /**
     * Provides [GetUsersUseCase] for fetching all users.
     */
    @Provides
    @ViewModelScoped
    fun provideGetUsersUseCase(
        userRepository: UserRepository
    ): GetUsersUseCase {
        return GetUsersUseCase(userRepository)
    }
    
    /**
     * Provides [GetUserByIdUseCase] for fetching a specific user.
     */
    @Provides
    @ViewModelScoped
    fun provideGetUserByIdUseCase(
        userRepository: UserRepository
    ): GetUserByIdUseCase {
        return GetUserByIdUseCase(userRepository)
    }
    
    /**
     * Provides [RefreshUsersUseCase] for refreshing user data.
     */
    @Provides
    @ViewModelScoped
    fun provideRefreshUsersUseCase(
        userRepository: UserRepository
    ): RefreshUsersUseCase {
        return RefreshUsersUseCase(userRepository)
    }
}
