package com.clonenubank.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.clonenubank.ui.theme.roxoNubank
import com.clonenubank.ui.comum.BotaoComum

@Composable
fun TelaInicialNubank(
    nome: String,
    onVerSaldoClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(roxoNubank)
            .padding(16.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            // Texto de boas-vindas
            Text(
                text = "Olá, $nome!",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Botões de ação
            BotaoComum(texto = "Ver Saldo", onClick = onVerSaldoClick)
            Spacer(modifier = Modifier.height(16.dp))
            BotaoComum(texto = "Meus Cartões", onClick = { /* Ação do botão */ })
        }
    }
}
