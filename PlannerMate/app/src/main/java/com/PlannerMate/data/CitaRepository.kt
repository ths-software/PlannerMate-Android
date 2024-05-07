package com.PlannerMate.data

import com.PlannerMate.data.services.CitaApiService
import com.PlannerMate.data.services.CitaServiceImplementation
import com.PlannerMate.models.Cita
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CitaRepository @Inject constructor(
    private val citaService: CitaServiceImplementation
) {
    suspend fun get(): List<Cita> = withContext(Dispatchers.IO) {
        citaService.get().map { it.toCita() }
    }

    suspend fun get(id: Int): Cita = withContext(Dispatchers.IO) {
        citaService.getOne(id).toCita()
    }

    suspend fun insert(cita: Cita) = withContext(Dispatchers.IO) {
        citaService.insert(cita.toCitaApi())
    }
    suspend fun update(cita: Cita) = withContext(Dispatchers.IO) {
        citaService.update(cita.idCita,cita.toCitaApi())
    }
    suspend fun delete(id: Int) = withContext(Dispatchers.IO) {
        citaService.delete(id)
    }


}