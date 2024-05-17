package com.PlannerMate.ui.features.profile

import com.PlannerMate.ui.features.cita.CitaEvent

sealed interface OpcionesEvent{

    data class OnClickBack(val onNavigatePerfil:(()->Unit)?): OpcionesEvent
}