package com.example.androidcomposebase.core.base

/**
 * Base interface for all UI states in the application.
 * 
 * Every feature's UI state should implement this interface to ensure
 * consistent handling of loading and error states across the app.
 */
interface BaseUiState {
    /**
     * Indicates whether a loading operation is in progress.
     */
    val isLoading: Boolean
    
    /**
     * Contains error message if an error occurred, null otherwise.
     */
    val error: String?
}
