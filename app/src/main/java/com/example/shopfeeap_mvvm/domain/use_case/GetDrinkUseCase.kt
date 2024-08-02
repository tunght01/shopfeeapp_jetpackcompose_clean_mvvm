package com.example.shopfeeap_mvvm.domain.use_case

import com.example.shopfeeap_mvvm.data.remote.dto.toDrinkModel
import com.example.shopfeeap_mvvm.domain.model.drink.DrinkModel
import com.example.shopfeeap_mvvm.domain.repository.DrinkRepository
import com.example.shopfeeap_mvvm.common.Resource
import retrofit2.HttpException
import javax.inject.Inject

class GetDrinkUseCase @Inject constructor(
    private val repository: DrinkRepository
) {
    suspend operator fun invoke(): Resource<List<DrinkModel>> {
        return try {
            val drinks = repository.getDrink().map {
                it.toDrinkModel()
            }
            Resource.Success(drinks)
        } catch (e: HttpException) {
            Resource.Error(e.localizedMessage ?: "An unexpected error occurred")
        } catch (e: Exception) {
            Resource.Error("Couldn't reach server. Check your internet connection ${e.message}")
        }
    }
}
