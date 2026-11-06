package com.PlannerMate.ui.features.login

data class LoginUiState(
    val clientId : Int,
    val name : String,
    val contact: String,
    val password: String,
    val isLoggedIn: Boolean
) {
    constructor() : this(
        clientId = 0,
        name = "",
        contact = "",
        password = "",
        isLoggedIn=false
    )
}


/*
    val idCliente : Int,
    val nombre : String,
    val contacto : String,
    val password : String
 */