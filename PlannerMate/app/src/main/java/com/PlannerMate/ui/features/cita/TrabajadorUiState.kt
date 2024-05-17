package com.PlannerMate.ui.features.cita

data class TrabajadorUiState(
    val idTrabajador : Int,
    val nombre : String,
    val contacto : String
)
{
    constructor() : this(
        idTrabajador = 0,
        nombre = "",
        contacto = ""
    )
}
