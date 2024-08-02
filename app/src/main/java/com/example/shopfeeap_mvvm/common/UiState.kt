package com.example.shopfeeap_mvvm.common

sealed class UiEvents {
    data class SnackbarEvent(val message : String) : UiEvents()
    data class NavigateEvent(val route: String) : UiEvents()
    data class ToastEvent(val message: String) : UiEvents()
}