package com.example.chemp_podject.api

import retrofit2.Call
import retrofit2.http.GET

interface ApiRequest {
    @GET("/api/news")
    fun getNews(): Call<List<NewsModel>>
}