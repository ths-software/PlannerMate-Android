package com.PlannerMate.data

import android.util.Log
import com.PlannerMate.data.services.TrabajadorServiceImplementation
import com.PlannerMate.models.Trabajador
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TrabajadorRepository @Inject constructor(
    private val trabajadorService : TrabajadorServiceImplementation
) {

    suspend fun get(): List<Trabajador> = withContext(Dispatchers.IO) {
        val trabajadoresApi = trabajadorService.get()
        val trabajadores = trabajadoresApi.map { it.toTrabajador() }
        trabajadores
    }

    suspend fun get(id: Int): Trabajador = withContext(Dispatchers.IO) {
        trabajadorService.getOne(id).toTrabajador()
    }

}