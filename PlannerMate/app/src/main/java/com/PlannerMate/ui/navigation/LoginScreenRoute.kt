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
    onNavigateToPerfil: (contacto: String) -> Unit
) {

    composable(route = HomeRoute){ backStackEntry ->

        LoginScreen(
            clienteUiState = loginViewModel.clienteUiState,
            validacionLoginUiState = loginViewModel.validacionUiState,
            mostrarSnackbarHostState = loginViewModel.mostrarSnackBar,
            onLoginEvent = loginViewModel::onLoginEvent,
            onNavigateToPerfil = onNavigateToPerfil,
            onMostrarSnackBar = loginViewModel.onMostrarSnackBar)
    }

}