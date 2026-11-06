package com.PlannerMate.models

import com.google.gson.annotations.SerializedName

data class Employee(
    @SerializedName("idTrabajador")
    val employeeId : Int,
    @SerializedName("nombre")
    val name : String,
    @SerializedName("contacto")
    val contact : String
)
{
    constructor() : this (
        employeeId = 0,
        name = "",
        contact = ""
    )
}