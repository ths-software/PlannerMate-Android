package com.PlannerMate.models

import com.PlannerMate.data.services.ClienteApi
import com.PlannerMate.data.services.TrabajadorApi

data class Cita(
    val idCita: Int,
    val nombre : String,
    val contacto : String,
    val fecha : String,
    val descripcion : String,
    val trabajador : Trabajador,
    val cliente : Cliente
)
