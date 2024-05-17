package com.PlannerMate.data.services

import com.google.gson.annotations.SerializedName

data class ClienteApi(
    val idCliente : Int,
    val nombre : String,
    val contacto : String,
    val password : String
)
