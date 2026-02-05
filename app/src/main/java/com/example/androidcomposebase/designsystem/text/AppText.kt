package com.example.androidcomposebase.designsystem.text

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.example.androidcomposebase.designsystem.theme.AppTheme

/**
 * App Text
 * 
 * A customizable text component with support for:
 * - All Material 3 typography styles
 * - Color customization
 * - Max lines with overflow handling
 * - Clickable text
 * - Text decorations (underline, strikethrough)
 * 
 * @param text Text content
 * @param modifier Modifier to be applied
 * @param style Text style (use AppTheme.typography.*)
 * @param color Text color
 * @param textAlign Text alignment
 * @param maxLines Maximum number of lines
 * @param overflow Text overflow behavior
 * @param textDecoration Text decoration (underline, strikethrough)
 * @param onClick Optional click handler (makes text clickable)
 */
@Composable
fun AppText(
    text: String,
    modifier: Modifier = Modifier,
    style: TextStyle = LocalTextStyle.current,
    color: Color = AppTheme.colors.textPrimary,
    textAlign: TextAlign? = null,
    maxLines: Int = Int.MAX_VALUE,
    overflow: TextOverflow = TextOverflow.Clip,
    textDecoration: TextDecoration? = null,
    fontWeight: FontWeight? = null,
    onClick: (() -> Unit)? = null
) {
    val clickableModifier = if (onClick != null) {
        modifier.clickable(
            interactionSource = remember { MutableInteractionSource() },
            indication = null,
            onClick = onClick
        )
    } else {
        modifier
    }
    
    Text(
        text = text,
        modifier = clickableModifier,
        style = style.copy(
            textDecoration = textDecoration,
            fontWeight = fontWeight ?: style.fontWeight
        ),
        color = color,
        textAlign = textAlign,
        maxLines = maxLines,
        overflow = overflow
    )
}

/**
 * Heading Text
 * 
 * Pre-styled heading text component.
 */
@Composable
fun HeadingText(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = AppTheme.colors.textPrimary,
    textAlign: TextAlign? = null
) {
    AppText(
        text = text,
        modifier = modifier,
        style = AppTheme.typography.headlineMedium,
        color = color,
        textAlign = textAlign
    )
}

/**
 * Title Text
 * 
 * Pre-styled title text component.
 */
@Composable
fun TitleText(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = AppTheme.colors.textPrimary,
    textAlign: TextAlign? = null
) {
    AppText(
        text = text,
        modifier = modifier,
        style = AppTheme.typography.titleMedium,
        color = color,
        textAlign = textAlign
    )
}

/**
 * Body Text
 * 
 * Pre-styled body text component.
 */
@Composable
fun BodyText(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = AppTheme.colors.textPrimary,
    maxLines: Int = Int.MAX_VALUE,
    overflow: TextOverflow = TextOverflow.Clip,
    textAlign: TextAlign? = null
) {
    AppText(
        text = text,
        modifier = modifier,
        style = AppTheme.typography.bodyMedium,
        color = color,
        maxLines = maxLines,
        overflow = overflow,
        textAlign = textAlign
    )
}

/**
 * Caption Text
 * 
 * Pre-styled caption/label text component.
 */
@Composable
fun CaptionText(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = AppTheme.colors.textSecondary,
    textAlign: TextAlign? = null
) {
    AppText(
        text = text,
        modifier = modifier,
        style = AppTheme.typography.bodySmall,
        color = color,
        textAlign = textAlign
    )
}

/**
 * Link Text
 * 
 * Clickable text that looks like a link.
 */
@Composable
fun LinkText(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    style: TextStyle = AppTheme.typography.bodyMedium
) {
    AppText(
        text = text,
        modifier = modifier,
        style = style,
        color = AppTheme.colors.textLink,
        textDecoration = TextDecoration.Underline,
        onClick = onClick
    )
}

/**
 * Error Text
 * 
 * Text styled for error messages.
 */
@Composable
fun ErrorText(
    text: String,
    modifier: Modifier = Modifier
) {
    AppText(
        text = text,
        modifier = modifier,
        style = AppTheme.typography.bodySmall,
        color = AppTheme.colors.error
    )
}

// ============================================================
// PREVIEWS
// ============================================================

@Preview(showBackground = true)
@Composable
private fun AppTextPreview() {
    AppTheme {
        AppText(
            text = "Regular Text",
            style = AppTheme.typography.bodyLarge
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun HeadingTextPreview() {
    AppTheme {
        HeadingText(text = "Heading")
    }
}

@Preview(showBackground = true)
@Composable
private fun TitleTextPreview() {
    AppTheme {
        TitleText(text = "Title Text")
    }
}

@Preview(showBackground = true)
@Composable
private fun BodyTextPreview() {
    AppTheme {
        BodyText(text = "This is body text content that can span multiple lines.")
    }
}

@Preview(showBackground = true)
@Composable
private fun CaptionTextPreview() {
    AppTheme {
        CaptionText(text = "Caption or label text")
    }
}

@Preview(showBackground = true)
@Composable
private fun LinkTextPreview() {
    AppTheme {
        LinkText(
            text = "Click here",
            onClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ErrorTextPreview() {
    AppTheme {
        ErrorText(text = "Something went wrong")
    }
}
