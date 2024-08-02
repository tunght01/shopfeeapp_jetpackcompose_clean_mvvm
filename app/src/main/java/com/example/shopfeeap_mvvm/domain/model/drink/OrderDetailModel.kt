package com.example.shopfeeap_mvvm.domain.model.drink

data class OrderDetailModel(
    val Price: Int?,
    val Quantity: Int?,
    val Sugarlevel: String?,
    val Temperature: String?,
    val Topping: String?,
    val drink: Int?,
    val id: Int?,
    val order: Int?,
    val users_permissions_user: Int?
)