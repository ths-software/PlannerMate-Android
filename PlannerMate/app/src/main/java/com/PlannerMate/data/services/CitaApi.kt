package com.PlannerMate.data.services

import com.google.gson.annotations.SerializedName

data class CitaApi (
    @SerializedName(value = "id_cita")
    val idCita: Int,
    val nombre : String,
    val contacto : String,
    val fecha : String,
    val descripcion : String,
    val trabajador : TrabajadorApi,
    val cliente : ClienteApi
)