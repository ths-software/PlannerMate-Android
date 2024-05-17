package com.PlannerMate.ui.features.profile

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.PlannerMate.R
import com.PlannerMate.models.Cita
import com.PlannerMate.models.Cliente
import com.PlannerMate.models.Trabajador
import com.PlannerMate.ui.features.cita.CitaEvent
import com.PlannerMate.ui.features.cita.CitaUiState
import com.PlannerMate.ui.features.login.LoginUiState
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

@Composable
fun ProfileScreen(
    clienteUiState: LoginUiState,
    citaActual: CitaUiState?,
    onLogout: () -> Unit,
    onCitaEvent: (CitaEvent) -> Unit,
    onAgregarCita: () -> Unit,
    onOpciones: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Nombre y contacto del cliente
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painter = painterResource(id = R.drawable.ic_user),
                contentDescription = "User Icon",
                modifier = Modifier.size(48.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(
                    text = clienteUiState.nombre,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = clienteUiState.contacto
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        // Si hay una cita actual, la mostramos con el botón para eliminar
        if (!citaActual?.fecha.equals("")) {

            var fechaFormateada = ""
            try {
                val fechaSinUTC = citaActual?.fecha?.replace("\\[UTC\\]$".toRegex(), "")
                Log.d("_CITA", "Fecha sin [UTC]: $fechaSinUTC")

                val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssX", Locale.ENGLISH)
                val fecha = LocalDateTime.parse(fechaSinUTC, formatter)

                val formatterSalida = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm", Locale.ENGLISH)
                fechaFormateada = formatterSalida.format(fecha)
            } catch (ex : Exception) {
                Log.d("_CITA", "$ex")
            }

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Row(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(text = "Cita actual:")
                        Text(text = " ${citaActual?.descripcion} $fechaFormateada")
                        Text(text = "con ${citaActual?.trabajador?.nombre}")
                    }
                    IconButton(onClick = { onCitaEvent(CitaEvent.OnEliminarCita) }) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Delete Icon"
                        )
                    }
                }
            }
        } else {
            // Si no hay cita actual, mostramos un mensaje y el botón para agregar una nueva cita
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Sin citas solicitadas")
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = { onAgregarCita() }) {
                    Text("Agregar Cita")
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Spacer(modifier = Modifier.height(16.dp))
        Spacer(modifier = Modifier.height(16.dp))
        // Botón para cerrar sesión
        TextButton(
            onClick = { onOpciones() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Opciones", color = MaterialTheme.colorScheme.primary)
        }
        Spacer(modifier = Modifier.height(16.dp))
        TextButton(
            onClick = { onLogout() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Cerrar Sesión", color = MaterialTheme.colorScheme.primary)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfilePreview() {
    val clienteUiState = LoginUiState()
    val trabajador = Trabajador(0,"nombreTrabajador","contactTrabajador")
    val cliente = Cliente(clienteUiState.idCliente,clienteUiState.nombre,clienteUiState.contacto,clienteUiState.password)

    val citaActual = Cita(0, nombre = "","", fecha = "", descripcion = "",
        trabajador,cliente)

    ProfileScreen(
        clienteUiState = clienteUiState,
        citaActual = null,
        onLogout = {},
        onCitaEvent = {},
        onAgregarCita = {},
        {}
    )
}
