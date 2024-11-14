package com.clonenubank

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable
import com.clonenubank.ui.screens.home.HomeScreen
import com.clonenubank.ui.theme.CloneNubankTheme
import com.clonenubank.ui.InitialScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CloneNubankTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "tela_inicial") {
                    composable("tela_inicial") {
                        InitialScreen(
                            nome = "Cliente",
                            onVerSaldoClick = { navController.navigate("tela_detalhes") }
                        )
                    }
                    composable("tela_detalhes") {
                        HomeScreen()
                    }
                }
            }
        }
    }
}
