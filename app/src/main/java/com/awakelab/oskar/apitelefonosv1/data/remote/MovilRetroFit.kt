package com.awakelab.oskar.apitelefonosv1.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//https://my-json-server.typicode.com/Himuravidal/FakeAPIdata/products/
class MovilRetroFit {

    companion object {
        private const val URL_BASE = "https://my-json-server.typicode.com/Himuravidal/FakeAPIdata/"
        fun getRetrofitMovil(): MovilApi {
            val mRetrofit = Retrofit.Builder()
                .baseUrl(URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return mRetrofit.create(MovilApi::class.java)
        }
    }
}