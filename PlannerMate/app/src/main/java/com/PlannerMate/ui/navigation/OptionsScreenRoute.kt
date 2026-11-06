package com.PlannerMate.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.PlannerMate.ui.features.login.LoginUiState
import com.PlannerMate.ui.features.profile.OptionsScreen

const val OptionsRoute = "optionsRoute"

fun NavController.navigateToOptions(
    navOptions: NavOptions? = null
) {
    this.navigate("$OptionsRoute", navOptions)
}

fun NavGraphBuilder.optionsScreenRoute(
    clientUiState: LoginUiState,
    onNavigateToProfile: () -> Unit

) {
    composable(route = OptionsRoute) { backStacklEntry ->

        OptionsScreen(
            clientUiState = clientUiState,
            onNavigateToProfile = onNavigateToProfile
        )
    }

}