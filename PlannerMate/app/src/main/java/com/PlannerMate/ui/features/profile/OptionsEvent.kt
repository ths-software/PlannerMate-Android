package com.PlannerMate.ui.features.profile

import com.PlannerMate.ui.features.appointment.AppointmentEvent

sealed interface OptionsEvent{

    data class OnClickBack(val onNavigateProfile:(()->Unit)?): OptionsEvent
}