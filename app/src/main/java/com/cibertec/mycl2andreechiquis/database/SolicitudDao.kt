package com.cibertec.mycl2andreechiquis.database

import androidx.room.*
import com.cibertec.mycl2andreechiquis.model.Solicitud


@Dao
interface SolicitudDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(solicitud: Solicitud)

    @Update
    fun update(solicitud: Solicitud)

    @Delete
    fun delete(solicitud: Solicitud)

    @Query("SELECT * FROM tb_solicitud order by id desc")
    fun getAllSolicitud(): List<Solicitud>
}