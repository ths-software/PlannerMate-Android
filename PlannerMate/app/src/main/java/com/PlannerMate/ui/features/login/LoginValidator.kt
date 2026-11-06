package com.PlannerMate.ui.features.login

import com.PlannerMate.utilities.validation.Validator
import com.PlannerMate.utilities.validation.CompositeValidator
import com.PlannerMate.utilities.validation.validators.EmailValidator
import com.PlannerMate.utilities.validation.validators.MinimumTextLengthValidator
import com.PlannerMate.utilities.validation.validators.NonEmptyTextValidator
import javax.inject.Inject

class LoginValidator  @Inject constructor() : Validator<LoginUiState> {
    var validatorLogin =
        CompositeValidator<String>()
            .add(NonEmptyTextValidator("El login no puede estar vacío"))
            .add(EmailValidator("El correo no es válido"))

    var validatorPassword =
        CompositeValidator<String>()
            .add(NonEmptyTextValidator("El password no puede estar vacío"))
            .add(MinimumTextLengthValidator(2, "El password debe tener como mínimo 8 carácteres"))

    override fun validate(data: LoginUiState): LoginValidationUiState {
        val validationLogin = validatorLogin.validate(data.contact)
        val validationPassword = validatorPassword.validate(data.password)

        return LoginValidationUiState(
            validationLogin = validationLogin,
            validationPassword = validationPassword
        )
    }
}