package com.PlannerMate.utilities.validation.validators

import com.PlannerMate.utilities.validation.Validation
import com.PlannerMate.utilities.validation.Validator

class EmailValidator(
    val error: String = "Correo no válido"
) : Validator<String> {
    override fun validate(text: String): Validation {
        return object : Validation {
            override val hasError: Boolean
                get() = !Regex("^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})$").matches(text)
            override val errorMessage: String
                get() = error
        }
    }
}