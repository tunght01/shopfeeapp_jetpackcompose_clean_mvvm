package com.example.shopfeeap_mvvm.data.remote.dto

import com.example.shopfeeap_mvvm.domain.model.drink.OrderDetailModel

data class OrderDetailDto(
    val Price: Int?,
    val Quantity: Int?,
    val Sugarlevel: String?,
    val Temperature: String?,
    val Topping: String?,
    val created_at: String?,
    val drink: Int?,
    val id: Int,
    val order: Int?,
    val published_at: String?,
    val updated_at: String?,
    val users_permissions_user: Int
)
fun OrderDetailDto.toOrderDetail():OrderDetailModel{
     return OrderDetailModel(
         Price = Price,
         Quantity= Quantity,
         Sugarlevel = Sugarlevel ,
         Temperature = Temperature,
         Topping = Topping,
         drink = drink,
         id = id,
         order = order,
         users_permissions_user = users_permissions_user
    )
}