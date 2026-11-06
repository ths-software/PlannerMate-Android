package com.PlannerMate.data

import android.util.Log
import com.PlannerMate.data.services.EmployeeServiceImplementation
import com.PlannerMate.models.Employee
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class EmployeeRepository @Inject constructor(
    private val employeeService : EmployeeServiceImplementation
) {

    suspend fun get(): List<Employee> = withContext(Dispatchers.IO) {
        val employeesApi = employeeService.get()
        val employees = employeesApi.map { it.toEmployee() }
        employees
    }

    suspend fun get(id: Int): Employee = withContext(Dispatchers.IO) {
        employeeService.getOne(id).toEmployee()
    }

}