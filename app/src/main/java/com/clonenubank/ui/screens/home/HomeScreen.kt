package com.clonenubank.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalanceWallet
import androidx.compose.material.icons.filled.ReceiptLong
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.clonenubank.ui.theme.roxoNubank
import androidx.compose.foundation.lazy.LazyColumn


val num: Int = 5

@Composable
fun HomeScreen() {
    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)) {
        Header()
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(16.dp),
            content = {
                item { Spacer(modifier = Modifier.height(16.dp)) }

                item { SectionConta(saldo = "R$ 3.240,22") }

                item { Spacer(modifier = Modifier.height(16.dp)) }

                item { SectionCartaoDeCredito() }

                item { Spacer(modifier = Modifier.height(16.dp)) }

                item { SectionDescubraMais() }
            }
        )
    }

}

@Composable
fun Header() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(roxoNubank, RoundedCornerShape(bottomEnd = 32.dp, bottomStart = 32.dp))
            .padding(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Nubank",
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.width(24.dp))
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Olá, Cliente",
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )
    }
}

@Composable
fun SectionConta(saldo: String) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "Conta",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            modifier = Modifier.padding(vertical = 8.dp)
        )

        Text(
            text = saldo,
            fontWeight = FontWeight.Bold,
            fontSize = 28.sp,
            color = Color.Black,
            modifier = Modifier.padding(vertical = 4.dp)
        )

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            // Botões de Ação com ícones
            ActionButton("Área Pix", Icons.Filled.AccountBalanceWallet)
            ActionButton("Pagar", Icons.Filled.ReceiptLong)
            ActionButton("Transferir", Icons.Filled.Send)
            ActionButton("Depositar", Icons.Filled.CreditCard)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFF0F0F0), RoundedCornerShape(8.dp))
                .padding(16.dp)
        ) {
            Text(text = "Meus Cartões", fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun ActionButton(texto: String, icon: ImageVector) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier
                .size(50.dp)
                .background(Color(0xFFF0F0F0), shape = CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = texto,
                tint = roxoNubank,
                modifier = Modifier.size(24.dp)
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = texto, fontSize = 12.sp)
    }
}

@Composable
fun SectionCartaoDeCredito() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(16.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = Icons.Filled.ShoppingCart,
                contentDescription = "Cartão de crédito",
                tint = Color.Black
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Cartão de crédito",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Peça seu cartão de crédito sem anuidade e tenha mais controle da sua vida financeira.",
            fontSize = 14.sp
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { /* Ação do botão */ },
            colors = ButtonDefaults.buttonColors(containerColor = roxoNubank),
            shape = RoundedCornerShape(50),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Pedir Cartão", color = Color.White)
        }
    }
}

@Composable
fun SectionDescubraMais() {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "Descubra mais",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            modifier = Modifier.padding(vertical = 8.dp)
        )
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            DescubraMaisItem()
            DescubraMaisItem()
        }
    }
}

@Composable
fun DescubraMaisItem() {
    Column(
        modifier = Modifier
            .width(160.dp)
            .background(Color(0xFFF5F5F5), RoundedCornerShape(8.dp))
            .padding(8.dp)
    ) {
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Portabilidade de salário",
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            modifier = Modifier.padding(vertical = 4.dp)
        )
        Text(
            text = "Sua liberdade financeira começa com você escolhendo...",
            fontSize = 12.sp,
            modifier = Modifier.padding(vertical = 4.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = { /* Ação do botão "Conhecer" */ },
            colors = ButtonDefaults.buttonColors(containerColor = roxoNubank),
            shape = RoundedCornerShape(50),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Conhecer", color = Color.White)
        }
    }
}
