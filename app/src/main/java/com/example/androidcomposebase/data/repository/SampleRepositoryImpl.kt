package com.example.androidcomposebase.data.repository

import com.example.androidcomposebase.core.util.Resource
import com.example.androidcomposebase.data.datasource.SampleDataSource
import com.example.androidcomposebase.domain.repository.SampleRepository
import com.example.androidcomposebase.feature.sample.SampleItem
import javax.inject.Inject

/**
 * Sample Repository Implementation
 * 
 * Data layer implementation of the domain repository.
 * Handles data source coordination.
 */
class SampleRepositoryImpl @Inject constructor(
    private val dataSource: SampleDataSource
) : SampleRepository {
    
    override suspend fun getSampleItems(): Resource<List<SampleItem>> {
        return dataSource.getSampleItems()
    }
    
    override suspend fun getSampleItemById(id: String): Resource<SampleItem> {
        return dataSource.getSampleItemById(id)
    }
    
    override suspend fun deleteSampleItem(id: String): Resource<Unit> {
        return dataSource.deleteSampleItem(id)
    }
}
