package com.PlannerMate.utilities.validation.validators

import com.PlannerMate.utilities.validation.Validation
import com.PlannerMate.utilities.validation.Validator
import java.util.Locale

class NationalIdValidator(
    val error: String = "El DNI no es válido"
) : Validator<String> {
    override fun validate(text: String): Validation {
        val letters = "TRWAGMYFPDXBNJZSQVHLCKE"
        val nationalId = text.uppercase(Locale.getDefault())
        var hasError = nationalId.length != 9 || !nationalId.matches(Regex("[0-9]{8}[A-Z]"))
        if (!hasError) {
            val letter = nationalId.substring(8, 9)
            val index = Integer.parseInt(nationalId.substring(0, 8)) % 23
            hasError = letters.substring(index, index + 1) != letter
        }
        return object : Validation {
            override val hasError: Boolean
                get() = hasError
            override val errorMessage: String
                get() = error
        }
    }
}