package com.klivvr

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.NonRestartableComposable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.klivvr.NavRoutes.CitySearch
import com.klivvr.search.composable.CitySearchScreen

@OptIn(ExperimentalComposeUiApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
@NonRestartableComposable
fun KlivvrNavHost( startDestination: String = CitySearch) {
    val navController = rememberNavController()

    Scaffold(
        modifier = Modifier,
        contentWindowInsets = WindowInsets(0, 0, 0, 0),
    ) {
        NavHost(
            modifier = Modifier.semantics { testTagsAsResourceId = true },
            navController = navController,
            startDestination = startDestination,
        ) {
            composable(CitySearch) {
                CitySearchScreen(
                    onMapRequested = { city ->
                        // navController.navigate("${NavRoutes.Map}/${city.id}")
                    }
                )
            }

        }
    }
}
object NavRoutes {
    const val CitySearch = "city_search"
    // Add other routes here
}

