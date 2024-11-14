package com.clonenubank.ui.screens.login

import android.content.Context
import android.content.SharedPreferences
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.edit
import com.clonenubank.ui.theme.roxoNubank
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(context: Context, onLoginSuccess: () -> Unit) {
    var cpf by remember { mutableStateOf(TextFieldValue("")) }
    var password by remember { mutableStateOf(TextFieldValue("")) }
    var showErrorToast by remember { mutableStateOf(false) }
    var isLoading by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()

    // Referência ao SharedPreferences
    val sharedPreferences = context.getSharedPreferences("login_data", Context.MODE_PRIVATE)

    LaunchedEffect(showErrorToast) {
        if (showErrorToast) {
            Toast.makeText(context, "CPF inválido!", Toast.LENGTH_SHORT).show()
            showErrorToast = false
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(roxoNubank),
        contentAlignment = Alignment.Center
    ) {
        if (isLoading) {
            CircularProgressIndicator(color = Color.White)
        } else {
            Column(
                modifier = Modifier
                    .fillMaxWidth(0.85f)
                    .background(Color.White, shape = androidx.compose.foundation.shape.RoundedCornerShape(8.dp))
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Faça seu login",
                    fontSize = 20.sp,
                    modifier = Modifier.padding(bottom = 24.dp)
                )



                // CPF Input
                Text(text = "CPF", fontSize = 12.sp, color = roxoNubank)
                BasicTextField(
                    value = cpf,
                    onValueChange = { newCpf ->
                        cpf = newCpf.text.applyCpfMask(newCpf.selection.start)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                        .background(Color.Transparent),
                    singleLine = true,
                    decorationBox = { innerTextField ->
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color(0xFFEEEEEE), shape = androidx.compose.foundation.shape.RoundedCornerShape(4.dp))
                                .padding(12.dp)
                        ) {
                            innerTextField()
                        }
                    }
                )

                // Senha Input
                Text(text = "Senha", fontSize = 12.sp, color = roxoNubank)
                BasicTextField(
                    value = password,
                    onValueChange = { newPassword ->
                        password = newPassword.copy(
                            selection = TextRange(newPassword.text.length)
                        )
                    },
                    visualTransformation = PasswordVisualTransformation(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                        .background(Color.Transparent),
                    singleLine = true,
                    decorationBox = { innerTextField ->
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color(0xFFEEEEEE), shape = androidx.compose.foundation.shape.RoundedCornerShape(4.dp))
                                .padding(12.dp)
                        ) {
                            innerTextField()
                        }
                    }
                )

                // Botão "Continuar"
                Button(
                    onClick = {
                        if (isCpfValid(cpf.text)) {
                            coroutineScope.launch {
                                isLoading = true
                                delay(2000) // Simulação de carregamento (2 segundos)
                                saveLoginData(sharedPreferences, cpf.text, password.text)
                                isLoading = false
                                onLoginSuccess() // Navega para a tela inicial
                            }
                        } else {
                            showErrorToast = true
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    enabled = cpf.text.isNotEmpty() && password.text.isNotEmpty(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = roxoNubank,
                        disabledContainerColor = Color(0xFFCCCCCC)
                    )
                ) {
                    Text(text = "CONTINUAR", color = Color.White)
                }
            }
        }
    }
}


// Extensão de função para aplicar a máscara de CPF em uma String
fun String.applyCpfMask(cursorPosition: Int): TextFieldValue {
    val numbers = this.replace(Regex("[^\\d]"), "").take(11)
    val formatted = StringBuilder()
    var adjustedCursorPos = cursorPosition

    for (i in numbers.indices) {
        when (i) {
            3, 6 -> {
                formatted.append('.')
                if (i < cursorPosition) adjustedCursorPos++
            }
            9 -> {
                formatted.append('-')
                if (i < cursorPosition) adjustedCursorPos++
            }
        }
        formatted.append(numbers[i])
    }

    adjustedCursorPos = adjustedCursorPos.coerceAtMost(formatted.length)

    return TextFieldValue(
        text = formatted.toString(),
        selection = TextRange(adjustedCursorPos)
    )
}

// Função para validar o CPF
fun isCpfValid(cpf: String): Boolean {
    val unmaskedCpf = cpf.replace(Regex("[^\\d]"), "")
    if (unmaskedCpf.length != 11) return false

    if (unmaskedCpf.all { it == unmaskedCpf[0] }) return false

    val dv1 = unmaskedCpf.take(9).mapIndexed { i, c -> (c - '0') * (10 - i) }.sum() * 10 % 11 % 10
    val dv2 = unmaskedCpf.take(10).mapIndexed { i, c -> (c - '0') * (11 - i) }.sum() * 10 % 11 % 10

    return dv1 == unmaskedCpf[9].digitToInt() && dv2 == unmaskedCpf[10].digitToInt()
}

// Função para salvar os dados no SharedPreferences
private fun saveLoginData(sharedPreferences: SharedPreferences, cpf: String, password: String) {
    sharedPreferences.edit {
        putString("cpf", cpf)
        putString("password", password)
        apply()
    }
}
