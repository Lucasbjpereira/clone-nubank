package com.clonenubank.ui.comum

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

// Função Composable para o botão comum
@Composable
fun BotaoComum(
    texto: String,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth() // Faz o botão ocupar toda a largura
            .padding(vertical = 8.dp), // Adiciona um espaçamento vertical
    ) {
        Text(text = texto) // O texto do botão
    }
}
