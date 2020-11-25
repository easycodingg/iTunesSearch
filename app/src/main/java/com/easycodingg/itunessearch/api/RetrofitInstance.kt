package com.easycodingg.itunessearch.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private const val BASE_URL = "https://itunes.apple.com/"

    private val retrofit by lazy {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)

        val client = OkHttpClient.Builder()
                .addInterceptor(logging)
                .build()

        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .baseUrl(BASE_URL)
            .build()
    }

    val api: ITunesApi by lazy {
        retrofit.create(ITunesApi::class.java)
    }
}