package com.example.androidcomposebase.di

import com.example.androidcomposebase.data.source.FakeUserDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Hilt module for providing DataSource dependencies.
 * 
 * Data sources are typically singletons to avoid creating multiple
 * instances of network clients, database instances, etc.
 * 
 * In a real application, this module would provide:
 * - Retrofit API services
 * - Room Database instances
 * - SharedPreferences / DataStore
 * - Network interceptors
 * 
 * Note: [FakeUserDataSource] uses constructor injection with @Inject,
 * so explicit provision here is optional but kept for demonstration.
 */
@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {
    
    /**
     * Provides [FakeUserDataSource] singleton.
     * 
     * In a real app, this would be replaced with actual data sources:
     * ```kotlin
     * @Provides
     * @Singleton
     * fun provideApiService(retrofit: Retrofit): ApiService {
     *     return retrofit.create(ApiService::class.java)
     * }
     * ```
     */
    @Provides
    @Singleton
    fun provideFakeUserDataSource(): FakeUserDataSource {
        return FakeUserDataSource()
    }
}
