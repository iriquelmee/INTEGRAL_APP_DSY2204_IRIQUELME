package com.ghost.integralapp

import androidx.compose.ui.test.assertAll
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.navigation.compose.rememberNavController
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.ghost.integralapp.ui.layouts.public.LoginForm
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = composeTestRule.activity.applicationContext
        assertEquals("com.ghost.integralapp", appContext.packageName)
    }

    @Test
    fun testLogin_success() {
        // prueba by ignacio riquelme - login
        // ingresando email
        composeTestRule.onNodeWithTag("mail").performTextInput("ieriquelme97@gmail.com")

        // ingresando pass
        composeTestRule.onNodeWithTag("pass").performTextInput("Inter123456")

        // ejecutando evento click boton login testing
        composeTestRule.onNodeWithTag("LoginButton").performClick()

        // espera a respuesta de componente firebase
        composeTestRule.waitUntil(timeoutMillis = 5000) {
            // Revisamoos el campo para validacion de prueba
            composeTestRule.onAllNodesWithTag("usuarioTest").fetchSemanticsNodes().isNotEmpty()
        }
    }
    @Test
    fun testLogin_fail() {
        // prueba by ignacio riquelme - login
        // ingresando email
        composeTestRule.onNodeWithTag("mail").performTextInput("ieriquelme97@gmail.com")

        // ingresando pass
        composeTestRule.onNodeWithTag("pass").performTextInput("Inter123456")

        // ejecutando evento click boton login testing
        composeTestRule.onNodeWithTag("LoginButton").performClick()

        // espera a respuesta de componente firebase
        composeTestRule.waitUntil(timeoutMillis = 5000) {
            // Revisamoos el campo para validacion de prueba
            composeTestRule.onAllNodesWithTag("usuarioTest").fetchSemanticsNodes().isEmpty()
        }
    }

    @Test
    fun testRegister_success() {
        // Pruebas by Ignacio Roiquelme - registro
        // se ejecuta evento boton click para navegar a componente registro
        composeTestRule.onNodeWithTag("register").performClick()
        // Asignando mail en campo texto
        composeTestRule.onNodeWithTag("mail").performTextInput("t3@gmail.com")

        // asignando pass 1
        composeTestRule.onNodeWithTag("pass1").performTextInput("Inter1234567")

        // asignando pass 2
        composeTestRule.onNodeWithTag("pass2").performTextInput("Inter1234567")

        // realisando reguistro
        composeTestRule.onNodeWithTag("registroClick").performClick()

        // espera a respuesta de componente firebase con registro validado
        composeTestRule.waitUntil(timeoutMillis = 5000) {
            // Revisamoos el campo para validacion de prueba
            composeTestRule.onAllNodesWithTag("usuarioTest").fetchSemanticsNodes().isNotEmpty()
        }
    }

    @Test
    fun testRegister_fail() {
        // Pruebas by Ignacio Roiquelme - registro redundante
        // se ejecuta evento boton click para navegar a componente registro
        composeTestRule.onNodeWithTag("register").performClick()
        // Asignando mail en campo texto
        composeTestRule.onNodeWithTag("mail").performTextInput("ieriquelme97@gmail.com")

        // asignando pass 1
        composeTestRule.onNodeWithTag("pass1").performTextInput("Inter123456")

        // asignando pass 2
        composeTestRule.onNodeWithTag("pass2").performTextInput("Inter123456")

        // realizando registro
        composeTestRule.onNodeWithTag("registroClick").performClick()

        // espera a respuesta de componente firebase con registro validado
        composeTestRule.waitUntil(timeoutMillis = 5000) {
            // Revisamoos el campo para validacion de prueba
            composeTestRule.onAllNodesWithTag("registroTestFail").fetchSemanticsNodes().isNotEmpty()
        }
    }

}