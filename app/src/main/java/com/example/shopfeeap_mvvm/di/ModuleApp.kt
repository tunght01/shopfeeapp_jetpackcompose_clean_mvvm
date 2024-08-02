package com.example.shopfeeap_mvvm.di

import com.example.shopfeeap_mvvm.common.Constant
import com.example.shopfeeap_mvvm.data.remote.ShopfeeApi
import com.example.shopfeeap_mvvm.data.repository.AuthRepositoryImpl
import com.example.shopfeeap_mvvm.data.repository.DrinkRepositoryImpl
import com.example.shopfeeap_mvvm.domain.repository.AuthRepository
import com.example.shopfeeap_mvvm.domain.repository.DrinkRepository
import com.example.shopfeeap_mvvm.domain.use_case.LoginUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ModuleApp {
    @Provides
    @Singleton
    fun providfePapriApi():ShopfeeApi{
        return Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ShopfeeApi::class.java)
    }
    @Provides
    @Singleton
    fun provideCoinRepository(api: ShopfeeApi):DrinkRepository{
        return DrinkRepositoryImpl(api)
    }
    @Provides
    @Singleton
    fun providesLoginUseCase(api: ShopfeeApi): AuthRepository {
        return AuthRepositoryImpl(api)
    }
    @Provides
    @Singleton
    fun providesLogin(repository: AuthRepository): LoginUseCase {
        return LoginUseCase(repository)
    }



}