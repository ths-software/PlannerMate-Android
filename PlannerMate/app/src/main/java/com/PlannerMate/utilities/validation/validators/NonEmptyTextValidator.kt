package com.PlannerMate.utilities.validation.validators

import com.PlannerMate.utilities.validation.Validator
import com.PlannerMate.utilities.validation.Validation


class NonEmptyTextValidator(
    val error: String = "El campo no puede estar vacío"
) : Validator<String> {
    override fun validate(text: String): Validation {
        return object : Validation {
            override val hasError: Boolean
                get() = text.isEmpty()
            override val errorMessage: String
                get() = error
        }
    }
}
