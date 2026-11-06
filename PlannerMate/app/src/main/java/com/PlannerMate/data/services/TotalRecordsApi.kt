package com.PlannerMate.data.services

import com.google.gson.annotations.SerializedName

data class TotalRecordsApi(
    @SerializedName("tabla")
    val table : String,
    @SerializedName("total_registros")
    val totalRecords : Int
)