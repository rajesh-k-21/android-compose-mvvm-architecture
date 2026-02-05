package com.example.androidcomposebase.designsystem.image

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BrokenImage
import androidx.compose.material.icons.filled.ImageNotSupported
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.example.androidcomposebase.designsystem.theme.AppTheme

/**
 * App Image
 * 
 * A Coil-based image component with:
 * - URL and drawable resource support
 * - Placeholder and error states
 * - Shape options (circle, rounded, rectangle)
 * - Loading indicator
 * - Crossfade animation
 * 
 * @param model Image source (URL string, Uri, or @DrawableRes)
 * @param contentDescription Accessibility description
 * @param modifier Modifier to be applied
 * @param placeholder Placeholder to show while loading
 * @param error Image to show on error
 * @param shape Shape of the image
 * @param contentScale How to scale the image
 * @param showLoading Whether to show loading indicator
 */
@Composable
fun AppImage(
    model: Any?,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    placeholder: Painter? = null,
    error: Painter? = null,
    shape: Shape = RoundedCornerShape(0.dp),
    contentScale: ContentScale = ContentScale.Crop,
    showLoading: Boolean = true
) {
    SubcomposeAsyncImage(
        model = model,
        contentDescription = contentDescription,
        modifier = modifier.clip(shape),
        contentScale = contentScale,
        loading = {
            if (showLoading) {
                Box(
                    modifier = Modifier
                        .matchParentSize()
                        .background(AppTheme.colors.surfaceVariant),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(24.dp),
                        color = AppTheme.colors.primary,
                        strokeWidth = 2.dp
                    )
                }
            } else if (placeholder != null) {
                androidx.compose.foundation.Image(
                    painter = placeholder,
                    contentDescription = null,
                    modifier = Modifier.matchParentSize(),
                    contentScale = contentScale
                )
            } else {
                Box(
                    modifier = Modifier
                        .matchParentSize()
                        .background(AppTheme.colors.surfaceVariant),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = null,
                        tint = AppTheme.colors.textTertiary
                    )
                }
            }
        },
        error = {
            if (error != null) {
                androidx.compose.foundation.Image(
                    painter = error,
                    contentDescription = null,
                    modifier = Modifier.matchParentSize(),
                    contentScale = contentScale
                )
            } else {
                Box(
                    modifier = Modifier
                        .matchParentSize()
                        .background(AppTheme.colors.surfaceVariant),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.BrokenImage,
                        contentDescription = null,
                        tint = AppTheme.colors.textTertiary
                    )
                }
            }
        }
    )
}

/**
 * Circle Image
 * 
 * An image clipped to a circle shape.
 * Commonly used for avatars and profile pictures.
 */
@Composable
fun CircleImage(
    model: Any?,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    size: Dp = 48.dp,
    showLoading: Boolean = true
) {
    AppImage(
        model = model,
        contentDescription = contentDescription,
        modifier = modifier.size(size),
        shape = CircleShape,
        showLoading = showLoading
    )
}

/**
 * Rounded Image
 * 
 * An image with rounded corners.
 */
@Composable
fun RoundedImage(
    model: Any?,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    cornerRadius: Dp = AppTheme.dimensions.radiusMedium,
    showLoading: Boolean = true
) {
    AppImage(
        model = model,
        contentDescription = contentDescription,
        modifier = modifier,
        shape = RoundedCornerShape(cornerRadius),
        showLoading = showLoading
    )
}

/**
 * App Avatar
 * 
 * A user avatar component with:
 * - Image or initials fallback
 * - Border option
 * - Multiple sizes
 */
@Composable
fun AppAvatar(
    imageUrl: String?,
    modifier: Modifier = Modifier,
    size: Dp = AppTheme.dimensions.avatarSizeMedium,
    placeholderText: String? = null,
    placeholderIcon: ImageVector? = null,
    borderColor: Color? = null,
    borderWidth: Dp = 2.dp
) {
    val borderModifier = if (borderColor != null) {
        Modifier
            .size(size + borderWidth * 2)
            .background(borderColor, CircleShape)
    } else {
        Modifier.size(size)
    }
    
    Box(
        modifier = modifier.then(borderModifier),
        contentAlignment = Alignment.Center
    ) {
        if (!imageUrl.isNullOrEmpty()) {
            AppImage(
                model = imageUrl,
                contentDescription = "Avatar",
                modifier = Modifier
                    .size(size)
                    .clip(CircleShape),
                shape = CircleShape,
                showLoading = false
            )
        } else {
            // Fallback to initials or icon
            Box(
                modifier = Modifier
                    .size(size)
                    .background(AppTheme.colors.primaryContainer, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                when {
                    !placeholderText.isNullOrEmpty() -> {
                        val initials = placeholderText
                            .split(" ")
                            .take(2)
                            .mapNotNull { it.firstOrNull()?.uppercase() }
                            .joinToString("")
                        
                        androidx.compose.material3.Text(
                            text = initials,
                            style = when {
                                size <= 32.dp -> AppTheme.typography.labelSmall
                                size <= 48.dp -> AppTheme.typography.labelMedium
                                else -> AppTheme.typography.titleMedium
                            },
                            color = AppTheme.colors.primary
                        )
                    }
                    placeholderIcon != null -> {
                        Icon(
                            imageVector = placeholderIcon,
                            contentDescription = null,
                            modifier = Modifier.size(size * 0.5f),
                            tint = AppTheme.colors.primary
                        )
                    }
                    else -> {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = null,
                            modifier = Modifier.size(size * 0.5f),
                            tint = AppTheme.colors.primary
                        )
                    }
                }
            }
        }
    }
}

// ============================================================
// PREVIEWS
// ============================================================

@Preview(showBackground = true)
@Composable
private fun AppImagePreview() {
    AppTheme {
        AppImage(
            model = null,
            contentDescription = "Sample image",
            modifier = Modifier.size(100.dp),
            shape = RoundedCornerShape(8.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CircleImagePreview() {
    AppTheme {
        CircleImage(
            model = null,
            contentDescription = "Profile picture",
            size = 64.dp
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun AppAvatarPreview() {
    AppTheme {
        AppAvatar(
            imageUrl = null,
            placeholderText = "John Doe",
            size = 48.dp
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun AppAvatarWithBorderPreview() {
    AppTheme {
        AppAvatar(
            imageUrl = null,
            placeholderText = "Jane Smith",
            size = 56.dp,
            borderColor = AppTheme.colors.primary
        )
    }
}
