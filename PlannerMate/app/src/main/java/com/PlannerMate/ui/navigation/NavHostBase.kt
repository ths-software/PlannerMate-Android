package com.PlannerMate.ui.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.PlannerMate.models.toClient
import com.PlannerMate.ui.features.appointment.AppointmentViewModel
import com.PlannerMate.ui.features.login.LoginViewModel
import com.PlannerMate.ui.features.profile.OptionsViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavHostBase() {
    val navController = rememberNavController()
    val loginViewModel: LoginViewModel = hiltViewModel()
    val appointmentViewModel: AppointmentViewModel = hiltViewModel()
    val optionsVM: OptionsViewModel = hiltViewModel()

    NavHost(
        navController = navController,
        startDestination = HomeRoute
    )
    {
        loginScreenRoute(
            loginViewModel,
            onNavigateToProfile = {
                // podríamos cargar elementos relacionados
                navController.navigateToProfile()
                appointmentViewModel.getClientAppointment(loginViewModel.clientUiState.clientId)
            }
        )

        profileScreenRoute(
            loginViewModel,
            appointmentViewModel,
            onAgregarAppointment = {
                navController.navigateToAppointments()
                appointmentViewModel.assignClient(loginViewModel.clientUiState.toClient())
            },
            onLogout = {
                navController.navigateToLogin()
            },
            onOptions = { navController.navigateToOptions() }
        )

        appointmentsScreenRoute(
            loginViewModel,
            appointmentViewModel,
            onNavigateToProfile = {
                appointmentViewModel.getClientAppointment(loginViewModel.clientUiState.clientId)
                navController.navigateToProfile()
            })

        optionsScreenRoute(
            clientUiState = loginViewModel.clientUiState,
            onNavigateToProfile = { navController.navigateToProfile() }
        )

    }
}