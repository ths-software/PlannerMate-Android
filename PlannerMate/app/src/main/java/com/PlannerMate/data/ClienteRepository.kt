package com.PlannerMate.data

import com.PlannerMate.data.services.ClienteServiceImplementation
import com.PlannerMate.models.Cita
import com.PlannerMate.models.Cliente
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ClienteRepository @Inject constructor(
    private val clienteService : ClienteServiceImplementation
) {

    suspend fun get(): List<Cliente> = withContext(Dispatchers.IO) {
        clienteService.get().map { it.toCliente() }
    }

    suspend fun getOne(id: Int): Cliente = withContext(Dispatchers.IO) {
        clienteService.getOne(id).toCliente()
    }

}