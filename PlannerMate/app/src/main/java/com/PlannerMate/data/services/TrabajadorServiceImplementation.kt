package com.PlannerMate.data.services

import android.util.Log
import javax.inject.Inject

class TrabajadorServiceImplementation @Inject constructor(
    private val trabajadorService: TrabajadorApiService
) {
    private val logTag:String = "OkHttp"
    suspend fun get() : List<TrabajadorApi>
    {
        val mensajeError = "No se ha podido obtener los trabajadores"
        try {
            val response = trabajadorService.trabajadores()
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

    suspend fun getOne(id : Int) : TrabajadorApi{
        val mensajeError = "No se ha podido obtener el trabajador"
        try {
            val response = trabajadorService.trabajador()
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