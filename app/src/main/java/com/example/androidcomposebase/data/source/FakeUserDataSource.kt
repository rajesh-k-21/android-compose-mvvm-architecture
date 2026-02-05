package com.example.androidcomposebase.data.source

import com.example.androidcomposebase.domain.model.User
import kotlinx.coroutines.delay
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Fake data source that provides mock user data for development and testing.
 * 
 * In a real application, this would be replaced with actual data sources
 * like a REST API client (Retrofit) or a local database (Room).
 * 
 * This implementation simulates network delays to demonstrate loading states.
 */
@Singleton
class FakeUserDataSource @Inject constructor() {
    
    /**
     * Mock user data for demonstration purposes.
     */
    private val mockUsers = listOf(
        User(
            id = 1,
            name = "Alice Johnson",
            email = "alice.johnson@example.com",
            avatarUrl = "https://i.pravatar.cc/150?u=alice",
            bio = "Senior Android Developer passionate about Kotlin and Jetpack Compose.",
            isActive = true
        ),
        User(
            id = 2,
            name = "Bob Smith",
            email = "bob.smith@example.com",
            avatarUrl = "https://i.pravatar.cc/150?u=bob",
            bio = "Full-stack developer with expertise in mobile and web technologies.",
            isActive = true
        ),
        User(
            id = 3,
            name = "Carol Williams",
            email = "carol.williams@example.com",
            avatarUrl = "https://i.pravatar.cc/150?u=carol",
            bio = "UX Designer turned developer. Lover of clean code and beautiful interfaces.",
            isActive = true
        ),
        User(
            id = 4,
            name = "David Brown",
            email = "david.brown@example.com",
            avatarUrl = "https://i.pravatar.cc/150?u=david",
            bio = "DevOps engineer specializing in CI/CD and cloud infrastructure.",
            isActive = false
        ),
        User(
            id = 5,
            name = "Eva Martinez",
            email = "eva.martinez@example.com",
            avatarUrl = "https://i.pravatar.cc/150?u=eva",
            bio = "Product Manager with a background in software engineering.",
            isActive = true
        ),
        User(
            id = 6,
            name = "Frank Wilson",
            email = "frank.wilson@example.com",
            avatarUrl = "https://i.pravatar.cc/150?u=frank",
            bio = "Backend developer focused on scalable microservices architecture.",
            isActive = true
        ),
        User(
            id = 7,
            name = "Grace Lee",
            email = "grace.lee@example.com",
            avatarUrl = "https://i.pravatar.cc/150?u=grace",
            bio = "Machine Learning engineer exploring the intersection of AI and mobile.",
            isActive = true
        ),
        User(
            id = 8,
            name = "Henry Taylor",
            email = "henry.taylor@example.com",
            avatarUrl = "https://i.pravatar.cc/150?u=henry",
            bio = "Security specialist ensuring apps are safe and compliant.",
            isActive = false
        )
    )
    
    /**
     * Simulates fetching all users from a remote source.
     * Includes an artificial delay to demonstrate loading states.
     * 
     * @return List of all mock users
     */
    suspend fun getUsers(): List<User> {
        // Simulate network delay
        delay(1000L)
        return mockUsers
    }
    
    /**
     * Simulates fetching a user by their ID.
     * 
     * @param userId The ID of the user to fetch
     * @return The user if found, null otherwise
     */
    suspend fun getUserById(userId: Int): User? {
        delay(500L)
        return mockUsers.find { it.id == userId }
    }
    
    /**
     * Simulates refreshing user data from a remote source.
     * In a real app, this would force a network call even if cached data exists.
     * 
     * @return List of all mock users
     */
    suspend fun refreshUsers(): List<User> {
        // Simulate longer network delay for refresh
        delay(1500L)
        return mockUsers
    }
}
