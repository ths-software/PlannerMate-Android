package com.PlannerMate.data.services

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ClienteApiService {
    @GET("clientes")
    @Headers("Accept: application/json", "Content-Type: application/json")
    suspend fun clientes() : Response<List<ClienteApi>>

    @GET("clientes/{id}")
    @Headers("Accept: application/json", "Content-Type: application/json")
    suspend fun cliente() : Response<ClienteApi>

}