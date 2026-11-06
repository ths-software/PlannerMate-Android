package com.PlannerMate.data.services

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface AppointmentApiService {
    @GET("citas")
    @Headers("Accept: application/json", "Content-Type: application/json")
    suspend fun appointments(): Response<List<AppointmentApi>>

    @GET("citas/{id}")
    @Headers("Accept: application/json", "Content-Type: application/json")
    suspend fun appointmentOfClient(@Path("id") id : Int): Response<AppointmentApi>

    @PUT("citas/{id}")
    @Headers("Accept: application/json", "Content-Type: application/json")
    suspend fun update(@Path("id") id: Int, @Body c : AppointmentApi) : Response<ApiResponse>

    @POST("citas")
    @Headers("Accept: application/json", "Content-Type: application/json")
    suspend fun insert(@Body c : AppointmentApi) : Response<ApiResponse>

    @DELETE("citas/{id}")
    @Headers("Accept: application/json", "Content-Type: application/json")
    suspend fun delete(@Path("id") id : Int) : Response<ApiResponse>
    

}
