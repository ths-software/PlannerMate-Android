package com.PlannerMate.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.PlannerMate.ui.features.login.LoginUiState
import com.PlannerMate.ui.features.profile.OpcionesScreen

const val OpcionesRoute = "opcionesRoute"

fun NavController.navigateToOpciones(
    navOptions: NavOptions? = null
) {
    this.navigate("$OpcionesRoute", navOptions)
}

fun NavGraphBuilder.opcionesScreenRoute(
    clienteUiState: LoginUiState,
    onNavigateToPerfil: () -> Unit

) {
    composable(route = OpcionesRoute) { backStacklEntry ->

        OpcionesScreen(
            clienteUiState = clienteUiState,
            onNavigateToPerfil = onNavigateToPerfil
        )
    }

}