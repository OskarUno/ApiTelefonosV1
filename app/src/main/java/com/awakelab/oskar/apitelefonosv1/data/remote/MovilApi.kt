package com.awakelab.oskar.apitelefonosv1.data.remote

import retrofit2.Response
import retrofit2.http.GET

interface MovilApi {
    @GET("products/")
    suspend fun getData(): Response<List<MovilDataClass>>
}