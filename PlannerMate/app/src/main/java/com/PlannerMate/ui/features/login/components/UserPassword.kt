package com.PlannerMate.ui.features.login.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.PlannerMate.ui.composables.CheckboxWithText
import com.PlannerMate.ui.composables.OutlinedTextFieldEmail
import com.PlannerMate.ui.composables.OutlinedTextFieldPassword

import com.PlannerMate.utilities.validation.Validation


@Composable
fun UserPassword(
    modifier: Modifier,
    loginState: String,
    validationLogin: Validation,
    passwordState: String,
    validationPassword: Validation,
    rememberMeState: Boolean,
    onValueChangeLogin: (String) -> Unit,
    onValueChangePassword: (String) -> Unit,
    onCheckedChanged: (Boolean) -> Unit,
    onClickLogIn: () -> Unit
) {
    Column {
        OutlinedTextFieldEmail(
            modifier = modifier,
            label = "Login",
            emailState = loginState,
            validationState = validationLogin,
            onValueChange = onValueChangeLogin
        )

        OutlinedTextFieldPassword(
            modifier = modifier,
            label = "Password",
            passwordState = passwordState,
            validationState = validationPassword,
            onValueChange = onValueChangePassword
        )

        CheckboxWithText(
            label = "Recordarme",
            checkedState = rememberMeState,
            onCheckedChange = onCheckedChanged
        )

        Button(
            onClick = onClickLogIn,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text("Login")
        }
    }
}
