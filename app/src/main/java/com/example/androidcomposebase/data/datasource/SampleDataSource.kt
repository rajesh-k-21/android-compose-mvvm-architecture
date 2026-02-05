package com.example.androidcomposebase.data.datasource

import com.example.androidcomposebase.core.util.Resource
import com.example.androidcomposebase.feature.sample.SampleItem
import kotlinx.coroutines.delay
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Sample Data Source Interface
 */
interface SampleDataSource {
    suspend fun getSampleItems(): Resource<List<SampleItem>>
    suspend fun getSampleItemById(id: String): Resource<SampleItem>
    suspend fun deleteSampleItem(id: String): Resource<Unit>
}

/**
 * Fake Sample Data Source
 * 
 * A fake implementation for development/testing.
 * Replace with real API implementation when ready.
 */
@Singleton
class FakeSampleDataSource @Inject constructor() : SampleDataSource {
    
    private val items = mutableListOf(
        SampleItem(
            id = "1",
            title = "Getting Started",
            description = "Learn how to use this sample feature as a template for your own features."
        ),
        SampleItem(
            id = "2",
            title = "Architecture Patterns",
            description = "Explore MVVM patterns with StateFlow, Events, and Effects."
        ),
        SampleItem(
            id = "3",
            title = "Design System",
            description = "Discover 20+ reusable Material 3 components in the design system."
        ),
        SampleItem(
            id = "4",
            title = "Navigation",
            description = "Type-safe Navigation 3 with serializable route keys."
        ),
        SampleItem(
            id = "5",
            title = "Dependency Injection",
            description = "Hilt-based DI setup with modules for network, repository, and use cases."
        ),
        SampleItem(
            id = "6",
            title = "Network Layer",
            description = "Retrofit + OkHttp + Moshi for API calls with error handling."
        ),
        SampleItem(
            id = "7",
            title = "Error Handling",
            description = "Resource wrapper for Success/Error/Loading states."
        ),
        SampleItem(
            id = "8",
            title = "Extensions",
            description = "Useful extension functions for Flow, Coroutines, and Modifiers."
        )
    )
    
    override suspend fun getSampleItems(): Resource<List<SampleItem>> {
        // Simulate network delay
        delay(1000)
        
        // Uncomment to simulate errors:
        // return Resource.Error("Simulated network error")
        
        return Resource.Success(items.toList())
    }
    
    override suspend fun getSampleItemById(id: String): Resource<SampleItem> {
        delay(500)
        val item = items.find { it.id == id }
        return if (item != null) {
            Resource.Success(item)
        } else {
            Resource.Error("Item not found")
        }
    }
    
    override suspend fun deleteSampleItem(id: String): Resource<Unit> {
        delay(300)
        items.removeAll { it.id == id }
        return Resource.Success(Unit)
    }
}
