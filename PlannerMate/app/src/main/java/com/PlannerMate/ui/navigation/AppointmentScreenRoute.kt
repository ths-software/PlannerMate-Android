package com.PlannerMate.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.PlannerMate.ui.features.appointment.AppointmentScreen
import com.PlannerMate.ui.features.appointment.AppointmentViewModel
import com.PlannerMate.ui.features.login.LoginViewModel

const val AppointmentsRoute = "appointmentsRoute"

fun NavController.navigateToAppointments(
    navOptions: NavOptions? = null
) {
    this.navigate("$AppointmentsRoute", navOptions)
}

fun NavGraphBuilder.appointmentsScreenRoute(
    loginViewModel: LoginViewModel,
    appointmentViewModel: AppointmentViewModel,
    onNavigateToProfile: (contact: String) -> Unit
) {
    composable(route = AppointmentsRoute){ backStackEntry->

        AppointmentScreen(
            clientUiState = loginViewModel.clientUiState,
            employees = loginViewModel.employees,
            onAppointmentEvent = appointmentViewModel::onAppointmentEvent,
            appointmentUiState = appointmentViewModel.appointmentUiState,
            onNavigateToProfile = onNavigateToProfile
        )

    }
}