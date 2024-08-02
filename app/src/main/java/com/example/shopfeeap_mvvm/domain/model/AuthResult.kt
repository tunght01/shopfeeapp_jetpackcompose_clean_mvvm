package com.example.shopfeeap_mvvm.domain.model

import com.example.shopfeeap_mvvm.common.Resource

data class AuthResult(
    val passwordError: String? = null,
    val emailError : String? = null,
    val result: Resource<Unit>? = null
)