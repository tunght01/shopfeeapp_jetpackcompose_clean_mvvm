package com.example.shopfeeap_mvvm.presentation.home_creen.viewmodel

import com.example.shopfeeap_mvvm.domain.model.drink.DrinkModel

data class DrinksState (

    val isLoading:Boolean = false,
    val drinks:List<DrinkModel> = emptyList(),
    val error:String = ""
    )