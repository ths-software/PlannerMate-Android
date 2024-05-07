package com.PlannerMate.data.services

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface CitaApiService {
    @GET("citas")
    @Headers("Accept: application/json", "Content-Type: application/json")
    suspend fun citas(): Response<List<CitaApi>>

    @GET("citas/{id}")
    @Headers("Accept: application/json", "Content-Type: application/json")
    suspend fun citaOfClient(@Path("id") id : Int): Response<CitaApi>

    @PUT("citas/{id}")
    @Headers("Accept: application/json", "Content-Type: application/json")
    suspend fun update(@Path("id") id: Int, @Body c : CitaApi) : Response<RespuestaApi>

    @POST("citas")
    @Headers("Accept: application/json", "Content-Type: application/json")
    suspend fun insert(@Body c : CitaApi) : Response<RespuestaApi>

    @DELETE("citas/{id}")
    @Headers("Accept: application/json", "Content-Type: application/json")
    suspend fun delete(@Path("id") id : Int) : Response<RespuestaApi>
    

}
