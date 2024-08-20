package com.ghost.integralapp.ui.layouts.private

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun HomeView(userEmail: String) {
    //Se asigna objeto tipo columna asignanndo posiciones iniciales - Ignnacio Riquelme
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        //Texto informativo de vbienvenida - Ignacio Riquelme
        Text(
            text = "Bienvenid@ a Intergal App",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        //Texto Bienvenida
        Text(
            text = "¡ Estamos felices de tenerte con nosotros !",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        //Mostrando mail de usuario autenticado - Ignacio Riquelme
        Text(
            text = if (userEmail.isNotEmpty()) "Usuario: $userEmail" else "",
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HomeViewPreview() {
    Surface(
        color = MaterialTheme.colorScheme.background
    ) {
        HomeView(userEmail = "")
    }
}