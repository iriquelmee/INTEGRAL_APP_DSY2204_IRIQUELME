package com.ghost.integralapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ghost.integralapp.ui.layouts.private.HomeView
import com.ghost.integralapp.ui.layouts.public.LoginForm
import com.ghost.integralapp.ui.layouts.public.RegisterForm

//funcion main principal que ejecuta apliccativo - Ignacio Riquelme
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(color = MaterialTheme.colorScheme.background) {
                //se inicia componente navegacion que orquesta vistas - Ignacio Riquelme
                AppNavHost()
            }
        }
    }
}

//Componente navegacion basico para enrutamiento de vistas - Ignacio Riquelme
@Composable
fun AppNavHost() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            LoginForm(
                onLoginSuccess = { userEmail ->
                    navController.navigate("home/$userEmail") {
                        popUpTo("login") { inclusive = true }
                    }
                },
                navController = navController
            )
        }
        composable("register") {
            RegisterForm(onRegisterSuccess = { userEmail ->
                navController.navigate("home/$userEmail") {
                    popUpTo("register") { inclusive = true }
                }
            })
        }
        composable(
            route = "home/{userEmail}",
            arguments = listOf(navArgument("userEmail") { type = NavType.StringType })
        ) { backStackEntry ->
            val userEmail = backStackEntry.arguments?.getString("userEmail") ?: ""
            HomeView(userEmail = userEmail)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Surface(color = MaterialTheme.colorScheme.background) {
        HomeView(userEmail = "")
    }
}