package com.PlannerMate.ui.features.cita

import com.PlannerMate.models.Cita
import com.PlannerMate.models.Cliente
import com.PlannerMate.models.Trabajador

data class CitaUiState(
    val idCita: Int,
    val nombre : String,
    val contacto : String,
    val fecha : String,
    val descripcion : String,
    val trabajador : Trabajador,
    val cliente : Cliente

)
{
    constructor() : this(
        idCita = 0,
        nombre = "",
        contacto = "",
        fecha = "",
        descripcion = "",
        trabajador = Trabajador(),
        cliente = Cliente()
    )
}

fun CitaUiState.toCita() = Cita(
    this.idCita,
    this.nombre,
    this.contacto,
    this.fecha,
    this.descripcion,
    this.trabajador,
    this.cliente
)