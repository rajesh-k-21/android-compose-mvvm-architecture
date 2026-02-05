package com.example.androidcomposebase.di

import com.example.androidcomposebase.data.datasource.FakeSampleDataSource
import com.example.androidcomposebase.data.datasource.SampleDataSource
import com.example.androidcomposebase.data.repository.SampleRepositoryImpl
import com.example.androidcomposebase.data.repository.UserRepositoryImpl
import com.example.androidcomposebase.domain.repository.SampleRepository
import com.example.androidcomposebase.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Hilt module for providing Repository dependencies.
 * 
 * This module binds repository interfaces to their implementations,
 * following the Dependency Inversion Principle (DIP).
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    
    @Binds
    @Singleton
    abstract fun bindUserRepository(
        userRepositoryImpl: UserRepositoryImpl
    ): UserRepository
    
    @Binds
    @Singleton
    abstract fun bindSampleRepository(
        sampleRepositoryImpl: SampleRepositoryImpl
    ): SampleRepository
    
    @Binds
    @Singleton
    abstract fun bindSampleDataSource(
        fakeSampleDataSource: FakeSampleDataSource
    ): SampleDataSource
}
