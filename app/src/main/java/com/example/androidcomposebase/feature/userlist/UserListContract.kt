package com.example.androidcomposebase.feature.userlist

import com.example.androidcomposebase.core.base.BaseUiEffect
import com.example.androidcomposebase.core.base.BaseUiEvent
import com.example.androidcomposebase.core.base.BaseUiState
import com.example.androidcomposebase.domain.model.User

/**
 * Contract file defining the State, Events, and Effects for the User List feature.
 * 
 * This follows the MVI (Model-View-Intent) pattern where:
 * - State: Represents the current UI state
 * - Events: User interactions/intents that trigger state changes
 * - Effects: One-time side effects like navigation or snackbars
 */

/**
 * UI State for the User List screen.
 * 
 * @param users The list of users to display
 * @param isLoading Whether data is being loaded
 * @param isRefreshing Whether a pull-to-refresh is in progress
 * @param error Error message if an error occurred
 * @param selectedUser Currently selected user for detail view
 */
data class UserListUiState(
    val users: List<User> = emptyList(),
    override val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    override val error: String? = null,
    val selectedUser: User? = null
) : BaseUiState

/**
 * UI Events for the User List screen.
 * 
 * These represent all possible user interactions on this screen.
 */
sealed class UserListUiEvent : BaseUiEvent {
    /**
     * Event triggered when the screen is first loaded.
     */
    data object LoadUsers : UserListUiEvent()
    
    /**
     * Event triggered when user pulls to refresh.
     */
    data object RefreshUsers : UserListUiEvent()
    
    /**
     * Event triggered when a user item is clicked.
     * 
     * @param user The clicked user
     */
    data class UserClicked(val user: User) : UserListUiEvent()
    
    /**
     * Event triggered when the retry button is clicked after an error.
     */
    data object RetryClicked : UserListUiEvent()
    
    /**
     * Event triggered when the error message is dismissed.
     */
    data object DismissError : UserListUiEvent()
}

/**
 * UI Effects (Side Effects) for the User List screen.
 * 
 * These are one-time events that should be consumed only once.
 */
sealed class UserListUiEffect : BaseUiEffect {
    /**
     * Navigate to user detail screen.
     * 
     * @param userId The ID of the user to show details for
     */
    data class NavigateToUserDetail(val userId: Int) : UserListUiEffect()
    
    /**
     * Show a snackbar message.
     * 
     * @param message The message to display
     */
    data class ShowSnackbar(val message: String) : UserListUiEffect()
    
    /**
     * Scroll to top of the list.
     */
    data object ScrollToTop : UserListUiEffect()
}
