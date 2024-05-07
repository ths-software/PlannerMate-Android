package com.PlannerMate.ui.features.login

sealed interface LoginEvent {
    data class LoginChanged(val contacto: String) : LoginEvent
    data class PasswordChanged(val password: String) : LoginEvent
    data class  OnClickLogearse(val onNavigatePerfil:((contacto:String)->Unit)?):LoginEvent
}