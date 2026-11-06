package com.PlannerMate.ui.features.appointment

data class EmployeeUiState(
    val employeeId : Int,
    val name : String,
    val contact : String
)
{
    constructor() : this(
        employeeId = 0,
        name = "",
        contact = ""
    )
}
