package com.klivvr.core.commonUi.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.Dp
import com.klivvr.core.designSystem.CustomTheme

@Composable
fun TextInputField(
    text: String,
    label: String? = null,
    labelHeight: Dp = CustomTheme.sizing.xSmall,
    placeholder: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier.fillMaxWidth(),
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Done,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    textColor: Color = Color.Black,
    disabledTextColor: Color = Color.Gray,
    backgroundFocusColor: Color = Color.Transparent,
    borderFocusColor: Color = Color.Black,
    borderUnFocusColor: Color = Color.Black,
    backgroundUnFocusColor: Color = Color.Black,
    textStyle: TextStyle = CustomTheme.typography.labelMedium,
    placeHolderStyle: TextStyle = CustomTheme.typography.labelMedium,
    placeholderColor: Color = Color.Black,
    labelColor: Color = textColor,
    cursorColor: Color = textColor,
    trailingIcon: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    isError: Boolean = false,
    singleLine: Boolean = true,
    isEnabled: Boolean = true,
    isReadOnly: Boolean = false,
) {

    Column {
        label?.let {
            Text(text = label, color = labelColor)
            Spacer(modifier = Modifier.height(labelHeight))
        }
        OutlinedTextField(
            value = text,
            textStyle = textStyle,
            placeholder = {
                Text(
                    placeholder, style = placeHolderStyle, color = placeholderColor
                )
            },
            onValueChange = onValueChange,
            modifier = modifier,
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType, imeAction = imeAction),
            keyboardActions = keyboardActions,
            singleLine = singleLine,
            isError = isError,
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = borderFocusColor,
                unfocusedIndicatorColor = borderUnFocusColor,
                cursorColor = cursorColor ,
                focusedContainerColor = backgroundFocusColor,
                unfocusedContainerColor = backgroundUnFocusColor,
                disabledContainerColor = disabledTextColor,
                focusedLabelColor = backgroundFocusColor,
                focusedPlaceholderColor = backgroundFocusColor,

            ),
            shape = CustomTheme.shapes.medium,
            readOnly = isReadOnly,
            enabled = isEnabled,
            trailingIcon = trailingIcon,
            leadingIcon = leadingIcon,

            )
    }
}
