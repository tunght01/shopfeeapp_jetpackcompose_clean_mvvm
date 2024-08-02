package com.example.shopfeeap_mvvm.data.remote

import com.example.shopfeeap_mvvm.data.remote.dto.DrinkDto
import com.example.shopfeeap_mvvm.data.remote.request.AuthRequest
import com.example.shopfeeap_mvvm.data.remote.respone.AuthResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path


interface ShopfeeApi {
    @GET("/drinks")
    suspend fun getDrinks():List<DrinkDto>

    @GET("/drinks/{id}")
    suspend fun getCoinById(@Path("id") drinkId:Int):DrinkDto

    @POST("/auth/local")
    suspend fun login(@Body loginRequest:AuthRequest):AuthResponse
}