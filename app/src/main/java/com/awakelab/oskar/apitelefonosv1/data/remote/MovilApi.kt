package com.awakelab.oskar.apitelefonosv1.data.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface MovilApi {
    @GET("products/")
    suspend fun getData(): Response<List<MovilDataClass>>

    @GET("details/{id}")
    suspend fun getDataDetalle(@Path("id") id: Int): Response<DetalleDataClass>
}