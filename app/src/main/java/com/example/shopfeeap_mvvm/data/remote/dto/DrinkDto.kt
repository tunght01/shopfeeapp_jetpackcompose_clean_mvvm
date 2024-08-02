package com.example.shopfeeap_mvvm.data.remote.dto

import com.example.shopfeeap_mvvm.domain.model.drink.CategoriModel
import com.example.shopfeeap_mvvm.domain.model.drink.DrinkModel
import com.example.shopfeeap_mvvm.domain.model.drink.ImageUrlModel
import com.example.shopfeeap_mvvm.domain.model.drink.OrderDetailModel
import com.example.shopfeeap_mvvm.domain.model.drink.RateModel

data class DrinkDto (
    val Description: String?,
    val ImageUrl: ImageUrlModel?,
    val Price: Int?,
    val categori: CategoriModel?,
    val created_at: String?,
    val id: Int,
    val name: String?,
    val order_details: List<OrderDetailModel>?,
    val published_at: String?,
    val rates: List<RateModel>?,
    val sales: Int?,
    val status: Boolean?,
    val updated_at: String?
)

fun DrinkDto.toDrinkModel():DrinkModel{
    return DrinkModel(
        id = id,
        Description = Description,
        rates = rates,
        sales = sales,
        status = status,
        name = name,
        Price = Price,
        categori = categori,
        order_details = order_details,
        ImageUrl = ImageUrl
    )

}