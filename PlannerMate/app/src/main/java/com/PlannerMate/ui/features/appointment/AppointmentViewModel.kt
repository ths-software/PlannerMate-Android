package com.PlannerMate.ui.features.appointment

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.PlannerMate.data.AppointmentRepository
import com.PlannerMate.data.ClientRepository
import com.PlannerMate.data.EmployeeRepository
import com.PlannerMate.models.Appointment
import com.PlannerMate.models.Client
import com.PlannerMate.models.Employee
import com.PlannerMate.ui.features.login.LoginUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppointmentViewModel @Inject constructor(
    private val clientRepository: ClientRepository,
    private val employeesRepository: EmployeeRepository,
    private val appointmentsRepository: AppointmentRepository
) : ViewModel() {

    var clientUiState by mutableStateOf(LoginUiState())

    var appointmentUiState by mutableStateOf(AppointmentUiState())


    var appointments by mutableStateOf(mutableListOf<Appointment>())

    fun onAppointmentEvent(appointmentEvent: AppointmentEvent) {
        when (appointmentEvent) {
            is AppointmentEvent.DescriptionChanged -> {
                appointmentUiState = appointmentUiState.copy(description = appointmentEvent.description)
            }

            is AppointmentEvent.OnClickBookAppointment -> {
                viewModelScope.launch {
                    Log.d(
                        "POST_FINAL", "${appointmentUiState.appointmentId}" +
                                "${appointmentUiState.name}" +
                                "${appointmentUiState.date} " +
                                "${appointmentUiState.contact}" +
                                "${appointmentUiState.description}" +
                                "${appointmentUiState.client}" +
                                "${appointmentUiState.employee}"
                    )
                    appointmentsRepository.insert(appointmentUiState.toAppointment())
                    appointmentEvent.onNavigateProfile?.let { it(clientUiState.contact) }
                }
            }

            is AppointmentEvent.OnClickBack -> {
                appointmentEvent.onNavigateProfile?.let { it(clientUiState.contact) }
            }

            is AppointmentEvent.DateChanged -> {
                val dateFormateada = appointmentEvent.date.replace("/", "-")
                appointmentUiState = appointmentUiState.copy(date = dateFormateada)

                Log.d("FECHA", "${appointmentUiState.date}")
            }

            is AppointmentEvent.EmployeeChanged -> {
                appointmentUiState = appointmentUiState.copy(employee = appointmentEvent.employee)
            }

            AppointmentEvent.OnDeleteAppointment -> {

                viewModelScope.launch {
                    appointmentsRepository.delete(appointmentUiState.appointmentId)
                    appointmentUiState = appointmentUiState.copy(
                        appointmentId = 0,
                        name = "",
                        date = "",
                        description = "",
                        contact = "",
                        client = Client(),
                        employee = Employee()
                    )
                }

            }
        }
    }

    fun assignClient(client: Client) {
        appointmentUiState = appointmentUiState.copy(client = client)
        appointmentUiState = appointmentUiState.copy(
            name = appointmentUiState.client.name,
            contact = appointmentUiState.client.contact
        )
    }

    suspend fun startAppointments() {
        try {
            appointments = appointmentsRepository.get().toMutableList()
        } catch (e: Exception) {
            Log.d("errorCitas", "error: $e")
        }
    }

    fun getClientAppointment(clientId: Int) {
        viewModelScope.launch {
            //iniciarCitas()
            try {
                appointments = appointmentsRepository.get().toMutableList()
            } catch (e: Exception) {
                Log.d("errorCitas", "error: $e")
            }

            appointments.forEach {
                if (it.client.clientId == clientId) {
                    appointmentUiState = appointmentUiState.copy(
                        appointmentId = it.appointmentId,
                        name = it.name,
                        contact = it.contact,
                        date = it.date,
                        description = it.description,
                        employee = it.employee,
                        client = it.client
                    )
                    Log.d("Resultado", "cita: ${appointmentUiState.name}")
                    Log.d("Resultado", "Citas cargadas: ${appointments.size}")
                } else {
                    appointmentUiState = appointmentUiState.copy(
                        appointmentId = 0,
                        name = "",
                        date = "",
                        description = "",
                        contact = "",
                        client = Client(),
                        employee = Employee()
                    )
                }

            }

        }


    }


}