package com.PlannerMate.data.services

import com.google.gson.annotations.SerializedName

data class ClientApi(
    @SerializedName("idCliente")
    val clientId : Int,
    @SerializedName("nombre")
    val name : String,
    @SerializedName("contacto")
    val contact : String,
    val password : String
)
