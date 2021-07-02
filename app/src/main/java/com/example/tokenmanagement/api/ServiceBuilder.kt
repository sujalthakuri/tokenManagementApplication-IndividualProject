package com.example.tokenmanagement.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceBuilder {


    private const val BASE_URL =
        "http://10.0.2.2:90/"

    //    private const val BASE_URL =
//            "http://localhost:90/"
    var token : String? = null
    private val okHttp = OkHttpClient.Builder()
    private val retrofitBuilder = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttp.build())
    // Create retrofit instance
    private val retrofit = retrofitBuilder.build()
    //Generic Function
    fun<T> buildService(serviceType:Class<T>): T {
        return retrofit.create(serviceType)
    }

    fun loadImage():String{
        val arr = BASE_URL.split("/").toTypedArray()
        return arr[0]+"/"+arr[1] + arr[2]+"/"
    }

}