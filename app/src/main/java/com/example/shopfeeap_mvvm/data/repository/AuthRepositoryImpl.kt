package com.example.shopfeeap_mvvm.data.repository

import com.example.shopfeeap_mvvm.common.Resource
import com.example.shopfeeap_mvvm.data.remote.ShopfeeApi
import com.example.shopfeeap_mvvm.data.remote.request.AuthRequest
import com.example.shopfeeap_mvvm.domain.repository.AuthRepository
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val api:ShopfeeApi
):AuthRepository {
    override suspend fun login(loginRequest: AuthRequest): Resource<Unit> {
        return try {
            val response = api.login(loginRequest)
//            preferences.saveAuthToken(response.token)
            Resource.Success(Unit)
        }catch (e: IOException){
            Resource.Error("${e.message}")
        }catch (e: HttpException){
            Resource.Error("${e.message}")
        }
    }

}