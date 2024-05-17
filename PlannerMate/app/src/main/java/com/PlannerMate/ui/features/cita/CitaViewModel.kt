package com.PlannerMate.ui.features.cita

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.PlannerMate.data.CitaRepository
import com.PlannerMate.data.ClienteRepository
import com.PlannerMate.data.TrabajadorRepository
import com.PlannerMate.models.Cita
import com.PlannerMate.models.Cliente
import com.PlannerMate.models.Trabajador
import com.PlannerMate.ui.features.login.LoginUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CitaViewModel @Inject constructor(
    private val clienteRepository: ClienteRepository,
    private val trabajadoresRepository: TrabajadorRepository,
    private val citasRepository: CitaRepository
) : ViewModel() {

    var clienteUiState by mutableStateOf(LoginUiState())

    var citaUiState by mutableStateOf(CitaUiState())

    //var trabajadorUiState by mutableStateOf(TrabajadorUiState())

    var citas by mutableStateOf(mutableListOf<Cita>())

    fun onCitaEvent(citaEvent: CitaEvent) {
        when (citaEvent) {
            is CitaEvent.DescripcionChanged -> {
                citaUiState = citaUiState.copy(descripcion = citaEvent.desc)
            }

            is CitaEvent.OnClickCitar -> {
                viewModelScope.launch {
                    Log.d(
                        "POST_FINAL", "${citaUiState.idCita}" +
                                "${citaUiState.nombre}" +
                                "${citaUiState.fecha} " +
                                "${citaUiState.contacto}" +
                                "${citaUiState.descripcion}" +
                                "${citaUiState.cliente}" +
                                "${citaUiState.trabajador}"
                    )
                    citasRepository.insert(citaUiState.toCita())
                    citaEvent.onNavigatePerfil?.let { it(clienteUiState.contacto) }
                }
            }

            is CitaEvent.OnClickBack -> {
                citaEvent.onNavigatePerfil?.let { it(clienteUiState.contacto) }
            }

            is CitaEvent.FechaChanged -> {
                val fechaFormateada = citaEvent.fecha.replace("/", "-")
                citaUiState = citaUiState.copy(fecha = fechaFormateada)

                Log.d("FECHA", "${citaUiState.fecha}")
            }

            is CitaEvent.TrabajadorChanged -> {
                citaUiState = citaUiState.copy(trabajador = citaEvent.trabajador)
            }

            CitaEvent.OnEliminarCita -> {

                viewModelScope.launch {
                    citasRepository.delete(citaUiState.idCita)
                    citaUiState = citaUiState.copy(
                        idCita = 0,
                        nombre = "",
                        fecha = "",
                        descripcion = "",
                        contacto = "",
                        cliente = Cliente(),
                        trabajador = Trabajador()
                    )
                }

            }
        }
    }

    fun asignarCliente(cliente: Cliente) {
        citaUiState = citaUiState.copy(cliente = cliente)
        citaUiState = citaUiState.copy(
            nombre = citaUiState.cliente.nombre,
            contacto = citaUiState.cliente.contacto
        )
    }

    suspend fun iniciarCitas() {
        try {
            citas = citasRepository.get().toMutableList()
        } catch (e: Exception) {
            Log.d("errorCitas", "error: $e")
        }
    }

    fun obtenerCitaDeCliente(idCliente: Int) {
        viewModelScope.launch {
            //iniciarCitas()
            try {
                citas = citasRepository.get().toMutableList()
            } catch (e: Exception) {
                Log.d("errorCitas", "error: $e")
            }

            citas.forEach {
                if (it.cliente.idCliente == idCliente) {
                    citaUiState = citaUiState.copy(
                        idCita = it.idCita,
                        nombre = it.nombre,
                        contacto = it.contacto,
                        fecha = it.fecha,
                        descripcion = it.descripcion,
                        trabajador = it.trabajador,
                        cliente = it.cliente
                    )
                    Log.d("Resultado", "cita: ${citaUiState.nombre}")
                    Log.d("Resultado", "Citas cargadas: ${citas.size}")
                } else {
                    citaUiState = citaUiState.copy(
                        idCita = 0,
                        nombre = "",
                        fecha = "",
                        descripcion = "",
                        contacto = "",
                        cliente = Cliente(),
                        trabajador = Trabajador()
                    )
                }

            }

        }


    }


}