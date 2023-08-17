package com.awakelab.oskar.apitelefonosv1.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MovilDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovil(detalleEntity: DetalleEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMoviles(movilEntity: List<MovilEntity>)

    @Query("select * from tabla_movil order by id ASC")
    fun getAllMoviles(): LiveData<List<MovilEntity>>

    @Query("select * from tabla_movil_detalle where id = :id")
    fun getMovil(id: Int): LiveData<DetalleEntity>

}