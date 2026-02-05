package com.example.androidcomposebase.core.base

/**
 * Base interface for all UI effects (side effects) in the application.
 * 
 * UI effects represent one-time events that should be consumed by the UI,
 * such as navigation, showing snackbars, or triggering dialogs.
 * Unlike UI state, effects are not persisted and are consumed only once.
 * 
 * Example usage:
 * ```
 * sealed class LoginUiEffect : BaseUiEffect {
 *     data object NavigateToHome : LoginUiEffect()
 *     data class ShowSnackbar(val message: String) : LoginUiEffect()
 *     data class ShowError(val error: String) : LoginUiEffect()
 * }
 * ```
 */
interface BaseUiEffect
