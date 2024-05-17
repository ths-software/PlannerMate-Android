package com.PlannerMate.ui.features.login

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.PlannerMate.R
import com.PlannerMate.ui.theme.Purple40
import com.pmdm.tienda.ui.composables.CircularImageFromResource
import com.pmdm.tienda.ui.composables.TextNewAccount
import com.pmdm.tienda.ui.features.login.components.UsuarioPassword
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch



@Composable
fun LoginScreen(

    clienteUiState: LoginUiState,
    validacionLoginUiState: ValidacionLoginUiState,
    mostrarSnackbarHostState: Boolean,
    onLoginEvent: (LoginEvent) -> Unit,
    onMostrarSnackBar: () -> Unit,
    onNavigateToPerfil: ((contacto: String) -> Unit)? = null

) {

//    var mensaje by remember { mutableStateOf("") }
    val scope = rememberCoroutineScope()

    var recordarmeState by remember { mutableStateOf(false) }

    Box() {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(20.dp)
        ) {
            CircularImageFromResource(
                idImageResource = R.drawable.logo, contentDescription = "Imagen Login"
            )


            UsuarioPassword(modifier = Modifier.fillMaxWidth(),
                loginState = clienteUiState.contacto,
                passwordState = clienteUiState.password,
                validacionLogin = validacionLoginUiState.validacionLogin,
                validacionPassword = validacionLoginUiState.validacionPassword,
                recordarmeState = recordarmeState,
                onValueChangeLogin = {
                    onLoginEvent(LoginEvent.LoginChanged(it))
                },
                onValueChangePassword = {
                    onLoginEvent(LoginEvent.PasswordChanged(it))
                },

                onCheckedChanged = { recordarmeState = it },

                onClickLogearse = {

                    onLoginEvent(LoginEvent.OnClickLogearse(onNavigateToPerfil))

                    onMostrarSnackBar()
                    scope.launch() {
                        delay(4000)
                        onMostrarSnackBar()

                        //onNavigateToTienda?.invoke(usuarioUiState.login)

                    }
                })

        }
        if (mostrarSnackbarHostState) {
            var mensaje = ""
            if (validacionLoginUiState.hayError) mensaje = validacionLoginUiState.mensajeError ?: ""
            else if (clienteUiState.estaLogeado) mensaje =
                "Entrando a la APP con usuario ${clienteUiState.contacto}"
            else mensaje = "Error, no existe el usuario o contraseña incorrecta"
            Snackbar(
                modifier = Modifier.align(Alignment.BottomCenter)
            ) {
                Text(text = mensaje)
            }
        }
    }
}


//@Composable
//fun LoginScreen(
//    clienteUiState: LoginUiState,
//    validacionLoginUiState: ValidacionLoginUiState,
//    mostrarSnackbarHostState: Boolean,
//    onLoginEvent: (LoginEvent) -> Unit,
//    onMostrarSnackBar: () -> Unit,
//
//    onNavigateToPerfil: ((contacto: String) -> Unit)? = null
//) {
//
//    // estos dos campos cambiarlos por los del vm.
//    //var username by remember { mutableStateOf(TextFieldValue()) }
//    //var password by remember { mutableStateOf(TextFieldValue()) }
//
//    val scope = rememberCoroutineScope()
//
//    Box()
//    {
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(horizontal = 16.dp),
//            verticalArrangement = Arrangement.Center,
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//            Text(
//                text = "Bienvenido!",
//
//                modifier = Modifier.padding(bottom = 32.dp)
//            )
//            OutlinedTextField(
//                value = clienteUiState.contacto,
//                onValueChange = { onLoginEvent(LoginEvent.LoginChanged(it)) },
//                label = { Text("Usuario") },
//                modifier = Modifier.fillMaxWidth()
//            )
//            Spacer(modifier = Modifier.height(16.dp))
//            OutlinedTextField(
//                value = clienteUiState.password,
//                onValueChange = { onLoginEvent(LoginEvent.PasswordChanged(it)) },
//                label = { Text("Contraseña") },
//                modifier = Modifier.fillMaxWidth()
//            )
//            Spacer(modifier = Modifier.height(32.dp))
//            Button(onClick = {
//                onLoginEvent(LoginEvent.OnClickLogearse(onNavigateToPerfil))
//
//                onMostrarSnackBar()
//                scope.launch() {
//                    delay(4000)
//                    onMostrarSnackBar()
//
//                    //onNavigateToTienda?.invoke(usuarioUiState.login)
//
//                }
//
//            }, modifier = Modifier.fillMaxWidth()) {
//                Text("Login")
//            }
//        }
//
//        if (mostrarSnackbarHostState) {
//            var mensaje = ""
//            if (validacionLoginUiState.hayError) mensaje = validacionLoginUiState.mensajeError ?: ""
//            else if (clienteUiState.estaLogeado) mensaje =
//                "Entrando a la APP con usuario ${clienteUiState.contacto}"
//            else mensaje = "Error, no existe el usuario o contraseña incorrecta"
//            Snackbar(
//                modifier = Modifier.align(Alignment.BottomCenter)
//            ) {
//                Text(text = mensaje)
//            }
//        }
//    }
//}

