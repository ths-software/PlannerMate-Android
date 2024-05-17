package com.PlannerMate.ui.features.login

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.PlannerMate.data.ClienteRepository
import com.PlannerMate.data.TrabajadorRepository
import com.PlannerMate.data.services.ApiServicesException
import com.PlannerMate.models.Cliente
import com.PlannerMate.models.Trabajador
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val clienteRepository: ClienteRepository, private val validadorLogin: ValidadorLogin,
    private val trabajadoresRepository: TrabajadorRepository
) : ViewModel() {

    var clienteUiState by mutableStateOf(LoginUiState())
        private set
    var validacionUiState by mutableStateOf(ValidacionLoginUiState())
        private set


    var mostrarSnackBar by mutableStateOf(false)
    val onMostrarSnackBar: () -> Unit by mutableStateOf({
        mostrarSnackBar = !mostrarSnackBar
    })

    var trabajadores by mutableStateOf(mutableListOf<Trabajador>())

    var clientes by mutableStateOf(mutableListOf<Cliente>())

    fun onLoginEvent(loginEvent: LoginEvent) {
        when (loginEvent) {
            is LoginEvent.LoginChanged -> {
                clienteUiState = clienteUiState.copy(contacto = loginEvent.contacto)

                viewModelScope.launch {
                    clientes = clienteRepository.get().toMutableList()

                    val usuario = clienteUiState.toCliente()
                    try {
                        clientes.forEach {
                            if (usuario.contacto == it.contacto) {
                                clienteUiState = clienteUiState.copy(
                                    idCliente = it.idCliente,
                                    nombre = it.nombre
                                )
                            }
                        }
                    } catch (e: ApiServicesException) {
                        Log.d("Resultado", "$e")
                    }
                }


                validacionUiState =
                    validacionUiState.copy(validadorLogin.validadorLogin.valida(loginEvent.contacto)) // check conditions
            }


            is LoginEvent.PasswordChanged -> {
                clienteUiState = clienteUiState.copy(password = loginEvent.password)

                validacionUiState = validacionUiState.copy(
                    validacionPassword = validadorLogin.validadorPassword.valida(loginEvent.password)
                )
            }

            is LoginEvent.OnClickLogearse -> {

                viewModelScope.launch {
                    validacionUiState = validadorLogin.valida(clienteUiState)
                    if (!validacionUiState.hayError) {

                        clienteUiState = clienteUiState.copy(
                            estaLogeado = logearse()
                        )
                        if (clienteUiState.estaLogeado) {
                            delay(3000)

                            loginEvent.onNavigatePerfil?.let { it(clienteUiState.contacto) }

                        } else {
                            Log.d("Resultado", "no logueó!")
                        }
                    } else {
                        Log.d("Resultado", "errores de validación!")
                    }
                }

            }

        }

    }

    suspend fun logearse(): Boolean {
        val usuario = clienteUiState.toCliente()
        try {
            clientes.forEach {
                if (usuario.contacto == it.contacto && usuario.password == it.password) {
                    clienteUiState = clienteUiState.copy(nombre = it.nombre, idCliente = it.idCliente)

                    trabajadores = trabajadoresRepository.get().toMutableList()

                    return true
                }
            }
        } catch (e: ApiServicesException) {
            Log.d("Resultado", "errores al recoger trabajadores: $e")
        }
        return false
    }


    fun LoginUiState.toCliente() =
        Cliente(this.idCliente, this.nombre, this.contacto, this.password)

}