package com.PlannerMate.ui.features.login

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.PlannerMate.data.ClientRepository
import com.PlannerMate.data.EmployeeRepository
import com.PlannerMate.data.services.ApiServicesException
import com.PlannerMate.models.Client
import com.PlannerMate.models.Employee
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val clientRepository: ClientRepository, private val validatorLogin: LoginValidator,
    private val employeesRepository: EmployeeRepository
) : ViewModel() {

    var clientUiState by mutableStateOf(LoginUiState())
        private set
    var validationUiState by mutableStateOf(LoginValidationUiState())
        private set


    var showSnackbar by mutableStateOf(false)
    val onShowSnackbar: () -> Unit by mutableStateOf({
        showSnackbar = !showSnackbar
    })

    var employees by mutableStateOf(mutableListOf<Employee>())

    var clients by mutableStateOf(mutableListOf<Client>())

    fun onLoginEvent(loginEvent: LoginEvent) {
        when (loginEvent) {
            is LoginEvent.LoginChanged -> {
                clientUiState = clientUiState.copy(contact = loginEvent.contact)

                viewModelScope.launch {
                    clients = clientRepository.get().toMutableList()

                    val user = clientUiState.toClient()
                    try {
                        clients.forEach {
                            if (user.contact == it.contact) {
                                clientUiState = clientUiState.copy(
                                    clientId = it.clientId,
                                    name = it.name
                                )
                            }
                        }
                    } catch (e: ApiServicesException) {
                        Log.d("Resultado", "$e")
                    }
                }


                validationUiState =
                    validationUiState.copy(validatorLogin.validatorLogin.validate(loginEvent.contact)) // check conditions
            }


            is LoginEvent.PasswordChanged -> {
                clientUiState = clientUiState.copy(password = loginEvent.password)

                validationUiState = validationUiState.copy(
                    validationPassword = validatorLogin.validatorPassword.validate(loginEvent.password)
                )
            }

            is LoginEvent.OnClickLogin -> {

                viewModelScope.launch {
                    validationUiState = validatorLogin.validate(clientUiState)
                    if (!validationUiState.hasError) {

                        clientUiState = clientUiState.copy(
                            isLoggedIn = logIn()
                        )
                        if (clientUiState.isLoggedIn) {
                            delay(3000)

                            loginEvent.onNavigateProfile?.let { it(clientUiState.contact) }

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

    suspend fun logIn(): Boolean {
        val user = clientUiState.toClient()
        try {
            clients.forEach {
                if (user.contact == it.contact && user.password == it.password) {
                    clientUiState = clientUiState.copy(name = it.name, clientId = it.clientId)

                    employees = employeesRepository.get().toMutableList()

                    return true
                }
            }
        } catch (e: ApiServicesException) {
            Log.d("Resultado", "errores al recoger trabajadores: $e")
        }
        return false
    }


    fun LoginUiState.toClient() =
        Client(this.clientId, this.name, this.contact, this.password)

}