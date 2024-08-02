package com.example.shopfeeap_mvvm.presentation.login

import android.annotation.SuppressLint
import android.graphics.drawable.Icon
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.shopfeeap_mvvm.R
import com.example.shopfeeap_mvvm.common.UiEvents
import com.example.shopfeeap_mvvm.presentation.login.viewmodel.AuthViewModel

@SuppressLint("ResourceAsColor")
@Composable
fun LoginScreen(
    navController: NavHostController,
    viewModel: AuthViewModel = hiltViewModel()
) {
    val usernameState by viewModel.emailState
    val passwordState by viewModel.passwordState
    val loginState by viewModel.loginState
    var passwordVisible by remember { mutableStateOf(false) }

    val context = LocalContext.current
    LaunchedEffect(Unit) {
        viewModel.eventFlow.collect { event ->
            when (event) {
                is UiEvents.NavigateEvent -> {
                    navController.navigate(route = event.route)
                }
                is UiEvents.SnackbarEvent -> {
                    Toast.makeText(context, event.message, Toast.LENGTH_LONG).show()
                }
                is UiEvents.ToastEvent ->{
                    Toast.makeText(context, event.message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.img),
            contentDescription = "Description of the image"
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Username",
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp)
        )
        OutlineTextFieldCustom(
            value = usernameState.text,
            onValueChange = viewModel::setEmail
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Password",
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp)
        )
        OutlineTextFieldCustom(
            value = passwordState.text,
            isPassword = true,
            onValueChange = viewModel::setPassword
        )
        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = { viewModel.loginUser() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp)
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(R.color.brown),
                contentColor = Color(R.color.brown),
            ),
            shape = RoundedCornerShape(15.dp),
            enabled = (passwordState.text.isNotEmpty() && usernameState.text.isNotEmpty()),

            ) {
            Text(text = "Đăng nhập", color = Color.White)
        }
        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "Quên mật khẩu?",
            textAlign = TextAlign.Center,
            style = TextStyle(fontSize = 12.sp),
            modifier = Modifier
                .defaultMinSize()
                .clickable { /* Handle forgot password */ }
        )
    }
    Column(
        modifier = Modifier
            .padding(bottom = 16.dp),
        Arrangement.Bottom,
        Alignment.End

    ) {
        Row(
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(
                text = "Bạn chưa có tài khoản?",
                textAlign = TextAlign.Center,
                style = TextStyle(fontSize = 12.sp)
            )
            Text(
                text = "Đăng ký",
                style = TextStyle(fontSize = 12.sp),
                modifier = Modifier.clickable {
                    // Handle sign-up navigation
                },
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Composable
fun OutlineTextFieldCustom(
    value: String,
    onValueChange: (String) -> Unit,
    isPassword: Boolean = false,
    trailingIcon: @Composable (() -> Unit)? = null
) {
    var passwordVisible by remember { mutableStateOf(false) }

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        shape = RoundedCornerShape(15.dp),
        visualTransformation = if (isPassword && !passwordVisible) PasswordVisualTransformation() else VisualTransformation.None,
        trailingIcon = {
            if (isPassword) {
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(imageVector =if (passwordVisible) Icons.Filled.FavoriteBorder else Icons.Filled.Favorite , contentDescription = null )
                }
            } else {
                trailingIcon?.invoke()
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp, end = 10.dp),
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Done
        )
    )
}
