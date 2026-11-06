package com.PlannerMate.data

import com.PlannerMate.data.services.ClientServiceImplementation
import com.PlannerMate.models.Appointment
import com.PlannerMate.models.Client
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ClientRepository @Inject constructor(
    private val clientService : ClientServiceImplementation
) {

    suspend fun get(): List<Client> = withContext(Dispatchers.IO) {
        clientService.get().map { it.toClient() }
    }

    suspend fun getOne(id: Int): Client = withContext(Dispatchers.IO) {
        clientService.getOne(id).toClient()
    }

}