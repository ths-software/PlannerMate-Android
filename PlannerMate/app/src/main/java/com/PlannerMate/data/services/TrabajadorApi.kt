package com.PlannerMate.data.services

import com.google.gson.annotations.SerializedName

data class TrabajadorApi(
    @SerializedName(value = "id_trabajador")
    val idTrabajador : Int,
    val nombre : String,
    val contacto : String
)

