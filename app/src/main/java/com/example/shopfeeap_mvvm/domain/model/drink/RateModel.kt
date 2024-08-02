package com.example.shopfeeap_mvvm.domain.model.drink

data class RateModel(
    val Rating: Int,
    val Review: String,
    val created_at: String,
    val drink: Int,
    val id: Int,
    val published_at: String,
    val updated_at: String,
    val users_permissions_user: Int
)