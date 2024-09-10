package com.ghost.integralapp.ui.layouts.public

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ghost.integralapp.ui.theme.Blue40
import com.ghost.integralapp.ui.theme.BlueGrey80
import com.google.firebase.auth.FirebaseAuth

@Composable
fun LoginForm(
    onLoginSuccess: (Any?) -> Unit,
    navController: NavController
) {

    //asignacion para formulario login basico . -Ignacio Riquelme
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var notification by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }

    //se instancia objeto firebasae, que manipulara autenticacion - Ignaacio Riquelme
    val auth = FirebaseAuth.getInstance()

    Box(
        //asignacoin basica contenedor de componentes pading y centrado de posicionamiento - Ignacio Riquelme
        modifier = Modifier.fillMaxSize().padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        //se define un objeto columna para ordenar labels e inputs o botones - Ignacio Riquelme
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.width(320.dp).padding(16.dp)
        ) {


            Text(
                text = "Integral App",
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.padding(bottom = 18.dp),
                color = BlueGrey80
            )
            //Campo texto login - Ignacio Riquelme
            Text(
                text = "Autentícate aqui.",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(bottom = 16.dp),
                color = BlueGrey80
            )

            //campo texto usuario mail  - Ignacio Riquelme
            TextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
            )

            //campo texto pasaword 1 - ignaacio Riquelme
            TextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),


            )

            //boton
            Button(
                onClick = {
                    //validando campo mail y pasword no vacios - Ignacio Riquelme
                    if (email.isEmpty() || password.isEmpty()) {
                        notification = "Usuario y contraseña deben ser ingresaadas !"
                        return@Button
                    }
                    isLoading = true
                    auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                        isLoading = false
                        if (task.isSuccessful) {
                            notification = "Login Exitoso!"
                            onLoginSuccess(email)
                        } else {
                            notification = "Login Fallido: ${task.exception?.localizedMessage}"
                        }
                    }
                },
                enabled = !isLoading,
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Blue40)

            ) {
                if (isLoading) {
                    Text("Realizando Validacion Credenciales")
                } else {
                    Text("Login")
                }
            }
            Button(
                onClick = {
                    navController.navigate("register")
                },
                modifier = Modifier.padding(top = 16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Blue40)

            ) {
                Text("Registro")
            }
            if (notification.isNotEmpty()) {
                Text(
                    text = notification,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.padding(top = 16.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginFormPreview() {
    Surface(color = MaterialTheme.colorScheme.background) {
        LoginForm(onLoginSuccess = {}, navController = rememberNavController())
    }
}