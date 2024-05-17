package com.PlannerMate.ui.navigation


import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.PlannerMate.models.Cita
import com.PlannerMate.ui.features.cita.CitaViewModel
import com.PlannerMate.ui.features.login.LoginScreen
import com.PlannerMate.ui.features.login.LoginViewModel
import com.PlannerMate.ui.features.profile.ProfileScreen

const val PerfilRoute = "perfil"


fun NavController.navigateToPerfil(
    navOptions: NavOptions? = null
) {
    this.navigate("$PerfilRoute", navOptions)
}

fun NavGraphBuilder.perfilScreenRoute(
    loginViewModel: LoginViewModel,
    citaViewModel: CitaViewModel,
    onAgregarCita : () -> Unit,
    onOpciones : () -> Unit,
    onLogout : () -> Unit,

) {

    composable(route = PerfilRoute){ backStackEntry ->
        
        ProfileScreen(
            clienteUiState = loginViewModel.clienteUiState,
            citaActual = citaViewModel.citaUiState,
            onLogout = onLogout,
            onAgregarCita = onAgregarCita,
            onOpciones = onOpciones,
            onCitaEvent = citaViewModel::onCitaEvent)

    }

}