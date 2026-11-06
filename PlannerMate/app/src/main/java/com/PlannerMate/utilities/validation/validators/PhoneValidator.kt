package com.PlannerMate.utilities.validation.validators

import com.PlannerMate.utilities.validation.Validation
import com.PlannerMate.utilities.validation.Validator

class PhoneValidator(
    val error: String = "Teléfono no válido"
) : Validator<String> {
    override fun validate(text: String): Validation {
        return object : Validation {
            override val hasError: Boolean
                get() = !Regex("^[0-9 ]{9,18}$").matches(text)
            override val errorMessage: String
                get() = error
        }
    }
}