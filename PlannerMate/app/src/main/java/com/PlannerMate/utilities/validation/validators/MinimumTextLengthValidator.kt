package com.PlannerMate.utilities.validation.validators

import com.PlannerMate.utilities.validation.Validation
import com.PlannerMate.utilities.validation.Validator

class MinimumTextLengthValidator(
    val minimumLength: Int,
    val error: String = "El texto debe mayor o igual a ${minimumLength}"
) : Validator<String> {
    override fun validate(text: String): Validation {
        return object : Validation {
            override val hasError: Boolean
                get() = text.length < minimumLength
            override val errorMessage: String
                get() = error
        }
    }
}
