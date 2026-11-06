package com.PlannerMate.models

import com.PlannerMate.data.services.ClientApi
import com.PlannerMate.data.services.EmployeeApi

data class Appointment(
    val appointmentId: Int,
    val name : String,
    val contact : String,
    val date : String,
    val description : String,
    val employee : Employee,
    val client : Client
)
