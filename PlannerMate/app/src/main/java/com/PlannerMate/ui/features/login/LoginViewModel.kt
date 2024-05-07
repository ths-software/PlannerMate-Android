package com.PlannerMate.ui.features.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.PlannerMate.data.ClienteRepository
import com.PlannerMate.data.services.ApiServicesException
import com.PlannerMate.models.Cliente
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val clienteRepository: ClienteRepository, private val validadorLogin: ValidadorLogin
) : ViewModel(){

    var clienteUiState by mutableStateOf(LoginUiState())
        private set
    var validacionUiState by mutableStateOf(ValidacionLoginUiState())
        private set

    var mostrarSnackBar by mutableStateOf(false)
    val onMostrarSnackBar: () -> Unit by mutableStateOf({
        mostrarSnackBar = !mostrarSnackBar
    })

    fun onLoginEvent(loginEvent: LoginEvent)
    {
        when(loginEvent)
        {
            is LoginEvent.LoginChanged -> {
                clienteUiState = clienteUiState.copy(contacto = loginEvent.contacto)
                validacionUiState = validacionUiState.copy(validadorLogin.validadorLogin.valida(loginEvent.contacto)) // check conditions
            }


            is LoginEvent.PasswordChanged -> {
                clienteUiState = clienteUiState.copy(password = loginEvent.password)

                validacionUiState = validacionUiState.copy(validacionPassword = validadorLogin.validadorPassword.valida(loginEvent.password))
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
                        }
                    }
                }

            }

        }

    }

    suspend fun logearse(): Boolean {
        val usuario = clienteUiState.toCliente()
        try {
            clienteRepository.get().forEach {
                if (usuario.contacto == it.contacto && usuario.password == it.password) return true
            }
        } catch (e: ApiServicesException) {
        }
        return false
    }
    /*
        val idCliente : Int,
    val nombre : String,
    val contacto : String,
    val password : String
     */
    fun LoginUiState.toCliente() = Cliente(this.idCliente,this.nombre,this.contacto,this.password)

}