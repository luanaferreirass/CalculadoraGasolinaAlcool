package com.example.appalcoolgasolina

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat



class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

    }

    fun calcularPreco(view: View) {
        // Recuperar os valores digitados
        val precoAlcoolEditText = findViewById<EditText>(R.id.preçoAlcool)
        val precoGasolinaEditText = findViewById<EditText>(R.id.preçoGasolina)

        // Recuperar os valores como String e garantir que não sejam vazios
        val precoAlcool = precoAlcoolEditText.text.toString().trim()
        val precoGasolina = precoGasolinaEditText.text.toString().trim()

        // Validar os campos
        if (validarCampos(precoAlcool, precoGasolina)) {
            // Calcular e mostrar resultado se os campos forem válidos
            val resultado = calcular(precoAlcool, precoGasolina)

            // Exibir o resultado
            Toast.makeText(this, resultado, Toast.LENGTH_SHORT).show()
        } else {
            // Caso os campos sejam inválidos, exibir um aviso
            Toast.makeText(
                this,
                "Por favor, preencha ambos os campos corretamente",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    fun validarCampos(precoAlcool: String, precoGasolina: String): Boolean {
        // Verificar se os campos não estão vazios e são válidos números
        return precoAlcool.isNotEmpty() && precoGasolina.isNotEmpty() && precoAlcool.toDoubleOrNull() != null && precoGasolina.toDoubleOrNull() != null
    }

    fun calcular(precoAlcool: String, precoGasolina: String): String {
        // Garantir que os preços possam ser convertidos para Double
        val precoAlcoolDouble = precoAlcool.toDoubleOrNull()
        val precoGasolinaDouble = precoGasolina.toDoubleOrNull()

        if (precoAlcoolDouble != null && precoGasolinaDouble != null) {
            // Realizando o cálculo de comparação entre os preços
            return if (precoAlcoolDouble < precoGasolinaDouble) {
                "Álcool é mais barato"
            } else {
                "Gasolina é mais barata"
            }
        } else {
            // Retornar uma mensagem de erro caso a conversão falhe
            return "Erro nos preços informados"
        }
    }
}
