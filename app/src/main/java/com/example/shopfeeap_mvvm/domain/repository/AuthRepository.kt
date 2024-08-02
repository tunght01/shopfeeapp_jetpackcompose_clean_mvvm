package com.example.shopfeeap_mvvm.domain.repository

import com.example.shopfeeap_mvvm.common.Resource
import com.example.shopfeeap_mvvm.data.remote.request.AuthRequest

interface AuthRepository {
    suspend fun login(loginRequest: AuthRequest): Resource<Unit>
}