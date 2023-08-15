package com.awakelab.oskar.apitelefonosv1.data.remote

import com.google.gson.annotations.SerializedName

data class MovilDataClass(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("price") val price: Int,
    @SerializedName("image") val image: String,
)
