package com.PlannerMate.ui.features.profile

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Web
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

import com.PlannerMate.models.Appointment
import com.PlannerMate.models.Client
import com.PlannerMate.models.Employee
import com.PlannerMate.ui.features.appointment.AppointmentEvent
import com.PlannerMate.ui.features.appointment.AppointmentUiState
import com.PlannerMate.ui.features.login.LoginUiState
import com.PlannerMate.ui.composables.CircularImageFromResource
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

@Composable
fun ProfileScreen(
    clientUiState: LoginUiState,
    appointmentActual: AppointmentUiState?,
    onLogout: () -> Unit,
    onAppointmentEvent: (AppointmentEvent) -> Unit,
    onAgregarAppointment: () -> Unit,
    onOptions: () -> Unit
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
                idImageResource = R.drawable.user,
                contentDescriptionription = "image",
                size = 100.dp
            )

            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = clientUiState.name,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
            Text(
                text = clientUiState.contact,
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
            if (!appointmentActual?.date.equals("")) {
                var dateFormateada = ""
                try {
                    val dateSinUTC = appointmentActual?.date?.replace("\\[UTC\\]$".toRegex(), "")
                    Log.d("_CITA", "Fecha sin [UTC]: $dateSinUTC")

                    val formatter =
                        DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssX", Locale.ENGLISH)
                    val date = LocalDateTime.parse(dateSinUTC, formatter)

                    val formatterSalida =
                        DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm", Locale.ENGLISH)
                    dateFormateada = formatterSalida.format(date)
                } catch (ex: Exception) {
                    Log.d("_CITA", "$ex")
                }

                Text(text = "Cita actual:")

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

                            Text(text = "${appointmentActual?.description} $dateFormateada")
                            Text(text = "con ${appointmentActual?.employee?.name}")
                        }
                        IconButton(onClick = { onAppointmentEvent(AppointmentEvent.OnDeleteAppointment) }) {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescriptionription = "Delete Icon"
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
                    Button(onClick = { onAgregarAppointment() }) {
                        Text("Agregar Cita")
                    }
                }
            }

            Spacer(modifier = Modifier.height(50.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth().padding(16.dp)
            ) {
                Icon(Icons.Default.Phone, contentDescriptionription = "Teléfono")
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Teléfono: 614999999")
            }


            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth().padding(16.dp)
            ) {
                Icon(Icons.Default.Email, contentDescriptionription = "Email")
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Email: peluquería@hotmail.tk")
            }


            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth().padding(16.dp)
            ) {
                Icon(Icons.Default.Web, contentDescriptionription = "Página web")
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Página web: www.LaMejorWeb.com/home")
            }
            Spacer(modifier = Modifier.height(50.dp))



            // Botón para cerrar sesión y opciones
            TextButton(
                onClick = { onOptions() },
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
    val clientUiState = LoginUiState()
    val employee = Employee(0, "nombreTrabajador", "contactTrabajador")
    val client = Client(
        clientUiState.clientId,
        clientUiState.name,
        clientUiState.contact,
        clientUiState.password
    )

    val appointmentActual = Appointment(0, name = "", "", date = "", description = "", employee, client)

    ProfileScreen(
        clientUiState = clientUiState,
        appointmentActual = null,
        onLogout = {},
        onAppointmentEvent = {},
        onAgregarAppointment = {},
        onOptions = {}
    )
}
