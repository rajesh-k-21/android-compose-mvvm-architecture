package com.example.androidcomposebase.domain.model

/**
 * Domain model representing a User in the application.
 * 
 * This is a pure Kotlin data class that represents the core
 * business entity, independent of any external framework or data source.
 * 
 * @param id Unique identifier for the user
 * @param name Display name of the user
 * @param email Email address of the user
 * @param avatarUrl Optional URL to the user's avatar image
 * @param bio Optional biography or description
 * @param isActive Whether the user account is active
 */
data class User(
    val id: Int,
    val name: String,
    val email: String,
    val avatarUrl: String? = null,
    val bio: String? = null,
    val isActive: Boolean = true
)
