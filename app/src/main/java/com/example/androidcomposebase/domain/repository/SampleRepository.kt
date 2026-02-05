package com.example.androidcomposebase.domain.repository

import com.example.androidcomposebase.core.util.Resource
import com.example.androidcomposebase.feature.sample.SampleItem

/**
 * Sample Repository Interface
 * 
 * Domain layer repository interface.
 * Implemented in data layer.
 */
interface SampleRepository {
    
    /**
     * Get all sample items.
     */
    suspend fun getSampleItems(): Resource<List<SampleItem>>
    
    /**
     * Get a single sample item by ID.
     */
    suspend fun getSampleItemById(id: String): Resource<SampleItem>
    
    /**
     * Delete a sample item.
     */
    suspend fun deleteSampleItem(id: String): Resource<Unit>
}
