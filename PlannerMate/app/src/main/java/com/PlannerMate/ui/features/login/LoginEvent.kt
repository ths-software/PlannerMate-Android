package com.PlannerMate.ui.features.login

sealed interface LoginEvent {
    data class LoginChanged(val contact: String) : LoginEvent
    data class PasswordChanged(val password: String) : LoginEvent
    data class  OnClickLogin(val onNavigateProfile:((contact:String)->Unit)?):LoginEvent
}