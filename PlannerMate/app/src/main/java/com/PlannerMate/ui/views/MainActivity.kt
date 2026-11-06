package com.PlannerMate.ui.views

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.PlannerMate.ui.features.login.LoginScreen
import com.PlannerMate.ui.features.login.LoginViewModel
import com.PlannerMate.ui.navigation.NavHostBase
import com.PlannerMate.ui.theme.ProjectBaseTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            val navController = rememberNavController()
            val loginViewModel: LoginViewModel = hiltViewModel()

            ProjectBaseTheme {

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavHostBase()


//                    LoginScreen(
//                        clienteUiState = loginViewModel.clienteUiState,
//                        validacionLoginUiState = loginViewModel.validacionUiState,
//                        mostrarSnackbarHostState = loginViewModel.mostrarSnackBar,
//                        onLoginEvent = loginViewModel::onLoginEvent,
//                        onMostrarSnackBar = { /*TODO*/ })
                }

            }
        }
    }
}
