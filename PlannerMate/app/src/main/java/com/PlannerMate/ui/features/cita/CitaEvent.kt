package com.PlannerMate.ui.features.cita

import com.PlannerMate.models.Trabajador
import com.PlannerMate.ui.features.login.LoginEvent

sealed interface CitaEvent{

    data class DescripcionChanged(val desc : String) : CitaEvent

    data class OnClickCitar(val onNavigatePerfil:((contacto:String)->Unit)?):CitaEvent

    data class OnClickBack(val onNavigatePerfil:((contacto:String)->Unit)?):CitaEvent
     object OnEliminarCita:CitaEvent

    data class FechaChanged(val fecha : String) : CitaEvent

    data class TrabajadorChanged(val trabajador : Trabajador) : CitaEvent

}