package com.example.androidcomposebase.feature.sample

import com.example.androidcomposebase.core.base.BaseUiEffect

/**
 * Sample Screen UI Effects
 * 
 * One-time events that should be consumed by the UI.
 * Effects flow: ViewModel → UI (consumed once)
 * 
 * Use effects for:
 * - Navigation
 * - Showing snackbars/toasts
 * - One-time UI actions
 */
sealed interface SampleEffect : BaseUiEffect {
    
    /**
     * Navigate to item detail screen.
     */
    data class NavigateToDetail(val itemId: String) : SampleEffect
    
    /**
     * Show success snackbar.
     */
    data class ShowSuccessMessage(val message: String) : SampleEffect
    
    /**
     * Show error snackbar.
     */
    data class ShowErrorMessage(val message: String) : SampleEffect
    
    /**
     * Navigate back.
     */
    data object NavigateBack : SampleEffect
    
    /**
     * Scroll to top of list.
     */
    data object ScrollToTop : SampleEffect
}
