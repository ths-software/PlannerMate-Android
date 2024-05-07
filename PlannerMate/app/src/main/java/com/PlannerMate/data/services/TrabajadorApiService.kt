package com.PlannerMate.data.services

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface TrabajadorApiService {

    @GET("trabajadores")
    @Headers("Accept: application/json", "Content-Type: application/json")
    suspend fun trabajadores() : Response<List<TrabajadorApi>>

    @GET("trabajadores/{id}")
    @Headers("Accept: application/json", "Content-Type: application/json")
    suspend fun trabajador() : Response<TrabajadorApi>


}