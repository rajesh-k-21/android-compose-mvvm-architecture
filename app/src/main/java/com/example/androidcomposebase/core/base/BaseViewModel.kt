package com.example.androidcomposebase.core.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * Base ViewModel class that provides common functionality for all ViewModels
 * in the application.
 * 
 * Features:
 * - StateFlow-based state management for lifecycle-aware UI updates
 * - Channel-based effect handling for one-time events (navigation, snackbars, etc.)
 * - Type-safe event handling with sealed classes
 * - Thread-safe state updates
 * 
 * @param State The UI state type that extends [BaseUiState]
 * @param Event The UI event type that extends [BaseUiEvent]
 * @param Effect The UI effect type that extends [BaseUiEffect]
 * @param initialState The initial state of the UI
 * 
 * Example usage:
 * ```
 * @HiltViewModel
 * class UserListViewModel @Inject constructor(
 *     private val getUsersUseCase: GetUsersUseCase
 * ) : BaseViewModel<UserListUiState, UserListUiEvent, UserListUiEffect>(
 *     initialState = UserListUiState()
 * ) {
 *     override fun onEvent(event: UserListUiEvent) {
 *         when (event) {
 *             is UserListUiEvent.LoadUsers -> loadUsers()
 *             is UserListUiEvent.UserClicked -> navigateToUser(event.userId)
 *         }
 *     }
 * }
 * ```
 */
abstract class BaseViewModel<State : BaseUiState, Event : BaseUiEvent, Effect : BaseUiEffect>(
    initialState: State
) : ViewModel() {

    /**
     * Private mutable state flow for internal state updates.
     */
    private val _uiState = MutableStateFlow(initialState)
    
    /**
     * Public immutable state flow for UI observation.
     * Collect this in Compose using [collectAsStateWithLifecycle].
     */
    val uiState: StateFlow<State> = _uiState.asStateFlow()

    /**
     * Channel for one-time effects like navigation or showing snackbars.
     * Uses BUFFERED capacity to prevent dropping events.
     */
    private val _effect = Channel<Effect>(Channel.BUFFERED)
    
    /**
     * Public flow for UI effect observation.
     * Collect this in [LaunchedEffect] in Compose.
     */
    val effect: Flow<Effect> = _effect.receiveAsFlow()

    /**
     * Provides direct access to the current state value.
     * Useful for checking current state within ViewModel methods.
     */
    protected val currentState: State
        get() = _uiState.value

    /**
     * Updates the UI state using a reducer function.
     * This ensures thread-safe state updates.
     * 
     * @param reducer A function that takes the current state and returns a new state
     * 
     * Example:
     * ```
     * updateState { copy(isLoading = true) }
     * ```
     */
    protected fun updateState(reducer: State.() -> State) {
        _uiState.update { it.reducer() }
    }

    /**
     * Sends a one-time effect to the UI.
     * Effects are consumed only once and are not persisted.
     * 
     * @param effectValue The effect to send
     * 
     * Example:
     * ```
     * sendEffect(UserListUiEffect.NavigateToUserDetail(userId))
     * ```
     */
    protected fun sendEffect(effectValue: Effect) {
        viewModelScope.launch {
            _effect.send(effectValue)
        }
    }

    /**
     * Handles incoming UI events.
     * Override this method to process events specific to your feature.
     * 
     * @param event The event to process
     */
    abstract fun onEvent(event: Event)
}
