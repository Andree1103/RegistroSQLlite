package com.cibertec.mycl2andreechiquis.presentacion

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import com.cibertec.mycl2andreechiquis.R
import com.cibertec.mycl2andreechiquis.database.AppDataBase
import com.cibertec.mycl2andreechiquis.databinding.ActivityMainBinding
import com.cibertec.mycl2andreechiquis.model.Solicitud
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private lateinit var bindig: ActivityMainBinding
    private lateinit var appDataBase: AppDataBase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindig = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindig.root)
        //setContentView(R.layout.activity_main)
        val splServicios: AutoCompleteTextView = bindig.spaServicios
        val serviciosList: List<String> = listOf("Baño", "Corte", "Paseo")

        splServicios.setAdapter(
            ArrayAdapter(this, R.layout.item_spinner_servicios, serviciosList)
        )

        bindig.btnSend.setOnClickListener {
            val cliente = bindig.edtCliente.text.toString()
            val nommascota = bindig.edtMascota.text.toString()
            val servicio = splServicios.text.toString()
            var pago = ""
            if (bindig.rdefectivo.isChecked) {
                pago = "Efectivo"
            }
            if (bindig.rdTarjeta.isChecked) {
                pago = "Tarjeta"
            }
            if (bindig.rdYape.isChecked) {
                pago = "Yape"
            }

            val tipMascota = if (bindig.rdgatos.isChecked) "Gato" else "Perro"

            var precio = 0

            if (servicio == "Baño") {
                precio = 20
            }
            if (servicio == "Corte") {
                precio = 18
            }
            if (servicio == "Paseo") {
                precio = 22
            }

            val solicitud = Solicitud(
                0,
                cliente = cliente,
                mascota = nommascota,
                tipmascota = tipMascota,
                servicio = servicio,
                precio = precio.toString(),
                pago = pago
            )
            appDataBase = AppDataBase.getInstance(this)

            GlobalScope.launch(Dispatchers.Main) {
                bindig.progressBar.visibility = View.VISIBLE
                withContext(Dispatchers.IO) {
                    appDataBase.soliDao().insert(solicitud)
                }
                bindig.progressBar.visibility = View.GONE
                Toast.makeText(this@MainActivity, "Registro Correcto", Toast.LENGTH_SHORT).show()
            }

        }
        bindig.btnBack.setOnClickListener {
            onBackPressed()
        }
    }
}