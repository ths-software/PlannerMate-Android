package com.PlannerMate.ui.features.appointment

import com.PlannerMate.models.Appointment
import com.PlannerMate.models.Client
import com.PlannerMate.models.Employee

data class AppointmentUiState(
    val appointmentId: Int,
    val name : String,
    val contact : String,
    val date : String,
    val description : String,
    val employee : Employee,
    val client : Client

)
{
    constructor() : this(
        appointmentId = 0,
        name = "",
        contact = "",
        date = "",
        description = "",
        employee = Employee(),
        client = Client()
    )
}

fun AppointmentUiState.toAppointment() = Appointment(
    this.appointmentId,
    this.name,
    this.contact,
    this.date,
    this.description,
    this.employee,
    this.client
)