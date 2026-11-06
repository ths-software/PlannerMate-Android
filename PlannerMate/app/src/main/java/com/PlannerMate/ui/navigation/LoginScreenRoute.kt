package com.PlannerMate.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.PlannerMate.ui.features.login.LoginScreen
import com.PlannerMate.ui.features.login.LoginViewModel

const val HomeRoute = "loginRoute"


fun NavController.navigateToLogin(
    navOptions: NavOptions? = null
) {
    this.navigate("$HomeRoute", navOptions)
}

fun NavGraphBuilder.loginScreenRoute(
    loginViewModel: LoginViewModel,
    onNavigateToProfile: (contact: String) -> Unit
) {

    composable(route = HomeRoute){ backStackEntry ->

        LoginScreen(
            clientUiState = loginViewModel.clientUiState,
            validationLoginUiState = loginViewModel.validationUiState,
            showSnackbarHostState = loginViewModel.showSnackbar,
            onLoginEvent = loginViewModel::onLoginEvent,
            onNavigateToProfile = onNavigateToProfile,
            onShowSnackbar = loginViewModel.onShowSnackbar)
    }

}