package com.example.calculo_imc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import com.example.calculo_imc.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btn.setOnClickListener {
            val n = (binding.nome.text).toString()

            val any: String = "Anonimo"

            if (TextUtils.isEmpty(binding.nome!!.text.toString())) {
                binding.resultTexto.text = any
            } else {
                binding.resultTexto.text = n
            }

            val h = (binding.alt.text).toString().toFloat()
            val w = binding.peso.text.toString().toFloat()

            configurarRG(h, w)
        }

    }

    private fun configurarRG(h: Float, w: Float) {
        binding.rgClassificacaoIdade.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.radioButtonAdulto -> {
                    calularIMC(1, h, w)
                }
                R.id.radioButtonIdoso -> {
                    calularIMC(2, h, w)
                }
            }
        }
    }

    private fun calularIMC(i: Int, h: Float, w: Float) {
        val res = w / (h * h)

        binding.result.text = "%.2f".format(res)

        var msg: String = ""
        if (i == 1) {
            if (res < 18.5) {
                msg = "Adulto abaixo do peso"
                binding.mensagem.setBackgroundColor(getColor(android.R.color.holo_orange_light))
                binding.mensagem.text = msg
            }
            if (res > 18.5 && res < 24.9) {
                msg = "Adulto peso normal"
                binding.mensagem.setBackgroundColor(getColor(android.R.color.holo_green_dark))
                binding.mensagem.text = msg
            }
            if (res > 25 && res < 29.9) {
                msg = "Adulto execesso de peso"
                binding.mensagem.setBackgroundColor(getColor(android.R.color.system_accent3_700))
                binding.mensagem.text = msg
            }
            if (res > 30 && res < 34.9) {
                msg = "Adulto obesidade de classe 1"
                binding.mensagem.setBackgroundColor(getColor(android.R.color.holo_orange_dark))
                binding.mensagem.text = msg
            }
            if (res > 35 && res < 39.9) {
                msg = "Adulto obesidade de classe 2"
                binding.mensagem.setBackgroundColor(getColor(android.R.color.holo_red_light))
                binding.mensagem.text = msg
            }
            if (res >= 40) {
                msg = "Adulto obesidade de classe 3"
                binding.mensagem.setBackgroundColor(getColor(android.R.color.holo_red_dark))
                binding.mensagem.text = msg
            }
        }
        if (i == 2) {
            if (res <= 22) {
                msg = "Idoso abaixo do peso"
                binding.mensagem.setBackgroundColor(getColor(android.R.color.holo_orange_light))
                binding.mensagem.text = msg
            }
            if (res > 22 && res < 27) {
                msg = "Idoso adequado ou eutrofíco"
                binding.mensagem.setBackgroundColor(getColor(android.R.color.holo_green_dark))
                binding.mensagem.text = msg
            }
            if (res > 27) {
                msg = "Idoso com sobrepeso"
                binding.mensagem.setBackgroundColor(getColor(android.R.color.holo_red_dark))
                binding.mensagem.text = msg
            }
        }
    }
}
