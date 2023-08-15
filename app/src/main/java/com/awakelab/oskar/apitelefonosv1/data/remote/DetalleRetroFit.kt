package com.awakelab.oskar.apitelefonosv1.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//https://my-json-server.typicode.com/Himuravidal/FakeAPIdata/details/3
class DetalleRetroFit {

    companion object {
        private const val URL_BASE =
            "https://my-json-server.typicode.com/Himuravidal/FakeAPIdata/details/"

        fun getRetrofitDetalle(): DetalleApi {
            val mRetrofit = Retrofit.Builder()
                .baseUrl(URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return mRetrofit.create(DetalleApi::class.java)  //Reflexion Patron Builder, permite crear una instancia generica
        }
    }

}