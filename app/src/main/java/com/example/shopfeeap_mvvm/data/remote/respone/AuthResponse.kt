package com.example.shopfeeap_mvvm.data.remote.respone

import com.google.gson.annotations.SerializedName

data class AuthResponse(
    @SerializedName("jwt")
    val jwt: String,
)
