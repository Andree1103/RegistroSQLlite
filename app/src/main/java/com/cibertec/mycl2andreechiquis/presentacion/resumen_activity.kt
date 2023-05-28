package com.cibertec.mycl2andreechiquis.presentacion

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.cibertec.mycl2andreechiquis.R
import com.cibertec.mycl2andreechiquis.databinding.ActivityResumenBinding

class resumen_activity : AppCompatActivity() {

    private lateinit var binding : ActivityResumenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResumenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //etContentView(R.layout.activity_resumen)
         val bundle = intent.extras

        val cliente =bundle?.getString("Key_Title") ?: "Desconocido"
        val mascota =bundle?.getString("Key_Desc") ?: "Desconocido"
        val tipmascota =bundle?.getString("KEY_TIP_MASCOTA") ?: "Desconocido"
        val servicio =bundle?.getString("KEY_SERVICIO") ?: "Desconocido"
        val precio =bundle?.getString("KEY_PRECIO") ?: "Desconocido"
        val pago =bundle?.getString("PAGO") ?: "Desconocido"

        val resultConcat =
            "Cliente: \n" +
                    "$cliente \n" +
                    "\n" +
                    "Nombre de la Mascota:\n" +
                    "$mascota \n" +
                    "\n" +
                    "Tipo de mascota:\n" +
                    "$tipmascota \n"+
                    "\n"+
                    "Tipo de Servicio: \n" +
                    "$servicio \n"+
                    "\n"+
                    "Medio de Pago: \n" +
                    "$pago \n"+
                    "\n"+
                    "Total A Pagar: \n" +
                    "S/"+"$precio \n"+
                    "\n"+" "

        binding.tvResult.text=resultConcat


        binding.btnBack.setOnClickListener {
            val intent = Intent(this,ListCitas::class.java)
            startActivity(intent)
        }

    }
}