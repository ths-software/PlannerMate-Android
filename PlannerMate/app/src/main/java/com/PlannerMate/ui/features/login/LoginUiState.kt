package com.PlannerMate.ui.features.login

data class LoginUiState(
    val idCliente : Int,
    val nombre : String,
    val contacto: String,
    val password: String,
    val estaLogeado: Boolean
) {
    constructor() : this(
        idCliente = 0,
        nombre = "",
        contacto = "",
        password = "",
        estaLogeado=false
    )
}


/*
    val idCliente : Int,
    val nombre : String,
    val contacto : String,
    val password : String
 */