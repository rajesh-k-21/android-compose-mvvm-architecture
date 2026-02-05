package com.example.androidcomposebase.designsystem.selection

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import com.example.androidcomposebase.designsystem.theme.AppTheme

/**
 * App Checkbox
 * 
 * A checkbox with optional label.
 * 
 * @param checked Whether the checkbox is checked
 * @param onCheckedChange Callback when checked state changes
 * @param modifier Modifier to be applied
 * @param enabled Whether the checkbox is enabled
 * @param label Optional label text
 */
@Composable
fun AppCheckbox(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    label: String? = null
) {
    Row(
        modifier = modifier
            .then(
                if (label != null) {
                    Modifier.toggleable(
                        value = checked,
                        enabled = enabled,
                        role = Role.Checkbox,
                        onValueChange = onCheckedChange
                    )
                } else Modifier
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = checked,
            onCheckedChange = if (label != null) null else onCheckedChange,
            enabled = enabled,
            colors = CheckboxDefaults.colors(
                checkedColor = AppTheme.colors.primary,
                uncheckedColor = AppTheme.colors.textSecondary,
                checkmarkColor = AppTheme.colors.onPrimary,
                disabledCheckedColor = AppTheme.colors.disabled,
                disabledUncheckedColor = AppTheme.colors.disabled
            )
        )
        
        if (label != null) {
            Spacer(modifier = Modifier.width(AppTheme.dimensions.spacingSm))
            Text(
                text = label,
                style = AppTheme.typography.bodyMedium,
                color = if (enabled) AppTheme.colors.textPrimary else AppTheme.colors.textDisabled
            )
        }
    }
}

/**
 * App Switch
 * 
 * A toggle switch with optional label.
 * 
 * @param checked Whether the switch is on
 * @param onCheckedChange Callback when state changes
 * @param modifier Modifier to be applied
 * @param enabled Whether the switch is enabled
 * @param label Optional label text
 */
@Composable
fun AppSwitch(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    label: String? = null
) {
    Row(
        modifier = modifier
            .then(
                if (label != null) {
                    Modifier.fillMaxWidth()
                } else Modifier
            ),
        horizontalArrangement = if (label != null) Arrangement.SpaceBetween else Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (label != null) {
            Text(
                text = label,
                style = AppTheme.typography.bodyMedium,
                color = if (enabled) AppTheme.colors.textPrimary else AppTheme.colors.textDisabled,
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(AppTheme.dimensions.spacingMd))
        }
        
        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange,
            enabled = enabled,
            colors = SwitchDefaults.colors(
                checkedThumbColor = AppTheme.colors.onPrimary,
                checkedTrackColor = AppTheme.colors.primary,
                uncheckedThumbColor = AppTheme.colors.onSurface,
                uncheckedTrackColor = AppTheme.colors.surfaceVariant,
                disabledCheckedThumbColor = AppTheme.colors.disabledContent,
                disabledCheckedTrackColor = AppTheme.colors.disabled,
                disabledUncheckedThumbColor = AppTheme.colors.disabledContent,
                disabledUncheckedTrackColor = AppTheme.colors.disabled
            )
        )
    }
}

/**
 * App Radio Button
 * 
 * A radio button with optional label.
 * 
 * @param selected Whether the radio button is selected
 * @param onClick Callback when clicked
 * @param modifier Modifier to be applied
 * @param enabled Whether the radio button is enabled
 * @param label Optional label text
 */
@Composable
fun AppRadioButton(
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    label: String? = null
) {
    Row(
        modifier = modifier
            .then(
                if (label != null) {
                    Modifier.selectable(
                        selected = selected,
                        enabled = enabled,
                        role = Role.RadioButton,
                        onClick = onClick
                    )
                } else Modifier
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = selected,
            onClick = if (label != null) null else onClick,
            enabled = enabled,
            colors = RadioButtonDefaults.colors(
                selectedColor = AppTheme.colors.primary,
                unselectedColor = AppTheme.colors.textSecondary,
                disabledSelectedColor = AppTheme.colors.disabled,
                disabledUnselectedColor = AppTheme.colors.disabled
            )
        )
        
        if (label != null) {
            Spacer(modifier = Modifier.width(AppTheme.dimensions.spacingSm))
            Text(
                text = label,
                style = AppTheme.typography.bodyMedium,
                color = if (enabled) AppTheme.colors.textPrimary else AppTheme.colors.textDisabled
            )
        }
    }
}

/**
 * App Radio Group
 * 
 * A group of radio buttons.
 */
@Composable
fun <T> AppRadioGroup(
    options: List<T>,
    selectedOption: T,
    onOptionSelected: (T) -> Unit,
    modifier: Modifier = Modifier,
    optionLabel: (T) -> String
) {
    Column(modifier = modifier) {
        options.forEach { option ->
            AppRadioButton(
                selected = option == selectedOption,
                onClick = { onOptionSelected(option) },
                label = optionLabel(option),
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

/**
 * App Slider
 * 
 * A slider for selecting a value from a range.
 * 
 * @param value Current value
 * @param onValueChange Callback when value changes
 * @param modifier Modifier to be applied
 * @param enabled Whether the slider is enabled
 * @param valueRange Range of values
 * @param steps Number of discrete steps (0 for continuous)
 */
@Composable
fun AppSlider(
    value: Float,
    onValueChange: (Float) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    valueRange: ClosedFloatingPointRange<Float> = 0f..1f,
    steps: Int = 0
) {
    Slider(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        enabled = enabled,
        valueRange = valueRange,
        steps = steps,
        colors = SliderDefaults.colors(
            thumbColor = AppTheme.colors.primary,
            activeTrackColor = AppTheme.colors.primary,
            inactiveTrackColor = AppTheme.colors.surfaceVariant,
            disabledThumbColor = AppTheme.colors.disabled,
            disabledActiveTrackColor = AppTheme.colors.disabled,
            disabledInactiveTrackColor = AppTheme.colors.surfaceVariant
        )
    )
}

// ============================================================
// PREVIEWS
// ============================================================

@Preview(showBackground = true)
@Composable
private fun AppCheckboxPreview() {
    AppTheme {
        Column {
            AppCheckbox(
                checked = true,
                onCheckedChange = {},
                label = "Checked"
            )
            AppCheckbox(
                checked = false,
                onCheckedChange = {},
                label = "Unchecked"
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun AppSwitchPreview() {
    AppTheme {
        Column {
            AppSwitch(
                checked = true,
                onCheckedChange = {},
                label = "Notifications"
            )
            AppSwitch(
                checked = false,
                onCheckedChange = {},
                label = "Dark Mode"
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun AppRadioButtonPreview() {
    AppTheme {
        Column {
            AppRadioButton(
                selected = true,
                onClick = {},
                label = "Option 1"
            )
            AppRadioButton(
                selected = false,
                onClick = {},
                label = "Option 2"
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun AppSliderPreview() {
    AppTheme {
        AppSlider(
            value = 0.5f,
            onValueChange = {}
        )
    }
}
