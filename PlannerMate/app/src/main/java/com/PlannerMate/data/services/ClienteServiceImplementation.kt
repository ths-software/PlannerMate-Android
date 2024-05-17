package com.PlannerMate.data.services

import android.util.Log
import javax.inject.Inject

class ClienteServiceImplementation @Inject constructor(
    private val clienteService : ClienteApiService
) {
    private val logTag:String = "OkHttp"

    suspend fun get() : List<ClienteApi>
    {
        val mensajeError = "No se ha podido obtener los clientes"
        try {
            val response = clienteService.clientes()
            if(response.isSuccessful)
            {
                Log.d(logTag, response.toString())
                val dato = response.body()
                return dato ?: throw ApiServicesException("No hay datos")
            }
            else
            {
                val body = response.errorBody()?.string()
                Log.d(logTag, "$mensajeError (código ${response.code()}): $this\n${body}")
                throw ApiServicesException(mensajeError)
            }
        }catch (e:Exception)
        {
            Log.e(logTag, "Error: ${e.localizedMessage}")
            throw ApiServicesException(mensajeError)

        }
    }

    suspend fun getOne(id : Int) : ClienteApi{
        val mensajeError = "No se ha podido obtener el cliente"
        try {
            val response = clienteService.cliente(id)
            if(response.isSuccessful)
            {
                Log.d(logTag, response.toString())
                val dato = response.body()
                return dato ?: throw ApiServicesException("No hay datos")
            }
            else
            {
                val body = response.errorBody()?.string()
                Log.d(logTag, "$mensajeError (código ${response.code()}): $this\n${body}")
                throw ApiServicesException(mensajeError)
            }
        }catch (e:Exception)
        {
            Log.e(logTag, "Error: ${e.localizedMessage}")
            throw ApiServicesException(mensajeError)

        }
    }


}