package com.example.androidcomposebase.feature.sample

import com.example.androidcomposebase.core.base.BaseUiState

/**
 * Sample Screen UI State
 * 
 * Demonstrates a typical UI state with:
 * - Loading state
 * - Error handling
 * - Data list
 * - Search/filter functionality
 * - Pagination readiness
 */
data class SampleState(
    override val isLoading: Boolean = false,
    override val error: String? = null,
    val items: List<SampleItem> = emptyList(),
    val searchQuery: String = "",
    val selectedItem: SampleItem? = null,
    val isRefreshing: Boolean = false
) : BaseUiState {
    
    /**
     * Computed property: filtered items based on search query.
     */
    val filteredItems: List<SampleItem>
        get() = if (searchQuery.isBlank()) {
            items
        } else {
            items.filter { 
                it.title.contains(searchQuery, ignoreCase = true) ||
                it.description.contains(searchQuery, ignoreCase = true)
            }
        }
    
    /**
     * Computed property: whether the list is empty after filtering.
     */
    val isEmpty: Boolean
        get() = filteredItems.isEmpty() && !isLoading && error == null
    
    /**
     * Computed property: whether we have data to display.
     */
    val hasData: Boolean
        get() = items.isNotEmpty()
}

/**
 * Sample Item
 * 
 * A data class representing a single item in the list.
 */
data class SampleItem(
    val id: String,
    val title: String,
    val description: String,
    val imageUrl: String? = null,
    val createdAt: Long = System.currentTimeMillis()
)
