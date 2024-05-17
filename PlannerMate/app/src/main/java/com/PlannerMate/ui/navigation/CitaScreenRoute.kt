package com.PlannerMate.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.PlannerMate.ui.features.cita.CitaScreen
import com.PlannerMate.ui.features.cita.CitaViewModel
import com.PlannerMate.ui.features.login.LoginViewModel

const val CitasRoute = "citasRoute"

fun NavController.navigateToCitas(
    navOptions: NavOptions? = null
) {
    this.navigate("$CitasRoute", navOptions)
}

fun NavGraphBuilder.citasScreenRoute(
    loginViewModel: LoginViewModel,
    citaViewModel: CitaViewModel,
    onNavigateToPerfil: (contacto: String) -> Unit
) {
    composable(route = CitasRoute){ backStackEntry->

        CitaScreen(
            clienteUiState = loginViewModel.clienteUiState,
            trabajadores = loginViewModel.trabajadores,
            onCitaEvent = citaViewModel::onCitaEvent,
            citaUiState = citaViewModel.citaUiState,
            onNavigateToPerfil = onNavigateToPerfil
        )

    }
}