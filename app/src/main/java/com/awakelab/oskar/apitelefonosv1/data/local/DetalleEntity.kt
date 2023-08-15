package com.awakelab.oskar.apitelefonosv1.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tabla_movil_detalle")
data class DetalleEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val price: Int,
    val image: String,
    val description: String,
    val lastPrice: Int,
    val credit: Boolean,
)
