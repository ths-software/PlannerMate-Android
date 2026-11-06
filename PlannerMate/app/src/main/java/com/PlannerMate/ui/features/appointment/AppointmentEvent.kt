package com.PlannerMate.ui.features.appointment

import com.PlannerMate.models.Employee
import com.PlannerMate.ui.features.login.LoginEvent

sealed interface AppointmentEvent{

    data class DescriptionChanged(val description : String) : AppointmentEvent

    data class OnClickBookAppointment(val onNavigateProfile:((contact:String)->Unit)?):AppointmentEvent

    data class OnClickBack(val onNavigateProfile:((contact:String)->Unit)?):AppointmentEvent
     object OnDeleteAppointment:AppointmentEvent

    data class DateChanged(val date : String) : AppointmentEvent

    data class EmployeeChanged(val employee : Employee) : AppointmentEvent

}