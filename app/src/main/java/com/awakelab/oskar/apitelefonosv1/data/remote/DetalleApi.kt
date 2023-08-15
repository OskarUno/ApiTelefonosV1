package com.awakelab.oskar.apitelefonosv1.data.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

//https://my-json-server.typicode.com/Himuravidal/FakeAPIdata/details/3
interface DetalleApi {

    @GET("{id}")
    suspend fun getDetalleMovil(@Path("id") id: Int): Response<DetalleDataClass>
}