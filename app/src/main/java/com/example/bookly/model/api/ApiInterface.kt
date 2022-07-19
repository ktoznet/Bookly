package com.example.bookly.model.api

import com.example.bookly.model.data.Also
import com.example.bookly.model.data.BestSellers
import com.example.bookly.model.data.Carousel
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path


interface ApiInterface {
    @GET("https://my-json-server.typicode.com/stellardiver/ebookdata/{best}")
    fun getBest(@Path("best") sort: String): Call<BestSellers>

    @GET("https://my-json-server.typicode.com/stellardiver/ebookdata/{carousel}")
    fun getCarousel(@Path("carousel") sort: String): Call<Carousel>

    @GET("https://my-json-server.typicode.com/stellardiver/ebookdata/{similar}")
    fun getAlso(@Path("similar") sort: String): Call<Also>



    companion object {

        var BASE_URL = "https://my-json-server.typicode.com"

        fun create(): ApiInterface {

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(ApiInterface::class.java)

        }
    }
}