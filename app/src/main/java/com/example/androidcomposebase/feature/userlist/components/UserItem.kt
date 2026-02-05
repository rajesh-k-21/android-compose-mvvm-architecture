package com.example.androidcomposebase.feature.userlist.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androidcomposebase.domain.model.User
import com.example.androidcomposebase.ui.theme.AndroidComposeBaseTheme

/**
 * A composable that displays a single user item in a card format.
 * 
 * This is a stateless composable that receives data and callbacks from its parent.
 * It follows the single responsibility principle and can be reused across different screens.
 * 
 * @param user The user data to display
 * @param onClick Callback invoked when the item is clicked
 * @param modifier Optional modifier for the composable
 */
@Composable
fun UserItem(
    user: User,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Avatar placeholder
            UserAvatar(
                name = user.name,
                isActive = user.isActive
            )
            
            Spacer(modifier = Modifier.width(16.dp))
            
            // User info
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = user.name,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                
                Text(
                    text = user.email,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                
                user.bio?.let { bio ->
                    Text(
                        text = bio,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f),
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
            
            // Status indicator
            if (!user.isActive) {
                Surface(
                    shape = RoundedCornerShape(4.dp),
                    color = MaterialTheme.colorScheme.errorContainer
                ) {
                    Text(
                        text = "Inactive",
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onErrorContainer
                    )
                }
            }
        }
    }
}

/**
 * A composable that displays a user's avatar with their initial.
 * 
 * @param name The user's name (first letter is used as initial)
 * @param isActive Whether the user is active (affects color)
 * @param modifier Optional modifier for the composable
 */
@Composable
private fun UserAvatar(
    name: String,
    isActive: Boolean,
    modifier: Modifier = Modifier
) {
    val backgroundColor = if (isActive) {
        MaterialTheme.colorScheme.primaryContainer
    } else {
        MaterialTheme.colorScheme.surfaceVariant
    }
    
    val contentColor = if (isActive) {
        MaterialTheme.colorScheme.onPrimaryContainer
    } else {
        MaterialTheme.colorScheme.onSurfaceVariant
    }
    
    Box(
        modifier = modifier
            .size(56.dp)
            .clip(CircleShape)
            .background(backgroundColor),
        contentAlignment = Alignment.Center
    ) {
        if (name.isNotEmpty()) {
            Text(
                text = name.first().uppercase(),
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = contentColor
            )
        } else {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "User avatar",
                tint = contentColor,
                modifier = Modifier.size(32.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun UserItemPreview() {
    AndroidComposeBaseTheme {
        UserItem(
            user = User(
                id = 1,
                name = "Alice Johnson",
                email = "alice.johnson@example.com",
                bio = "Senior Android Developer passionate about Kotlin and Jetpack Compose.",
                isActive = true
            ),
            onClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun UserItemInactivePreview() {
    AndroidComposeBaseTheme {
        UserItem(
            user = User(
                id = 2,
                name = "Bob Smith",
                email = "bob.smith@example.com",
                bio = "Full-stack developer with expertise in mobile and web.",
                isActive = false
            ),
            onClick = {}
        )
    }
}
