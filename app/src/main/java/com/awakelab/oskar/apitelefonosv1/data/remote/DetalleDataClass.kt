package com.awakelab.oskar.apitelefonosv1.data.remote

import com.google.gson.annotations.SerializedName

data class DetalleDataClass(
    @SerializedName("id"          ) var id          : Int,
    @SerializedName("name"        ) var name        : String,
    @SerializedName("price"       ) var price       : Int,
    @SerializedName("image"       ) var image       : String,
    @SerializedName("description" ) var description : String,
    @SerializedName("lastPrice"   ) var lastPrice   : Int,
    @SerializedName("credit"      ) var credit      : Boolean
)
