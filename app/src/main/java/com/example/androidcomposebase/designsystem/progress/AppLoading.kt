package com.example.androidcomposebase.designsystem.progress

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.androidcomposebase.designsystem.theme.AppTheme

/**
 * App Loading
 * 
 * A circular progress indicator with customizable size and color.
 * 
 * @param modifier Modifier to be applied
 * @param size Size of the indicator
 * @param color Indicator color
 * @param strokeWidth Stroke width
 */
@Composable
fun AppLoading(
    modifier: Modifier = Modifier,
    size: Dp = 40.dp,
    color: Color = AppTheme.colors.primary,
    strokeWidth: Dp = 3.dp
) {
    CircularProgressIndicator(
        modifier = modifier.size(size),
        color = color,
        strokeWidth = strokeWidth
    )
}

/**
 * App Loading with Text
 * 
 * A loading indicator with accompanying text.
 */
@Composable
fun AppLoadingWithText(
    text: String,
    modifier: Modifier = Modifier,
    size: Dp = 32.dp
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        AppLoading(size = size)
        Spacer(modifier = Modifier.width(AppTheme.dimensions.spacingMd))
        Text(
            text = text,
            style = AppTheme.typography.bodyMedium,
            color = AppTheme.colors.textSecondary
        )
    }
}

/**
 * Full Screen Loading
 * 
 * A full-screen loading overlay with optional message.
 */
@Composable
fun FullScreenLoading(
    modifier: Modifier = Modifier,
    message: String? = null,
    backgroundColor: Color = AppTheme.colors.background.copy(alpha = 0.9f)
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(backgroundColor),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AppLoading(size = 48.dp)
            
            if (!message.isNullOrEmpty()) {
                Spacer(modifier = Modifier.height(AppTheme.dimensions.spacingLg))
                Text(
                    text = message,
                    style = AppTheme.typography.bodyMedium,
                    color = AppTheme.colors.textSecondary
                )
            }
        }
    }
}

/**
 * App Linear Progress
 * 
 * A linear progress indicator.
 * 
 * @param progress Current progress (0f to 1f), or null for indeterminate
 * @param modifier Modifier to be applied
 * @param color Progress color
 * @param trackColor Background track color
 */
@Composable
fun AppLinearProgress(
    progress: Float? = null,
    modifier: Modifier = Modifier,
    color: Color = AppTheme.colors.primary,
    trackColor: Color = AppTheme.colors.surfaceVariant
) {
    if (progress != null) {
        LinearProgressIndicator(
            progress = { progress.coerceIn(0f, 1f) },
            modifier = modifier,
            color = color,
            trackColor = trackColor
        )
    } else {
        LinearProgressIndicator(
            modifier = modifier,
            color = color,
            trackColor = trackColor
        )
    }
}

/**
 * Shimmer Effect
 * 
 * A shimmer loading placeholder effect.
 * 
 * @param modifier Modifier including size
 * @param shape Shape of the shimmer
 */
@Composable
fun ShimmerEffect(
    modifier: Modifier = Modifier,
    shape: Shape = AppTheme.shapes.small
) {
    val shimmerColors = listOf(
        AppTheme.colors.shimmer.copy(alpha = 0.6f),
        AppTheme.colors.shimmer.copy(alpha = 0.2f),
        AppTheme.colors.shimmer.copy(alpha = 0.6f)
    )
    
    val transition = rememberInfiniteTransition(label = "shimmer")
    val translateAnimation by transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1200,
                easing = LinearEasing
            ),
            repeatMode = RepeatMode.Restart
        ),
        label = "shimmer_translate"
    )
    
    val brush = Brush.linearGradient(
        colors = shimmerColors,
        start = Offset(translateAnimation - 200, translateAnimation - 200),
        end = Offset(translateAnimation, translateAnimation)
    )
    
    Box(
        modifier = modifier
            .clip(shape)
            .background(brush)
    )
}

/**
 * Skeleton Text
 * 
 * A skeleton placeholder for text content.
 */
@Composable
fun SkeletonText(
    modifier: Modifier = Modifier,
    width: Dp = 120.dp,
    height: Dp = 16.dp
) {
    ShimmerEffect(
        modifier = modifier
            .width(width)
            .height(height),
        shape = AppTheme.shapes.extraSmall
    )
}

/**
 * Skeleton Circle
 * 
 * A skeleton placeholder for circular content (avatars, icons).
 */
@Composable
fun SkeletonCircle(
    modifier: Modifier = Modifier,
    size: Dp = 48.dp
) {
    ShimmerEffect(
        modifier = modifier.size(size),
        shape = AppTheme.shapes.full
    )
}

// ============================================================
// PREVIEWS
// ============================================================

@Preview(showBackground = true)
@Composable
private fun AppLoadingPreview() {
    AppTheme {
        Box(modifier = Modifier.padding(16.dp)) {
            AppLoading()
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun AppLoadingWithTextPreview() {
    AppTheme {
        Box(modifier = Modifier.padding(16.dp)) {
            AppLoadingWithText(text = "Loading...")
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun AppLinearProgressPreview() {
    AppTheme {
        Column(modifier = Modifier.padding(16.dp)) {
            AppLinearProgress(progress = 0.6f)
            Spacer(modifier = Modifier.height(16.dp))
            AppLinearProgress()
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ShimmerEffectPreview() {
    AppTheme {
        Column(modifier = Modifier.padding(16.dp)) {
            Row {
                SkeletonCircle()
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    SkeletonText(width = 150.dp)
                    Spacer(modifier = Modifier.height(8.dp))
                    SkeletonText(width = 100.dp)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun FullScreenLoadingPreview() {
    AppTheme {
        Surface {
            FullScreenLoading(message = "Please wait...")
        }
    }
}
