package com.PlannerMate.utilities.validation.validators

import android.util.Range
import com.PlannerMate.utilities.validation.Validation
import com.PlannerMate.utilities.validation.Validator

class DecimalValidator(
    val range: Range<Double> = Range(0.0, Double.MAX_VALUE),
    val error: String = "Se espera un valor real"
) : Validator<String> {
    override fun validate(text: String): Validation {
        return object : Validation {
            override val hasError: Boolean
                get() = !text.matches(Regex("^[0-9]+(\\.[0-9]+)?$"))
                        ||
                        !range.contains(text.toDouble())
            override val errorMessage: String
                get() = error
        }
    }
}