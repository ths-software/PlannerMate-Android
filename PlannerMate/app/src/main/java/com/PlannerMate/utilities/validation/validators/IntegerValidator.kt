package com.PlannerMate.utilities.validation.validators

import android.util.Range
import com.PlannerMate.utilities.validation.Validation
import com.PlannerMate.utilities.validation.Validator

class IntegerValidator(
    val range: Range<Int> = Range(0, Int.MAX_VALUE),
    val error: String = "Se espera un valor entero"
) : Validator<String> {
    override fun validate(text: String): Validation {
        return object : Validation {
            override val hasError: Boolean
                get() = !text.matches(Regex("^[+-]?[0-9]+$"))
                        ||
                        !range.contains(text.toInt())
            override val errorMessage: String
                get() = error
        }
    }
}