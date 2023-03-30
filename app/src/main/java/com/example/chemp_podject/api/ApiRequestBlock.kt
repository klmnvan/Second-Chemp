package com.example.chemp_podject.api

import retrofit2.Call
import retrofit2.http.*

interface ApiRequestBlock {
    @GET("/api/catalog")
    fun getBlock(): Call<List<BlockModel>>
    @GET("/api/catalog/search")
    fun getSearchBlock(@Query("q") searchText: String): Call<MutableList<BlockModel>>

    @Headers("accept: application/json")
    @POST("api/sendCode")
    fun postEmail(@Header("email") email: String): Call<String>
    @POST("api/signin")
    fun postCode(@Header("code") code: String, @Header("email") email: String): Call<String>
}