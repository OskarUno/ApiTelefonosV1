package com.awakelab.oskar.apitelefonosv1.data

import android.util.Log
import androidx.lifecycle.LiveData
import com.awakelab.oskar.apitelefonosv1.data.local.DetalleEntity
import com.awakelab.oskar.apitelefonosv1.data.local.MovilDao
import com.awakelab.oskar.apitelefonosv1.data.local.MovilEntity
import com.awakelab.oskar.apitelefonosv1.data.remote.DetalleDataClass
import com.awakelab.oskar.apitelefonosv1.data.remote.MovilApi
import com.awakelab.oskar.apitelefonosv1.data.remote.MovilDataClass

class Repository(
    private val movilApi: MovilApi,
    private val movilDao: MovilDao,
) {
    fun obtenerDetalleEntity(id: Int): LiveData<DetalleEntity> = movilDao.getMovil(id)
    fun obtenerMovilEntity(): LiveData<List<MovilEntity>> = movilDao.getAllMoviles()

    suspend fun obtenerMoviles() {
        val respuesta = movilApi.getData()
        if (respuesta.isSuccessful) {
            val res = respuesta.body()
            res?.let { movilApis ->
                val movilEntity = movilApis.map { it.transEntity() }
                movilDao.insertMoviles(movilEntity)
            }
        } else {
            Log.e("Repository", respuesta.errorBody().toString())
        }
    }

    suspend fun obtenerDetalleMovil(id: Int) {
        val res = movilApi.getDataDetalle(id)
        if (res.isSuccessful) {
            val detalleEntity = res.body()!!.copy()
            movilDao.insertMovil(detalleEntity.transDetalle())
        } else {
            Log.e("Repository", res.errorBody().toString())
        }
    }
}

fun MovilDataClass.transEntity(): MovilEntity = MovilEntity(
    this.id, this.name, this.price, this.image
)

fun DetalleDataClass.transDetalle(): DetalleEntity = DetalleEntity(
    this.id, this.name, this.price, this.image, this.description, this.lastPrice, this.credit
)