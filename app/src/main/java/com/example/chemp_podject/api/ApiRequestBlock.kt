package com.example.chemp_podject.api

import com.example.chemp_podject.models.PolzovatModel
import retrofit2.Call
import retrofit2.http.*

interface ApiRequestBlock {
    @GET("/api/catalog")
    fun getBlock(): Call<List<BlockModel>>
    @Headers("accept: application/json")
    @POST("/api/sendCode")
    fun postEmail(@Header("email") email: String): Call<String>
    @POST("/api/signin")
    fun postCode(@Header("code") code: String, @Header("email") email: String): Call<String>
    @POST("/api/createProfile")
    @FormUrlEncoded
    fun postProfile(@Field("person") profile: PolzovatModel)
    @POST("/api/updateProfile")
    fun postUpdateProfile(): Call<List<PolzovatModel>>
}
