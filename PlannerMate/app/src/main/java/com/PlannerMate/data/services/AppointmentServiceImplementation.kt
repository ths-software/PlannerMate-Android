package com.PlannerMate.data.services

import android.util.Log
import javax.inject.Inject


// otro paso más para gestionar errores
//
class AppointmentServiceImplementation @Inject constructor(
    private val appointmentService: AppointmentApiService)
{
    private val logTag:String = "OkHttp" //tag para encontrar el log de los errores con la db

    // todas las citas
    suspend fun get() : List<AppointmentApi>{
        val errorMessage = "No se ha podido obtener las citas"
        try {
            val response = appointmentService.appointments()
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

    // post, put, delete...
    // una cita, correspondiente al cliente
    suspend fun getOne(id : Int) : AppointmentApi{
        val errorMessage = "No se ha podido obtener la cita"
        try {
            val response = appointmentService.appointmentOfClient(id)
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


    suspend fun insert(appointment : AppointmentApi){
        val errorMessage = "No se ha podido obtener las citas"
        try {
            val response = appointmentService.insert(appointment)
            if(response.isSuccessful)
            {
                Log.d(logTag, response.toString())
                val data = response.body() ?: throw ApiServicesException("No se ha podido añadir")

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

    suspend fun update(id : Int,appointment : AppointmentApi){
        val errorMessage = "No se ha podido obtener las citas"
        try {
            val response = appointmentService.update(id, appointment)
            if(response.isSuccessful)
            {
                Log.d(logTag, response.toString())
                val data = response.body() ?: throw ApiServicesException("No existe")

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

    suspend fun delete(id : Int){
        val errorMessage = "No se ha podido obtener las citas"
        try {
            val response = appointmentService.delete(id)
            if(response.isSuccessful)
            {
                Log.d(logTag, response.toString())
                val data = response.body() ?: throw ApiServicesException("No existe")

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