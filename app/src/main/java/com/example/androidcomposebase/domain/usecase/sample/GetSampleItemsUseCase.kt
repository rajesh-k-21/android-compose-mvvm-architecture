package com.example.androidcomposebase.domain.usecase.sample

import com.example.androidcomposebase.core.util.Resource
import com.example.androidcomposebase.domain.repository.SampleRepository
import com.example.androidcomposebase.feature.sample.SampleItem
import javax.inject.Inject

/**
 * Get Sample Items Use Case
 * 
 * Demonstrates a typical use case that:
 * - Fetches data from repository
 * - Returns Resource wrapper
 * - Can add business logic/validation
 */
class GetSampleItemsUseCase @Inject constructor(
    private val repository: SampleRepository
) {
    suspend operator fun invoke(): Resource<List<SampleItem>> {
        return repository.getSampleItems()
    }
}
