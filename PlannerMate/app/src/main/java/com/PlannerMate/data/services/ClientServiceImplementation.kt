package com.PlannerMate.data.services

import android.util.Log
import javax.inject.Inject

class ClientServiceImplementation @Inject constructor(
    private val clientService : ClientApiService
) {
    private val logTag:String = "OkHttp"

    suspend fun get() : List<ClientApi>
    {
        val errorMessage = "No se ha podido obtener los clientes"
        try {
            val response = clientService.clients()
            if(response.isSuccessful)
            {
                Log.d(logTag, response.toString())
                val data = response.body()
                return data ?: throw ApiServicesException("No hay datos")
            }
            else
            {
                val body = response.errorBody()?.string()
                Log.d(logTag, "$errorMessage (código ${response.code()}): $this\n${body}")
                throw ApiServicesException(errorMessage)
            }
        }catch (e:Exception)
        {
            Log.e(logTag, "Error: ${e.localizedMessage}")
            throw ApiServicesException(errorMessage)

        }
    }

    suspend fun getOne(id : Int) : ClientApi{
        val errorMessage = "No se ha podido obtener el cliente"
        try {
            val response = clientService.client(id)
            if(response.isSuccessful)
            {
                Log.d(logTag, response.toString())
                val data = response.body()
                return data ?: throw ApiServicesException("No hay datos")
            }
            else
            {
                val body = response.errorBody()?.string()
                Log.d(logTag, "$errorMessage (código ${response.code()}): $this\n${body}")
                throw ApiServicesException(errorMessage)
            }
        }catch (e:Exception)
        {
            Log.e(logTag, "Error: ${e.localizedMessage}")
            throw ApiServicesException(errorMessage)

        }
    }


}