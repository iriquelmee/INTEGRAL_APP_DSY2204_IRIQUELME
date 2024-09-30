package com.ghost.integralapp.ui.layouts.private

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import com.ghost.integralapp.ui.theme.Blue40
import com.ghost.integralapp.ui.theme.DarkBlue40

@Composable
fun HomeView(userEmail: String) {
    //Se asigna objeto tipo columna asignanndo posiciones iniciales - Ignnacio Riquelme
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        //Texto informativo de bienvenida - Ignacio Riquelme
        Text(
            text = "Bienvenid@ a Intergal App",
            style = MaterialTheme.typography.headlineMedium.copy(
                color = DarkBlue40,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp
            ),
            modifier = Modifier.padding(bottom = 8.dp)
        )

        //Texto Bienvenida
        Text(
            text = "¡ Estamos felices de tenerte con nosotros !",
            style = MaterialTheme.typography.bodyMedium.copy(
                color = Color.Gray,
                fontStyle = FontStyle.Italic,
                fontSize = 18.sp
            ),
            modifier = Modifier.padding(bottom = 16.dp),

        )
        //Mostrando mail de usuario autenticado - Ignacio Riquelme
        Text(
            text = if (userEmail.isNotEmpty()) userEmail else "",
            style = MaterialTheme.typography.bodyLarge.copy(
                color = Color.Black,
                fontWeight = FontWeight.Medium,
                fontSize = 20.sp
            ),
            modifier = Modifier.padding(bottom = 16.dp).testTag("usuarioTest"),
        )

        //mostrando botonera -  Ignacio Riquelme
        ButtonGrid()
    }
}

@Composable
fun ButtonGrid() {
    // se setea estado para mostrar contactos de emergencia - Ignacio Riquelme
    var showEmergencyNumbers by remember { mutableStateOf(false) }

    // numeros de emergencia  en duro
    val emergencyContacts = listOf("133", "131", "132", "134")

    // lista de nombre botones para manipularlos
    val buttonTexts = listOf("Emergencia", "Modulo 2","Modulo 3","Modulo 4")

    val context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        //grid de botones se usa weight para que el grid ocupe el espacio disponible - Ignacio Riquelme
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(16.dp),
            modifier = Modifier.weight(1f).fillMaxWidth()
        ) {
            items(buttonTexts) { text ->
                Button(
                    onClick = {
                        if (text == "Emergencia")
                        {
                            showEmergencyNumbers = !showEmergencyNumbers
                        }
                        else
                        {
                            //se asigna mensaje apropiado para otros botones de momento en mantencion para la siguiente entrega
                            Toast.makeText(context, "Modulo $text en mantencion", Toast.LENGTH_SHORT).show()
                        }
                    },
                    modifier = Modifier.padding(8.dp).size(150.dp).background(Color.Transparent),
                    colors = ButtonDefaults.buttonColors(containerColor = Blue40)
                ) {
                    Text(
                        text = text,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }
        }

        //se valida para mostrar botones de llamada de emergencia de bajo del grid - Ignacio Riquelme
        if (showEmergencyNumbers) {
            Column(
                modifier = Modifier.padding(top = 16.dp).fillMaxWidth().weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                emergencyContacts.forEach { contact ->
                    Button(
                        onClick = {
                            // Llamando al número de emergencia apretado - Igancio Riquelme
                            val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$contact"))
                            context.startActivity(intent)
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
                    ) {
                        Text(text = "Call $contact", color = Color.White, fontSize = 16.sp)
                    }
                }
            }
        }
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