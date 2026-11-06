package com.PlannerMate.di

import com.PlannerMate.data.services.AppointmentApiService
import com.PlannerMate.data.services.ClientApiService
import com.PlannerMate.data.services.EmployeeApiService
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
        .baseUrl("http://localhost:8080/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun provideAppointmentsService(
        retrofit: Retrofit
    ) : AppointmentApiService = retrofit.create(AppointmentApiService::class.java)

    @Provides
    @Singleton
    fun provideClientsService(
        retrofit: Retrofit
    ) : ClientApiService = retrofit.create(ClientApiService::class.java)

    @Provides
    @Singleton
    fun provideEmployeesService(
        retrofit: Retrofit
    ) : EmployeeApiService = retrofit.create(EmployeeApiService::class.java)

}