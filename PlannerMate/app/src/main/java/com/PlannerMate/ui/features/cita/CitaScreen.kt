package com.PlannerMate.ui.features.cita

import android.text.format.DateFormat
import android.text.format.DateUtils
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.PlannerMate.models.Trabajador
import com.PlannerMate.ui.features.login.LoginUiState
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.DatePicker
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.window.Dialog
import com.PlannerMate.utilities.convertMillisToLocalDate
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import java.util.TimeZone


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CitaScreen(
    clienteUiState: LoginUiState,
    citaUiState: CitaUiState,
    trabajadores: List<Trabajador>,
    onCitaEvent: (CitaEvent) -> Unit,
    onNavigateToPerfil: ((contacto: String) -> Unit)? = null
) {
    var expanded by remember { mutableStateOf(false) }

    var fechaSeleccionada by remember { mutableStateOf("") }

    var horaSeleccionada by remember { mutableStateOf("") }


    var trabajadorSeleccionado by remember { mutableStateOf(trabajadores.last()) }

    var isDatePickerVisible by remember { mutableStateOf(false) }
    var isTimePickerVisible by remember { mutableStateOf(false) }

    val datePickerState = rememberDatePickerState()
    val timePickerState = rememberTimePickerState()


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Nombre y contacto del cliente
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = clienteUiState.nombre,
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = clienteUiState.contacto,
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        // Botones para elegir fecha y hora
        Row {
            Button(
                onClick = { isDatePickerVisible = true }
            ) {
                Text("Seleccionar Fecha")
            }
            Spacer(modifier = Modifier.width(16.dp))
            Button(
                onClick = { isTimePickerVisible = true }
            ) {
                Text("Seleccionar Hora")
            }
        }
        Spacer(modifier = Modifier.height(16.dp))

        // Texto para mostrar la fecha y la hora seleccionadas
        Text(
            text = "Fecha seleccionada: $fechaSeleccionada\nHora seleccionada: $horaSeleccionada",
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Cuadro de descripción
        OutlinedTextField(
            value = citaUiState.descripcion,
            onValueChange = {onCitaEvent(CitaEvent.DescripcionChanged(it))},
            label = { Text("Descripción de la cita, ejemplo: tintar") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))


        Box(modifier = Modifier.fillMaxWidth())
        {

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = "Seleccionar trabajador:")
                Spacer(modifier = Modifier.width(10.dp))
                Text(text = trabajadorSeleccionado.nombre)
                Spacer(modifier = Modifier.width(8.dp))
                IconButton(onClick = { expanded = !expanded }) {
                    Icon(
                        imageVector = Icons.Default.MoreVert,
                        contentDescription = "More"
                    )
                }


            }


            // Dropdown para elegir el trabajador
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier.fillMaxWidth()
            ) {
                trabajadores.forEach { trabajador ->
                    DropdownMenuItem(
                        text = { Text(text = trabajador.nombre) },
                        onClick = { trabajadorSeleccionado = trabajador
                        })
                }
            }
        }


        Spacer(modifier = Modifier.height(16.dp))


        // Botones para cancelar y solicitar la cita
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            Button(onClick = { onCitaEvent(CitaEvent.OnClickBack(onNavigateToPerfil)) }) {
                Icon(Icons.Default.Cancel, contentDescription = "Cancelar")
                Text("Cancelar")
            }
            Spacer(modifier = Modifier.width(16.dp))
            Button(
                onClick = {
                    val fechaHora = "$fechaSeleccionada $horaSeleccionada:00"
                    val formatter = SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.getDefault())
                    val parsedDate = formatter.parse(fechaHora)
                    val isoFormatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
                    isoFormatter.timeZone = TimeZone.getTimeZone("UTC")
                    val fechaISO = isoFormatter.format(parsedDate)

                    onCitaEvent(CitaEvent.FechaChanged(fechaISO))


                    onCitaEvent(CitaEvent.TrabajadorChanged(trabajadorSeleccionado))
                    onCitaEvent(CitaEvent.OnClickCitar(onNavigateToPerfil))
                },
                enabled = fechaSeleccionada.isNotBlank() && horaSeleccionada.isNotBlank() && citaUiState.descripcion.isNotBlank()
            ) {
                Icon(Icons.AutoMirrored.Filled.Send, contentDescription = "Solicitar")
                Text("Solicitar")


            }
        }

        // Diálogo para seleccionar fecha
        if (isDatePickerVisible) {
            Dialog(
                onDismissRequest = { isDatePickerVisible = false }
            ) {
                Surface(
                    color = MaterialTheme.colorScheme.surface,
                    modifier = Modifier.padding(16.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .width(300.dp)
                    ) {
                        DatePicker(
                            headline = { Text(text = "Fecha de la cita") },
                            title = { Text(text = "selecciona la fecha") },
                            state = datePickerState,

                            )
                        Spacer(modifier = Modifier.height(16.dp))
                        Button(
                            onClick = {
                                if (datePickerState.selectedDateMillis != null) {
                                    val formatter: java.text.DateFormat =
                                        SimpleDateFormat("dd-MM-yyyy")
                                    val calendar = Calendar.getInstance()
                                    calendar.timeInMillis = datePickerState.selectedDateMillis!!
                                    val fechaString = formatter.format(calendar.time)
                                    fechaSeleccionada = fechaString
                                }
                                isDatePickerVisible = false
                            }
                        ) {
                            Text("Aceptar")
                        }
                    }
                }
            }
        }

        // Diálogo para seleccionar hora
        if (isTimePickerVisible) {
            Dialog(
                onDismissRequest = { isTimePickerVisible = false }
            ) {
                Surface(
                    color = MaterialTheme.colorScheme.surface,
                    modifier = Modifier.padding(16.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .width(300.dp)
                    ) {
                        TimePicker(
                            state = timePickerState
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Button(
                            onClick = {
                                val selectedHourString =
                                    timePickerState.hour.toString().padStart(2, '0')
                                val selectedMinuteString =
                                    timePickerState.minute.toString().padStart(2, '0')
                                horaSeleccionada = "$selectedHourString:$selectedMinuteString"

                                isTimePickerVisible = false
                            }
                        ) {
                            Text("Aceptar")
                        }
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun FillAppointmentPreview() {
    val clienteUiState = LoginUiState()
    val citaUiState = CitaUiState()
    val trabajadores = listOf(
        Trabajador(1, "Trabajador 1", "Contacto 1"),
        Trabajador(2, "Trabajador 2", "Contacto 2"),
        Trabajador(3, "Trabajador 3", "Contacto 3")
    )

    CitaScreen(
        clienteUiState = clienteUiState,
        trabajadores = trabajadores,
        citaUiState = citaUiState,
        onCitaEvent = {}
    )
}

