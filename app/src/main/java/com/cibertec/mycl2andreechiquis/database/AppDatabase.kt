package com.cibertec.mycl2andreechiquis.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.cibertec.mycl2andreechiquis.model.Solicitud


@Database(entities = [Solicitud::class], version = 1)
abstract class AppDataBase : RoomDatabase() {
    abstract  fun soliDao() :SolicitudDao
    companion object {
        var appDataBase : AppDataBase? = null

        fun getInstance(context: Context) : AppDataBase {

            if (appDataBase == null) {
                appDataBase = Room.databaseBuilder(
                    context,
                    AppDataBase::class.java,
                    "veterinaria_patitas"
                ).build()
                //.allowMainThreadQueries().build()
            }
            return appDataBase!!
        }
    }
}