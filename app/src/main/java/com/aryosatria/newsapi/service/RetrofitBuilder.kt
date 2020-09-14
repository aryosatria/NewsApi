package com.aryosatria.newsapi.service

import com.aryosatria.newsapi.model.ResponseNews
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers

object RetrofitBuilder {
    private val client = OkHttpClient.Builder().build()
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://newsapi.org/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    fun getService() = retrofit.create(TopHeadLines::class.java)
}

interface TopHeadLines{
    @Headers("Authorization: 8ff64a23ae364ab49b4caa501b17f89f")
    @GET ("/v2/top-headlines?country=id")
    fun fetchHeadlines(): Call<ResponseNews>
}