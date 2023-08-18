package com.awakelab.oskar.apitelefonosv1.data

import com.awakelab.oskar.apitelefonosv1.data.local.DetalleEntity
import com.awakelab.oskar.apitelefonosv1.data.local.MovilEntity
import com.awakelab.oskar.apitelefonosv1.data.remote.DetalleDataClass
import com.awakelab.oskar.apitelefonosv1.data.remote.MovilDataClass

fun MovilDataClass.transEntity(): MovilEntity = MovilEntity(
    this.id, this.name, this.price, this.image
)

fun DetalleDataClass.transDetalle(): DetalleEntity = DetalleEntity(
    this.id, this.name, this.price, this.image, this.description, this.lastPrice, this.credit
)