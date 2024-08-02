package com.example.shopfeeap_mvvm.domain.model.drink

data class DrinkModel(
    val Description: String?,
    val ImageUrl: ImageUrlModel?,
    val Price: Int?,
    val categori: CategoriModel?,
    val id: Int?,
    val name: String?,
    val order_details: List<OrderDetailModel>?,
    val rates: List<RateModel>?,
    val sales: Int?,
    val status: Boolean?,
)