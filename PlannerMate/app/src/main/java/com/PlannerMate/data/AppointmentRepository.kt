package com.PlannerMate.data

import com.PlannerMate.data.services.AppointmentApiService
import com.PlannerMate.data.services.AppointmentServiceImplementation
import com.PlannerMate.models.Appointment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AppointmentRepository @Inject constructor(
    private val appointmentService: AppointmentServiceImplementation
) {
    suspend fun get(): List<Appointment> = withContext(Dispatchers.IO) {
        appointmentService.get().map { it.toAppointment() }
    }

    suspend fun get(id: Int): Appointment = withContext(Dispatchers.IO) {
        appointmentService.getOne(id).toAppointment()
    }

    suspend fun insert(appointment: Appointment) = withContext(Dispatchers.IO) {
        appointmentService.insert(appointment.toAppointmentApi())
    }
    suspend fun update(appointment: Appointment) = withContext(Dispatchers.IO) {
        appointmentService.update(appointment.appointmentId,appointment.toAppointmentApi())
    }
    suspend fun delete(id: Int) = withContext(Dispatchers.IO) {
        appointmentService.delete(id)
    }


}