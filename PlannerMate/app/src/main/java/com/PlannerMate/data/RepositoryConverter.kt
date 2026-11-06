package com.PlannerMate.data

import com.PlannerMate.data.services.AppointmentApi
import com.PlannerMate.data.services.ClientApi
import com.PlannerMate.data.services.EmployeeApi
import com.PlannerMate.models.Appointment
import com.PlannerMate.models.Client
import com.PlannerMate.models.Employee

fun Appointment.toAppointmentApi() = AppointmentApi(
    this.appointmentId,
    this.name,
    this.contact,
    this.date,
    this.description,
    this.employee,
    this.client
)

fun AppointmentApi.toAppointment() = Appointment(
    this.appointmentId,
    this.name,
    this.contact ?: "",
    this.date,
    this.description,
    this.employee ?: Employee(), // Asigna un Trabajador vacío si trabajador es nulo
    this.client ?: Client() // Asigna un Cliente vacío si cliente es nulo
)


fun Client.toClientApi() = ClientApi(
    this.clientId,
    this.name,
    this.contact,
    this.password
)

fun ClientApi.toClient() = Client(
    this.clientId,
    this.name,
    this.contact,
    this.password
)

fun Employee.toEmployeeApi() = EmployeeApi(
    this.employeeId,
    this.name,
    this.contact
)

fun EmployeeApi.toEmployee(): Employee {
    val contactNotNull = contact ?: ""
    return Employee(employeeId, name, contactNotNull)
}