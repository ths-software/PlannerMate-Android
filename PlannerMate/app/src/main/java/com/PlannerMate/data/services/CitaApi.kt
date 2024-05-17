package com.PlannerMate.data.services

import com.PlannerMate.models.Cliente
import com.PlannerMate.models.Trabajador
import com.google.gson.annotations.SerializedName

data class CitaApi (
    val idCita: Int,
    val nombre : String,
    val contacto : String?,
    val fecha : String,
    val descripcion : String,
    val trabajador : Trabajador?,
    val cliente : Cliente?
)