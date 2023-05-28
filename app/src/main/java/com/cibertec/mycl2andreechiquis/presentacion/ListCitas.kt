package com.cibertec.mycl2andreechiquis.presentacion

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.cibertec.mycl2andreechiquis.R
import com.cibertec.mycl2andreechiquis.database.AppDataBase
import com.cibertec.mycl2andreechiquis.databinding.ActivityListCitasBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ListCitas : AppCompatActivity() {

    private lateinit var binding: ActivityListCitasBinding
    private  lateinit var soliAdapter : SoliAdapter
    private lateinit var appDataBase: AppDataBase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =ActivityListCitasBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //setContentView(R.layout.activity_list_citas)

        setupAdapter()

        appDataBase =AppDataBase.getInstance(this)
        GlobalScope.launch {
            binding.progressBar2.visibility = View.VISIBLE
            val solicitudes = withContext(Dispatchers.IO){
                appDataBase.soliDao().getAllSolicitud()
            }
            binding.progressBar2.visibility = View.GONE
            soliAdapter.updateList(solicitudes)
        }
        binding.fabAdd.setOnClickListener{
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
        }
    override fun onStart() {
        super.onStart()
        appDataBase = AppDataBase.getInstance(this)
        getlistdb()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    private fun getlistdb() {
        GlobalScope.launch (Dispatchers.Main) {

            binding.progressBar2.visibility = View.VISIBLE
            val notes = withContext(Dispatchers.IO){
                appDataBase.soliDao().getAllSolicitud()
            }
            binding.progressBar2.visibility = View.GONE
            soliAdapter.updateList(notes)
        }
    }


    private fun setupAdapter() {
        soliAdapter = SoliAdapter(onItemDelete = {soli ->
            GlobalScope.launch (Dispatchers.Main) {

                binding.progressBar2.visibility = View.VISIBLE
                withContext(Dispatchers.IO){
                    appDataBase.soliDao().delete(soli)
                }
                binding.progressBar2.visibility = View.GONE
                Toast.makeText(this@ListCitas, "Cita Eliminada", Toast.LENGTH_SHORT).show()
            }
            getlistdb()
        }, onItemView = {
            val bundle = Bundle().apply {
                putString("Key_Title", it.cliente)
                putString("Key_Desc",it.mascota)
                putString("KEY_TIP_MASCOTA",it.tipmascota)
                putString("KEY_SERVICIO",it.servicio)
                putString("KEY_PRECIO", it.precio)
                putString("PAGO", it.pago)
            }
            val intent = Intent(this,resumen_activity::class.java).apply {
                putExtras(bundle)
            }
            startActivity(intent)
        })
        binding.rvCitas.adapter=soliAdapter
    }
    }