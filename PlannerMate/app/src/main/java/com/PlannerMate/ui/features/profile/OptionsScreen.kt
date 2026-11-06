package com.PlannerMate.ui.features.profile

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.PlannerMate.R
import com.PlannerMate.ui.features.login.LoginUiState
import com.PlannerMate.ui.composables.CircularImageFromResource

@Composable
fun OptionsScreen(
    clientUiState: LoginUiState,
    onNavigateToProfile: () -> Unit

) {
    var currentPassword by remember { mutableStateOf("") }
    var newPassword by remember { mutableStateOf("") }
    var repeatPassword by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Nombre y contacto del cliente
        Row(verticalAlignment = Alignment.CenterVertically) {
            CircularImageFromResource(
                idImageResource = R.drawable.user,
                contentDescriptionription = "image",
                size = 100.dp
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(
                    text = clientUiState.name,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = clientUiState.contact
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        // Texto y campo para la contraseña actual
        Text("Contraseña Actual")
        TextField(
            value = currentPassword,
            onValueChange = { currentPassword = it },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        // Texto y campos para la nueva contraseña y repetición
        Text("Nueva Contraseña")
        TextField(
            value = newPassword,
            onValueChange = { newPassword = it },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text("Repetir Contraseña")
        TextField(
            value = repeatPassword,
            onValueChange = { repeatPassword = it },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        // Botones para cancelar y guardar cambios
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            Button(onClick = { onNavigateToProfile }) {
                Text("Cancelar")
            }
            Spacer(modifier = Modifier.width(16.dp))
            Button(
                onClick = { onNavigateToProfile},
                enabled = newPassword.isNotEmpty() && newPassword == repeatPassword
            ) {
                Text("Guardar Cambios")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ChangePasswordPreview() {
    val clientUiState = LoginUiState()

    OptionsScreen(
        clientUiState = clientUiState,
        onNavigateToProfile = {}
    )
}
