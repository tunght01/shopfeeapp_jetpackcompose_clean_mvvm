package com.example.shopfeeap_mvvm.presentation.login.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopfeeap_mvvm.common.Resource
import com.example.shopfeeap_mvvm.common.TextFieldState
import com.example.shopfeeap_mvvm.common.UiEvents
import com.example.shopfeeap_mvvm.domain.use_case.GetDrinkUseCase
import com.example.shopfeeap_mvvm.domain.use_case.LoginUseCase
import com.example.shopfeeap_mvvm.presentation.home_creen.viewmodel.DrinksState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
//    private val registerUseCase: RegisterUseCase,
): ViewModel() {

    private var _loginState  = mutableStateOf(AuthState())
    val loginState: State<AuthState> = _loginState

    private val  _eventFlow = MutableSharedFlow<UiEvents>()
    val eventFlow = _eventFlow.asSharedFlow()

    private val _firstName = mutableStateOf(TextFieldState())
    val firstName: State<TextFieldState> = _firstName

    fun setFirstName(value:String){
        _firstName.value = firstName.value.copy(text = value)
    }

    private val _lastName = mutableStateOf(TextFieldState())
    val lastName: State<TextFieldState> = _lastName

    fun setLastName(value:String){
        _lastName.value = lastName.value.copy(text = value)
    }

    private val _emailState = mutableStateOf(TextFieldState())
    val emailState: State<TextFieldState> = _emailState

    fun setEmail(value:String){
        _emailState.value = emailState.value.copy(text = value)
    }

    private val _passwordState = mutableStateOf(TextFieldState())
    val passwordState: State<TextFieldState> = _passwordState

    fun setPassword(value:String){
        _passwordState.value = passwordState.value.copy(text = value)
    }

    fun loginUser(){
        viewModelScope.launch {
            _loginState.value = loginState.value.copy(isLoading = false)

            val loginResult = loginUseCase(
                email = emailState.value.text,
                password = passwordState.value.text
            )


            if (loginResult.emailError != null){
                _emailState.value=emailState.value.copy(error = loginResult.emailError)
            }
            if (loginResult.passwordError != null){
                _passwordState.value = passwordState.value.copy(error = loginResult.passwordError)
            }

            when(loginResult.result){
                is Resource.Success->{
                    _loginState.value = loginState.value.copy(isLoading = false)
                    _eventFlow.emit(
                        UiEvents.ToastEvent("Đăng nhập thành công")
                    )
                    _eventFlow.emit(
                        UiEvents.NavigateEvent("home")
                    )
                }
                is Resource.Error->{
                    UiEvents.SnackbarEvent(
                        loginResult.result.message ?: "Error!"
                    )
                }
                else -> {

                }
            }
        }
    }

//    fun registerUser(){
//        viewModelScope.launch {
//            _loginState.value = loginState.value.copy(isLoading = false)
//
//            val registerResult = registerUseCase(
//                email = emailState.value.text,
//                password = passwordState.value.text
//            )
//
//            _loginState.value = loginState.value.copy(isLoading = false)
//
//            if (registerResult.emailError != null){
//                _emailState.value=emailState.value.copy(error = registerResult.emailError)
//            }
//            if (registerResult.passwordError != null){
//                _passwordState.value = passwordState.value.copy(error = registerResult.passwordError)
//            }
//
//            when(registerResult.result){
//                is Resource.Success->{
//                    _eventFlow.emit(
//                        UiEvents.NavigateEvent("home")
//                    )
//                }
//                is Resource.Error->{
//                    UiEvents.SnackbarEvent(
//                        registerResult.result.message ?: "Error!"
//                    )
//                }
//                else -> {
//
//                }
//            }
//        }
//    }

}