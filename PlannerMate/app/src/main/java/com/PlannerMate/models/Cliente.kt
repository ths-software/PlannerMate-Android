package com.PlannerMate.models

import com.PlannerMate.ui.features.login.LoginUiState

data class Cliente(
    val idCliente : Int,
    val nombre : String,
    val contacto : String,
    val password : String
)
{
    constructor() : this (
        idCliente = 0,
        nombre = "",
        contacto = "",
        password = ""
    )
}

fun LoginUiState.toCliente() = Cliente(
    this.idCliente ,
    this.nombre ,
    this.contacto ,
    this.password
)