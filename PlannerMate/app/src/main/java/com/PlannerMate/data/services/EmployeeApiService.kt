package com.PlannerMate.data.services

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface EmployeeApiService {

    @GET("trabajadores")
    @Headers("Accept: application/json", "Content-Type: application/json")
    suspend fun employees() : Response<List<EmployeeApi>>

    @GET("trabajadores/{id}")
    @Headers("Accept: application/json", "Content-Type: application/json")
    suspend fun employee() : Response<EmployeeApi>


}