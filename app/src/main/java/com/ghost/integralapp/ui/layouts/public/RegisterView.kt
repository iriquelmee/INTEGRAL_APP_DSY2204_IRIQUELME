package com.ghost.integralapp.ui.layouts.public

import androidx.compose.ui.Alignment
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.input.PasswordVisualTransformation
import com.google.firebase.auth.FirebaseAuth

@Composable
fun RegisterForm(onRegisterSuccess: (String) -> Unit) {

    //definicion de parametros formulario instanciando en vacio y falso para la asignacion por defecto. Ignacio Riquelme

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var notification by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }

    val auth = FirebaseAuth.getInstance()

    //Objeto conbtenedor que mostrara componentes UI. Ignacio Riquelme
    Box(
        //asignacion de parametros basicos paraa este contenedor
        modifier = Modifier.fillMaxSize().padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        //Se asigna objeto de tipo columna para mostrar contenido uno debajo de otro, de componentes UI
        Column(

            //Parametros basicos Columna - Ignacio Riquelme
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.width(320.dp).padding(16.dp)
        ) {
            //campo texto  tipo label - Ignacio Riquelme
            Text(
                text = "Registranate aquí",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            //ccampo texto mail - Ignacio Riquelme
            TextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
            )

            //campo txto tipo password 1 - Ignacio Riquelme
            TextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
            )

            //campo txto tipo password 2 - Ignacio Riquelme
            TextField(
                value = confirmPassword,
                onValueChange = { confirmPassword = it },
                label = { Text("Confirm Password") },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
            )

            //boton con manejo de evento onClick para realizar registro de credenciales. - Ignacio Riquelme
            Button(
                onClick = {

                    //validando campos que ninguno este vacio - Ignacio Riquelme
                    if (email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                        notification = "todos los campos son requeridos"
                        return@Button
                    }

                    //validacion de password sean iguales - Ignacio Riquelme
                    if (password != confirmPassword) {
                        notification = "contraseña no coinciden."
                        return@Button
                    }
                    isLoading = true

                    //realizando llamada de autenticacion firebaasae absica de email - Ignacio Riquelme
                    auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                        isLoading = false
                        if (task.isSuccessful) {
                            notification = "Registro Exitoso."
                            //se pasa como parametro email a vista login para manipularlo dentro de esa vista. - Ignacio Riquelme
                            onRegisterSuccess(email)
                        } else {
                            // se asigna respuesta de tipo notificacion en caso de haber problaas de autenticacion con firebas - Ignacio Riquelme
                            notification = "Registro Fallido: ${task.exception?.localizedMessage}"
                        }
                    }
                },
                enabled = !isLoading,
                modifier = Modifier.fillMaxWidth()
            ) {
                if (isLoading) {
                    Text("Registrando")
                } else {
                    Text("Registrar")
                }
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
fun RegisterFormPreview() {
    RegisterForm(onRegisterSuccess = {})
}