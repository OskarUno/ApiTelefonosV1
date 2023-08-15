package com.awakelab.oskar.apitelefonosv1.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tabla_movil")
data class MovilEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val price: Int,
    val image: String,
)
