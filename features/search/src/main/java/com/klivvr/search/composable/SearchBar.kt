package com.klivvr.search.composable

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import com.klivvr.core.commonUi.composables.TextInputField
import com.klivvr.core.designSystem.CustomTheme
import com.klivvr.core.R

@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
fun SearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    focusRequester: FocusRequester,
    modifier: Modifier = Modifier
) {
    var isFocused by remember { mutableStateOf(true) }

    LaunchedEffect(isFocused) {
        if (isFocused) {
            focusRequester.requestFocus()
        }
    }

    Surface(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clickable { isFocused = true },
        color = Color.White,
    ) {
        Row(
            modifier = Modifier.wrapContentSize(), verticalAlignment = Alignment.CenterVertically
        ) {
            TextInputField(
                text = query,
                placeholder = stringResource(id = R.string.search_placeholder),
                onValueChange = onQueryChange,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(CustomTheme.spacing.spacer)
                    .focusRequester(focusRequester)
                    .onFocusChanged { isFocused = it.isFocused },
                leadingIcon = {
                    Icon(Icons.Default.Search, contentDescription = stringResource(id = R.string.search_icon_desc))
                },
                trailingIcon = {
                    if (query.isNotEmpty()) {
                        IconButton(onClick = { onQueryChange("") }) {
                            Icon(Icons.Default.Close, contentDescription = stringResource(id = R.string.clear_icon_desc))
                        }
                    }
                },
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Previous,
                textColor = Color.Black,
                backgroundFocusColor = Color.White,
                borderFocusColor = Color.White,
                borderUnFocusColor = CustomTheme.colors.LightGray_3,
                backgroundUnFocusColor = CustomTheme.colors.LightGray_3,
                textStyle = CustomTheme.typography.labelMedium,
                placeHolderStyle = CustomTheme.typography.labelMedium.copy(color = CustomTheme.colors.Gray),
                singleLine = true,
                cursorColor = CustomTheme.colors.Dark_Gray
            )
        }
    }
}