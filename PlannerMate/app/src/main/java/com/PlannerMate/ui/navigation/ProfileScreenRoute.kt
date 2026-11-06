package com.PlannerMate.ui.navigation


import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.PlannerMate.models.Appointment
import com.PlannerMate.ui.features.appointment.AppointmentViewModel
import com.PlannerMate.ui.features.login.LoginScreen
import com.PlannerMate.ui.features.login.LoginViewModel
import com.PlannerMate.ui.features.profile.ProfileScreen

const val ProfileRoute = "profile"


fun NavController.navigateToProfile(
    navOptions: NavOptions? = null
) {
    this.navigate("$ProfileRoute", navOptions)
}

fun NavGraphBuilder.profileScreenRoute(
    loginViewModel: LoginViewModel,
    appointmentViewModel: AppointmentViewModel,
    onAgregarAppointment : () -> Unit,
    onOptions : () -> Unit,
    onLogout : () -> Unit,

) {

    composable(route = ProfileRoute){ backStackEntry ->
        
        ProfileScreen(
            clientUiState = loginViewModel.clientUiState,
            appointmentActual = appointmentViewModel.appointmentUiState,
            onLogout = onLogout,
            onAgregarAppointment = onAgregarAppointment,
            onOptions = onOptions,
            onAppointmentEvent = appointmentViewModel::onAppointmentEvent)

    }

}