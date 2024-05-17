package com.PlannerMate.di

import com.PlannerMate.data.services.CitaApiService
import com.PlannerMate.data.services.ClienteApiService
import com.PlannerMate.data.services.TrabajadorApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.HEADERS
        val timeout = 10L
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(timeout, TimeUnit.SECONDS)
            .readTimeout(timeout, TimeUnit.SECONDS)
            .writeTimeout(timeout, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient
    ) : Retrofit = Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl("http://plannermate.westeurope.cloudapp.azure.com:8080/PlannerAPIRest/datos/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun provideCitasService(
        retrofit: Retrofit
    ) : CitaApiService = retrofit.create(CitaApiService::class.java)

    @Provides
    @Singleton
    fun provideClientesService(
        retrofit: Retrofit
    ) : ClienteApiService = retrofit.create(ClienteApiService::class.java)

    @Provides
    @Singleton
    fun provideTrabajadoresService(
        retrofit: Retrofit
    ) : TrabajadorApiService = retrofit.create(TrabajadorApiService::class.java)

}