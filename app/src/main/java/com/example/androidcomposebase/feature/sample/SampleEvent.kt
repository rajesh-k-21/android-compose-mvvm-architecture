package com.example.androidcomposebase.feature.sample

import com.example.androidcomposebase.core.base.BaseUiEvent

/**
 * Sample Screen UI Events
 * 
 * Represents all possible user actions on the Sample screen.
 * Events flow: UI → ViewModel → State changes
 */
sealed interface SampleEvent : BaseUiEvent {
    
    /**
     * Load initial data.
     */
    data object LoadData : SampleEvent
    
    /**
     * Refresh data (pull-to-refresh).
     */
    data object Refresh : SampleEvent
    
    /**
     * Retry after error.
     */
    data object Retry : SampleEvent
    
    /**
     * Search query changed.
     */
    data class SearchQueryChanged(val query: String) : SampleEvent
    
    /**
     * Item clicked.
     */
    data class ItemClicked(val item: SampleItem) : SampleEvent
    
    /**
     * Delete item.
     */
    data class DeleteItem(val itemId: String) : SampleEvent
    
    /**
     * Clear error state.
     */
    data object ClearError : SampleEvent
}
