package com.PlannerMate.data.services

import com.google.gson.annotations.SerializedName

data class ApiResponse(
    @SerializedName("respuesta")
    val response: Int,
    @SerializedName("metodo")
    val method: String? = null,
    @SerializedName("tabla")
    val table: String? = null,
    @SerializedName("mensaje")
    val message: String? = null,
    val sqlQuery: String? = null,
    val sqlError: String? = null
)