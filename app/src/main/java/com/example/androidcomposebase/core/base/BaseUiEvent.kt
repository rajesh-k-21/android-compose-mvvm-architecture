package com.example.androidcomposebase.core.base

/**
 * Base interface for all UI events in the application.
 * 
 * UI events represent user interactions or actions that should be
 * processed by the ViewModel. They follow the unidirectional data
 * flow pattern (MVI-style).
 * 
 * Example usage:
 * ```
 * sealed class LoginUiEvent : BaseUiEvent {
 *     data class EmailChanged(val email: String) : LoginUiEvent()
 *     data class PasswordChanged(val password: String) : LoginUiEvent()
 *     data object LoginClicked : LoginUiEvent()
 * }
 * ```
 */
interface BaseUiEvent
