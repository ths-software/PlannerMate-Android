package com.PlannerMate.data.services

import android.util.Log
import javax.inject.Inject


// otro paso más para gestionar errores
//
class CitaServiceImplementation @Inject constructor(
    private val citaService: CitaApiService)
{
    private val logTag:String = "OkHttp" //tag para encontrar el log de los errores con la db

    // todas las citas
    suspend fun get() : List<CitaApi>{
        val mensajeError = "No se ha podido obtener las citas"
        try {
            val response = citaService.citas()
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

    // post, put, delete...
    // una cita, correspondiente al cliente
    suspend fun getOne(id : Int) : CitaApi{
        val mensajeError = "No se ha podido obtener la cita"
        try {
            val response = citaService.citaOfClient(id)
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


    suspend fun insert(cita : CitaApi){
        val mensajeError = "No se ha podido obtener las citas"
        try {
            val response = citaService.insert(cita)
            if(response.isSuccessful)
            {
                Log.d(logTag, response.toString())
                val dato = response.body() ?: throw ApiServicesException("No se ha podido añadir")

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

    suspend fun update(id : Int,cita : CitaApi){
        val mensajeError = "No se ha podido obtener las citas"
        try {
            val response = citaService.update(id, cita)
            if(response.isSuccessful)
            {
                Log.d(logTag, response.toString())
                val dato = response.body() ?: throw ApiServicesException("No existe")

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

    suspend fun delete(id : Int){
        val mensajeError = "No se ha podido obtener las citas"
        try {
            val response = citaService.delete(id)
            if(response.isSuccessful)
            {
                Log.d(logTag, response.toString())
                val dato = response.body() ?: throw ApiServicesException("No existe")

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