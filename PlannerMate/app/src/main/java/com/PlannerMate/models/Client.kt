package com.PlannerMate.models

import com.PlannerMate.ui.features.login.LoginUiState
import com.google.gson.annotations.SerializedName

data class Client(
    @SerializedName("idCliente")
    val clientId : Int,
    @SerializedName("nombre")
    val name : String,
    @SerializedName("contacto")
    val contact : String,
    val password : String
)
{
    constructor() : this (
        clientId = 0,
        name = "",
        contact = "",
        password = ""
    )
}

fun LoginUiState.toClient() = Client(
    this.clientId ,
    this.name ,
    this.contact ,
    this.password
)