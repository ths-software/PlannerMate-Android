package com.PlannerMate.utilities.validation.validators

import com.PlannerMate.utilities.validation.Validation
import com.PlannerMate.utilities.validation.Validator

class MaximumTextLengthValidator(
    val maximumLength: Int,
    val error: String = "El texto debe ser inferior o igual a ${maximumLength}"
) : Validator<String> {
    override fun validate(text: String): Validation {
        return object : Validation {
            override val hasError: Boolean
                get() = text.length > maximumLength
            override val errorMessage: String
                get() = error
        }
    }
}