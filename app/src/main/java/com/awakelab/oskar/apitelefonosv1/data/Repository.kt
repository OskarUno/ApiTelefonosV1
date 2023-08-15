package com.awakelab.oskar.apitelefonosv1.data

import android.util.Log
import androidx.lifecycle.LiveData
import com.awakelab.oskar.apitelefonosv1.data.local.MovilDao
import com.awakelab.oskar.apitelefonosv1.data.local.MovilEntity
import com.awakelab.oskar.apitelefonosv1.data.remote.DetalleApi
import com.awakelab.oskar.apitelefonosv1.data.remote.MovilApi
import com.awakelab.oskar.apitelefonosv1.data.remote.MovilDataClass

class Repository(
    private val movilApi: MovilApi,
    private val movilDao: MovilDao,
    private val detalleApi: DetalleApi,
) {
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
/*
    suspend fun obtenerDetalleMovil(id: Int) {
        val res = detalleApi.getDetalleMovil(id)
        if(res.isSuccessful){
            val detalleEntity = res.body()
           movilDao.insertMovil(detalleEntity)

        }

    }

*/
}

fun MovilDataClass.transEntity(): MovilEntity =
    MovilEntity(this.id, this.name, this.price, this.image)

/*
suspend fun obtenerMoviles() {
    val respuesta = movilApi.getData()
    if (respuesta.isSuccessful) {
        val res = respuesta.body()
        res?.let { movilApis ->
            val movilEntity = movilApis.map { it.transEntity() }
            movilDao.insertMoviles(movilEntity)
        }
    }
}


 */