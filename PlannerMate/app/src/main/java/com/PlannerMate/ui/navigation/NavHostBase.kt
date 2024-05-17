package com.PlannerMate.ui.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.PlannerMate.models.toCliente
import com.PlannerMate.ui.features.cita.CitaViewModel
import com.PlannerMate.ui.features.login.LoginViewModel
import com.PlannerMate.ui.features.profile.OpcionesViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavHostBase() {
    val navController = rememberNavController()
    val loginViewModel: LoginViewModel = hiltViewModel()
    val citaViewModel: CitaViewModel = hiltViewModel()
    val opcionesVM: OpcionesViewModel = hiltViewModel()

    NavHost(
        navController = navController,
        startDestination = HomeRoute
    )
    {
        loginScreenRoute(
            loginViewModel,
            onNavigateToPerfil = {
                // podríamos cargar elementos relacionados
                navController.navigateToPerfil()
                citaViewModel.obtenerCitaDeCliente(loginViewModel.clienteUiState.idCliente)
            }
        )

        perfilScreenRoute(
            loginViewModel,
            citaViewModel,
            onAgregarCita = {
                navController.navigateToCitas()
                citaViewModel.asignarCliente(loginViewModel.clienteUiState.toCliente())
            },
            onLogout = {
                navController.navigateToLogin()
            },
            onOpciones = { navController.navigateToOpciones() }
        )

        citasScreenRoute(
            loginViewModel,
            citaViewModel,
            onNavigateToPerfil = {
                citaViewModel.obtenerCitaDeCliente(loginViewModel.clienteUiState.idCliente)
                navController.navigateToPerfil()
            })

        opcionesScreenRoute(
            clienteUiState = loginViewModel.clienteUiState,
            onNavigateToPerfil = { navController.navigateToPerfil() }
        )

    }
}