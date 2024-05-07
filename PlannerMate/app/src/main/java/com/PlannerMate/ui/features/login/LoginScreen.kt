package com.PlannerMate.ui.features.login

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun LoginScreen(
    clienteUiState: LoginUiState,
    validacionLoginUiState: ValidacionLoginUiState,
    mostrarSnackbarHostState: Boolean, //posiblemente no se use
    onLoginEvent: (LoginEvent) -> Unit,
    onMostrarSnackBar:() -> Unit, // lo mismo

    onNavigateToPerfil:((contacto : String) -> Unit)? = null
) {

    // estos dos campos cambiarlos por los del vm.
    //var username by remember { mutableStateOf(TextFieldValue()) }
    //var password by remember { mutableStateOf(TextFieldValue()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Welcome Back!",

            modifier = Modifier.padding(bottom = 32.dp)
        )
        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Username") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(32.dp))
        Button(onClick = { /* TODO: Handle login */ }, modifier = Modifier.fillMaxWidth()) {
            Text("Login")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewLoginScreen() {
    MaterialTheme {
        //LoginScreen()
    }
}
