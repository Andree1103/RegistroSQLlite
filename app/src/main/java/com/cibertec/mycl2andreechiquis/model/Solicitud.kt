package com.cibertec.mycl2andreechiquis.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "tb_solicitud")
data class Solicitud(
    @PrimaryKey(autoGenerate = true)
    @NonNull
    val id:Int,

    @ColumnInfo(name="cliente")
    val cliente:String,

    @ColumnInfo(name = "mascota")
    val mascota:String,

    @ColumnInfo(name = "tip_mascota")
    val tipmascota:String,

    @ColumnInfo(name = "servicio")
    val servicio:String,

    @ColumnInfo(name = "precio")
    val precio:String,

    @ColumnInfo(name = "pago")
    val pago:String
)
