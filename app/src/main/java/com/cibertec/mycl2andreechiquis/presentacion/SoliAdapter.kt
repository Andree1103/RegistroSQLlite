package com.cibertec.mycl2andreechiquis.presentacion

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.cibertec.mycl2andreechiquis.R
import com.cibertec.mycl2andreechiquis.databinding.ItemCitaBinding
import com.cibertec.mycl2andreechiquis.model.Solicitud

//3 implementar los metodos del adaptador
//Indicar de donde sacaras la data
class SoliAdapter constructor(private var solis:List<Solicitud> = emptyList(), val onItemDelete : (Solicitud)-> Unit, val onItemView:(Solicitud)-> Unit) : Adapter<SoliAdapter.SoliViewHolder>() {
    // Implementar el view holder
    //XML
    //Data
    inner class SoliViewHolder constructor(val itemView:View) : ViewHolder(itemView){

        private val bindind : ItemCitaBinding = ItemCitaBinding.bind(itemView)

        fun bind(soli: Solicitud) {
            bindind.tvDueO.text=soli.cliente
            bindind.tvNomMascota.text=soli.mascota
            bindind.tvServicio.text=soli.servicio
            bindind.tvCosto.text=soli.precio

            bindind.imgView.setOnClickListener{
                onItemView(soli)
            }
        }


    }
    fun updateList(solis: List<Solicitud>){
        this.solis = solis
        notifyDataSetChanged()
    }
    //5 inflar el xml que se va usar
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SoliAdapter.SoliViewHolder {
        val itemView: View = LayoutInflater.from(parent.context).inflate(R.layout.item_cita, parent, false)
        return SoliViewHolder(itemView)
    }
    //6 recorre toda la lista
    override fun onBindViewHolder(holder: SoliAdapter.SoliViewHolder, position: Int) {
        val soli = solis[position]
        holder.bind(soli)
    }
    //4. cantidad de la lista
    override fun getItemCount(): Int {
        return solis.size
    }
}