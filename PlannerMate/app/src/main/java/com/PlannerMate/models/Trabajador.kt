package com.PlannerMate.models

data class Trabajador(
    val idTrabajador : Int,
    val nombre : String,
    val contacto : String
)
{
    constructor() : this (
        idTrabajador = 0,
        nombre = "",
        contacto = ""
    )
}