package com.PlannerMate.data.services

import com.google.gson.annotations.SerializedName

data class EmployeeApi(
    @SerializedName("idTrabajador")
    val employeeId : Int,
    @SerializedName("nombre")
    val name : String,
    @SerializedName("contacto")
    val contact : String
)
