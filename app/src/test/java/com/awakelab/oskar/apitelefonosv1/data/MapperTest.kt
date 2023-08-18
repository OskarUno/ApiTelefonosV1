package com.awakelab.oskar.apitelefonosv1.data

import com.awakelab.oskar.apitelefonosv1.data.local.MovilEntity
import com.awakelab.oskar.apitelefonosv1.data.remote.DetalleDataClass
import com.awakelab.oskar.apitelefonosv1.data.remote.MovilDataClass
import org.junit.Assert.assertEquals
import org.junit.Test

class MapperTest {

    @Test
    fun transEntity() {
        //Given (Dado este valor)
        val movilEntity = MovilEntity(0, "Nombre1", 10000, "img1")

        val movilDataClass = MovilDataClass(0, "Nombre1", 10000, "img1")

        //fun MovilDataClass.transEntity()
        //When (Hago esta acción)
        val res = movilDataClass.transEntity()

        //Then (Espero este resultado)
        assertEquals(movilEntity,res)
    }
}