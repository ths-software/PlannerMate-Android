package com.PlannerMate.ui.features.login

import com.PlannerMate.utilities.validation.Validation
import com.PlannerMate.utilities.validation.CompositeValidation

data class LoginValidationUiState(
    val validationLogin: Validation = object : Validation {},
    val validationPassword: Validation = object : Validation {}
) : Validation {
    private lateinit var validationComposite: CompositeValidation

    private fun componerValidation(): CompositeValidation {
        validationComposite = CompositeValidation()
            .add(validationLogin)
            .add(validationPassword)
        return validationComposite
    }

    override val hasError: Boolean
        get() = componerValidation().hasError
    override val errorMessage: String?
        get() = validationComposite.errorMessage
}