package com.example.androidcomposebase.feature.sample

import androidx.lifecycle.viewModelScope
import com.example.androidcomposebase.core.base.BaseViewModel
import com.example.androidcomposebase.core.util.Resource
import com.example.androidcomposebase.domain.usecase.sample.GetSampleItemsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Sample ViewModel
 * 
 * Demonstrates typical ViewModel patterns:
 * - Loading data on init
 * - Error handling
 * - Search/filter
 * - Pull-to-refresh
 * - Item actions
 * - Effects for navigation/snackbars
 */
@HiltViewModel
class SampleViewModel @Inject constructor(
    private val getSampleItemsUseCase: GetSampleItemsUseCase
) : BaseViewModel<SampleState, SampleEvent, SampleEffect>(
    initialState = SampleState()
) {
    
    init {
        // Load data when ViewModel is created
        onEvent(SampleEvent.LoadData)
    }
    
    override fun onEvent(event: SampleEvent) {
        when (event) {
            is SampleEvent.LoadData -> loadData()
            is SampleEvent.Refresh -> refresh()
            is SampleEvent.Retry -> retry()
            is SampleEvent.SearchQueryChanged -> onSearchQueryChanged(event.query)
            is SampleEvent.ItemClicked -> onItemClicked(event.item)
            is SampleEvent.DeleteItem -> deleteItem(event.itemId)
            is SampleEvent.ClearError -> clearError()
        }
    }
    
    /**
     * Load initial data.
     */
    private fun loadData() {
        viewModelScope.launch {
            updateState { copy(isLoading = true, error = null) }
            
            when (val result = getSampleItemsUseCase()) {
                is Resource.Success -> {
                    updateState { 
                        copy(
                            isLoading = false,
                            items = result.data,
                            error = null
                        )
                    }
                }
                is Resource.Error -> {
                    updateState { 
                        copy(
                            isLoading = false,
                            error = result.message
                        )
                    }
                }
                is Resource.Loading -> {
                    // Already handled above
                }
            }
        }
    }
    
    /**
     * Refresh data (pull-to-refresh).
     */
    private fun refresh() {
        viewModelScope.launch {
            updateState { copy(isRefreshing = true, error = null) }
            
            // Simulate network delay
            delay(500)
            
            when (val result = getSampleItemsUseCase()) {
                is Resource.Success -> {
                    updateState { 
                        copy(
                            isRefreshing = false,
                            items = result.data,
                            error = null
                        )
                    }
                    sendEffect(SampleEffect.ScrollToTop)
                }
                is Resource.Error -> {
                    updateState { copy(isRefreshing = false) }
                    sendEffect(SampleEffect.ShowErrorMessage(result.message))
                }
                is Resource.Loading -> {}
            }
        }
    }
    
    /**
     * Retry loading after error.
     */
    private fun retry() {
        loadData()
    }
    
    /**
     * Handle search query changes.
     */
    private fun onSearchQueryChanged(query: String) {
        updateState { copy(searchQuery = query) }
    }
    
    /**
     * Handle item click.
     */
    private fun onItemClicked(item: SampleItem) {
        updateState { copy(selectedItem = item) }
        sendEffect(SampleEffect.NavigateToDetail(item.id))
    }
    
    /**
     * Delete an item.
     */
    private fun deleteItem(itemId: String) {
        viewModelScope.launch {
            updateState { 
                copy(items = items.filterNot { it.id == itemId })
            }
            sendEffect(SampleEffect.ShowSuccessMessage("Item deleted"))
        }
    }
    
    /**
     * Clear error state.
     */
    private fun clearError() {
        updateState { copy(error = null) }
    }
}
