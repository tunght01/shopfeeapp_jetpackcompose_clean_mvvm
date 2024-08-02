package com.example.shopfeeap_mvvm.data.remote.request

import com.google.gson.annotations.SerializedName

data class AuthRequest(
    @SerializedName("identifier")
    var email: String,
    @SerializedName("password")
    var password: String
)