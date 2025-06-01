package com.klivvr.search.composable

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.with
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.klivvr.core.commonUi.TextInputField
import com.klivvr.core.designSystem.CustomTheme

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

    AnimatedContent(
        targetState = isFocused,
        transitionSpec = { fadeIn() with fadeOut() }, label = ""
    ) { focused ->
        if (true) {
            TextInputField(
                text = query,
                placeholder = "Search cities...",
                onValueChange = onQueryChange,
                modifier = modifier
                    .height(56.dp)
                    .focusRequester(focusRequester)
                    .onFocusChanged { isFocused = it.isFocused },
                trailingIcon = {
                    IconButton(onClick = { if (query.isEmpty()) isFocused = false }) {
                        Icon(Icons.Default.Close, contentDescription = "Close")
                    }
                },
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Search,
                keyboardActions = KeyboardActions.Default,
                textColor = Color.Black,
                disabledTextColor = Color.Gray,
                backgroundColor = Color.Transparent,
                borderFocusColor = Color.Black,
                borderUnFocusColor = Color.Black,
                textStyle = CustomTheme.typography.labelMedium,
                placeHolderStyle = CustomTheme.typography.labelMedium,
                placeholderColor = Color.Black,
                labelColor = Color.Black,
                cursorColor = Color.Black,
                singleLine = true,
            )
        } else {
            Surface(
                modifier = modifier
                    .height(56.dp)
                    .clickable { isFocused = true },
                shape = RoundedCornerShape(20),
                color = Color.LightGray,
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(Icons.Default.Search, contentDescription = "Search")
                    Spacer(Modifier.width(8.dp))
                    Text(
                        "Search cities...",
                        color = CustomTheme.colors.LightGray.copy(alpha = 0.7f)
                    )
                }
            }
        }
    }
}