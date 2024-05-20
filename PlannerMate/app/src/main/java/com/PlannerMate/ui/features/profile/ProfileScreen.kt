package com.PlannerMate.ui.features.profile

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.PlannerMate.R

import com.PlannerMate.models.Cita
import com.PlannerMate.models.Cliente
import com.PlannerMate.models.Trabajador
import com.PlannerMate.ui.features.cita.CitaEvent
import com.PlannerMate.ui.features.cita.CitaUiState
import com.PlannerMate.ui.features.login.LoginUiState
import com.pmdm.tienda.ui.composables.CircularImageFromResource
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
    ) {
        // Parte superior con fondo de color clarito
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFE3F2FD)) // Color clarito
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            CircularImageFromResource(
                idImageResource = R.drawable.usuario,
                contentDescription = "image",
                size = 100.dp
            )

            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = clienteUiState.nombre,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
            Text(
                text = clienteUiState.contacto,
                fontSize = 16.sp
            )
        }

        // Parte inferior con fondo blanco
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(16.dp)
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            // Si hay una cita actual, la mostramos con el botón para eliminar
            if (!citaActual?.fecha.equals("")) {
                var fechaFormateada = ""
                try {
                    val fechaSinUTC = citaActual?.fecha?.replace("\\[UTC\\]$".toRegex(), "")
                    Log.d("_CITA", "Fecha sin [UTC]: $fechaSinUTC")

                    val formatter =
                        DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssX", Locale.ENGLISH)
                    val fecha = LocalDateTime.parse(fechaSinUTC, formatter)

                    val formatterSalida =
                        DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm", Locale.ENGLISH)
                    fechaFormateada = formatterSalida.format(fecha)
                } catch (ex: Exception) {
                    Log.d("_CITA", "$ex")
                }

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    shape = RoundedCornerShape(8.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
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

            // Botón para cerrar sesión y opciones
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
}

@Preview(showBackground = true)
@Composable
fun ProfilePreview() {
    val clienteUiState = LoginUiState()
    val trabajador = Trabajador(0, "nombreTrabajador", "contactTrabajador")
    val cliente = Cliente(
        clienteUiState.idCliente,
        clienteUiState.nombre,
        clienteUiState.contacto,
        clienteUiState.password
    )

    val citaActual = Cita(0, nombre = "", "", fecha = "", descripcion = "", trabajador, cliente)

    ProfileScreen(
        clienteUiState = clienteUiState,
        citaActual = null,
        onLogout = {},
        onCitaEvent = {},
        onAgregarCita = {},
        onOpciones = {}
    )
}
