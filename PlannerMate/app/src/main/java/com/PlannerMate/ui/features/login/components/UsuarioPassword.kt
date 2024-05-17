package com.pmdm.tienda.ui.features.login.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.pmdm.tienda.ui.composables.CheckboxWithText
import com.pmdm.tienda.ui.composables.OutlinedTextFieldEmail
import com.pmdm.tienda.ui.composables.OutlinedTextFieldPassword

import com.pmdm.tienda.utilities.validacion.Validacion


@Composable
fun UsuarioPassword(
    modifier: Modifier,
    loginState: String,
    validacionLogin: Validacion,
    passwordState: String,
    validacionPassword: Validacion,
    recordarmeState: Boolean,
    onValueChangeLogin: (String) -> Unit,
    onValueChangePassword: (String) -> Unit,
    onCheckedChanged: (Boolean) -> Unit,
    onClickLogearse: () -> Unit
) {
    Column {
        OutlinedTextFieldEmail(
            modifier = modifier,
            label = "Login",
            emailState = loginState,
            validacionState = validacionLogin,
            onValueChange = onValueChangeLogin
        )

        OutlinedTextFieldPassword(
            modifier = modifier,
            label = "Password",
            passwordState = passwordState,
            validacionState = validacionPassword,
            onValueChange = onValueChangePassword
        )

        CheckboxWithText(
            label = "Recordarme",
            checkedState = recordarmeState,
            onCheckedChange = onCheckedChanged
        )

        Button(
            onClick = onClickLogearse,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text("Login")
        }
    }
}
