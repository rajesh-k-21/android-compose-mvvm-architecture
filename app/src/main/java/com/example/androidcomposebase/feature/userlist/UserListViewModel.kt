package com.example.androidcomposebase.feature.userlist

import androidx.lifecycle.viewModelScope
import com.example.androidcomposebase.core.base.BaseViewModel
import com.example.androidcomposebase.core.util.Resource
import com.example.androidcomposebase.domain.usecase.GetUsersUseCase
import com.example.androidcomposebase.domain.usecase.RefreshUsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel for the User List screen.
 * 
 * This ViewModel extends [BaseViewModel] and handles all business logic
 * for the user list feature, including loading users, refreshing, and
 * handling user interactions.
 * 
 * @param getUsersUseCase Use case for fetching users
 * @param refreshUsersUseCase Use case for refreshing users
 */
@HiltViewModel
class UserListViewModel @Inject constructor(
    private val getUsersUseCase: GetUsersUseCase,
    private val refreshUsersUseCase: RefreshUsersUseCase
) : BaseViewModel<UserListUiState, UserListUiEvent, UserListUiEffect>(
    initialState = UserListUiState()
) {
    
    init {
        // Load users when ViewModel is created
        onEvent(UserListUiEvent.LoadUsers)
    }
    
    /**
     * Handles incoming UI events.
     * 
     * @param event The event to process
     */
    override fun onEvent(event: UserListUiEvent) {
        when (event) {
            is UserListUiEvent.LoadUsers -> loadUsers()
            is UserListUiEvent.RefreshUsers -> refreshUsers()
            is UserListUiEvent.UserClicked -> handleUserClick(event.user)
            is UserListUiEvent.RetryClicked -> loadUsers()
            is UserListUiEvent.DismissError -> dismissError()
        }
    }
    
    /**
     * Loads users from the repository.
     */
    private fun loadUsers() {
        viewModelScope.launch {
            getUsersUseCase()
                .onEach { result ->
                    when (result) {
                        is Resource.Loading -> {
                            updateState { 
                                copy(
                                    isLoading = true,
                                    error = null
                                ) 
                            }
                        }
                        is Resource.Success -> {
                            updateState { 
                                copy(
                                    users = result.data,
                                    isLoading = false,
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
                            sendEffect(UserListUiEffect.ShowSnackbar(result.message))
                        }
                    }
                }
                .launchIn(this)
        }
    }
    
    /**
     * Refreshes users from the repository (pull-to-refresh).
     */
    private fun refreshUsers() {
        viewModelScope.launch {
            refreshUsersUseCase()
                .onEach { result ->
                    when (result) {
                        is Resource.Loading -> {
                            updateState { 
                                copy(
                                    isRefreshing = true,
                                    error = null
                                ) 
                            }
                        }
                        is Resource.Success -> {
                            updateState { 
                                copy(
                                    users = result.data,
                                    isRefreshing = false,
                                    error = null
                                ) 
                            }
                            sendEffect(UserListUiEffect.ScrollToTop)
                            sendEffect(UserListUiEffect.ShowSnackbar("Users refreshed"))
                        }
                        is Resource.Error -> {
                            updateState { 
                                copy(
                                    isRefreshing = false,
                                    error = result.message
                                ) 
                            }
                            sendEffect(UserListUiEffect.ShowSnackbar(result.message))
                        }
                    }
                }
                .launchIn(this)
        }
    }
    
    /**
     * Handles user item click.
     */
    private fun handleUserClick(user: com.example.androidcomposebase.domain.model.User) {
        updateState { copy(selectedUser = user) }
        sendEffect(UserListUiEffect.NavigateToUserDetail(user.id))
    }
    
    /**
     * Dismisses the current error.
     */
    private fun dismissError() {
        updateState { copy(error = null) }
    }
}
