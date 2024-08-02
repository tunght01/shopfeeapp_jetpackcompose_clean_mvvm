package com.example.shopfeeap_mvvm.domain.repository

import com.example.shopfeeap_mvvm.data.remote.dto.DrinkDto

interface DrinkRepository {
    suspend fun getDrink():List<DrinkDto>
    suspend fun getDrinkById(drinkID:Int):DrinkDto

}