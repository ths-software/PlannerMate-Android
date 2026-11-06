package com.PlannerMate.data.services

import com.PlannerMate.models.Client
import com.PlannerMate.models.Employee
import com.google.gson.annotations.SerializedName

data class AppointmentApi (
    @SerializedName("idCita")
    val appointmentId: Int,
    @SerializedName("nombre")
    val name : String,
    @SerializedName("contacto")
    val contact : String?,
    @SerializedName("fecha")
    val date : String,
    @SerializedName("descripcion")
    val description : String,
    @SerializedName("trabajador")
    val employee : Employee?,
    @SerializedName("cliente")
    val client : Client?
)