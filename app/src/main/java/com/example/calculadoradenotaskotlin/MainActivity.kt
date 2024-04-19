package com.example.calculadoradenotaskotlin

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.calculadoradenotaskotlin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btCalcular.setOnClickListener{

            val nota1 = binding.Nota1.text.toString()
            val nota2 = binding.Nota2.text.toString()
            val nota3 = binding.Nota3.text.toString()
            val nota4 = binding.Nota4.text.toString()
            val numFaltas = binding.NumeroFaltas.text.toString()
            val resultado = binding.txtResultado

            if (nota1.isEmpty() || nota2.isEmpty() || nota3.isEmpty()
                || nota4.isEmpty() || numFaltas.isEmpty()){

                Toast.makeText(this, "Preencha todos os campos!!!", Toast.LENGTH_SHORT).show()

            }else{

                calcularMedia(nota1.toDouble(), nota2.toDouble(), nota3.toDouble(),
                    nota4.toDouble(), numFaltas.toInt(), resultado)

            }
        }
    }

    fun calcularMedia(nota1: Double, nota2: Double, nota3: Double, nota4: Double,
                      numFaltas: Int, resultado: TextView) {

        val media = (nota1 + nota2 + nota3 + nota4 ) / 4
        val mediaFinal = String.format("%.2f", media)

        if (media >= 6 && numFaltas <= 20){

            resultado.setText("Aluno Aprovado!!! \n Média Final: $mediaFinal")
            resultado.setTextColor(getColor(R.color.green))

        }else if (media < 6 && numFaltas > 20){

            resultado.setText("Aluno Reprovado por Nota e Faltas \n Média Final: $mediaFinal")
            resultado.setTextColor(getColor(R.color.red))

        }else if (media < 6 && numFaltas <= 20){

            resultado.setText("Aluno Reprovado por Nota \n Média Final: $mediaFinal")
            resultado.setTextColor(getColor(R.color.red))

        }else{

            resultado.setText("Aluno Reprovado por Faltas \n Média Final: $mediaFinal")
            resultado.setTextColor(getColor(R.color.red))

        }

    }

}


