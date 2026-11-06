package com.PlannerMate.data.services

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ClientApiService {
    @GET("clientes")
    @Headers("Accept: application/json", "Content-Type: application/json")
    suspend fun clients() : Response<List<ClientApi>>

    @GET("clientes/{id}")
    @Headers("Accept: application/json", "Content-Type: application/json")
    suspend fun client(@Path("id") id: Int) : Response<ClientApi>

}