package com.example.androidcomposebase.navigation

import kotlinx.serialization.Serializable

/**
 * Navigation keys for Navigation 3.
 * 
 * Navigation 3 uses type-safe navigation keys instead of string routes.
 * Each key is a @Serializable data class or object that uniquely identifies
 * a destination and can carry typed arguments.
 * 
 * Benefits of this approach:
 * - Type-safe argument passing
 * - Compile-time validation
 * - No string route parsing errors
 * - IntelliSense/autocomplete support
 */

/**
 * Navigation key for the User List screen.
 * This is a singleton object since it doesn't require any arguments.
 */
@Serializable
data object UserListKey

/**
 * Navigation key for the User Detail screen.
 * 
 * @param userId The ID of the user to display
 */
@Serializable
data class UserDetailKey(val userId: Int)

/**
 * Navigation key for the Settings screen.
 */
@Serializable
data object SettingsKey

/**
 * Navigation key for the Sample screen.
 */
@Serializable
data object SampleKey

/**
 * Navigation key for the Sample Detail screen.
 */
@Serializable
data class SampleDetailKey(val itemId: String)
