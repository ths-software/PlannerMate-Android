package com.PlannerMate.data.services

import android.util.Log
import javax.inject.Inject

class EmployeeServiceImplementation @Inject constructor(
    private val employeeService: EmployeeApiService
) {
    private val logTag:String = "OkHttp"
    suspend fun get() : List<EmployeeApi>
    {
        val errorMessage = "No se ha podido obtener los trabajadores"
        try {
            val response = employeeService.employees()
            if(response.isSuccessful)
            {
                Log.d(logTag,"Respuesta trabajadores: $response.toString()" )
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

    suspend fun getOne(id : Int) : EmployeeApi{
        val errorMessage = "No se ha podido obtener el trabajador"
        try {
            val response = employeeService.employee()
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