package com.example.shopfeeap_mvvm.data.repository

import com.example.shopfeeap_mvvm.data.remote.ShopfeeApi
import com.example.shopfeeap_mvvm.data.remote.dto.DrinkDto
import com.example.shopfeeap_mvvm.domain.repository.DrinkRepository
import javax.inject.Inject

class DrinkRepositoryImpl @Inject constructor(
    private val api:ShopfeeApi
):DrinkRepository{
    override suspend fun getDrink(): List<DrinkDto> {
        return api.getDrinks()
    }

    override suspend fun getDrinkById(drinkID: Int): DrinkDto {
        return api.getCoinById(drinkId = drinkID)
    }
}