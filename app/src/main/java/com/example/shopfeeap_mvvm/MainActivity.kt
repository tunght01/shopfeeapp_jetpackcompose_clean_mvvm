package com.example.shopfeeap_mvvm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.shopfeeap_mvvm.presentation.home_creen.HomeScreen
import com.example.shopfeeap_mvvm.presentation.home_creen.RecipeScreen
import com.example.shopfeeap_mvvm.presentation.login.LoginScreen
import com.example.shopfeeap_mvvm.presentation.login.viewmodel.AuthViewModel
import com.example.shopfeeap_mvvm.presentation.theme.ui.ShopfeeAp_MVVMTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel: AuthViewModel = hiltViewModel()
            val navController = rememberNavController()

            val viewmodel: AuthViewModel = hiltViewModel()
            ShopfeeAp_MVVMTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    NavHost(navController = navController, startDestination = "login") {
                        composable("home") { HomeScreen(navController= navController) }
                        composable("login") {
                            LoginScreen(navController= navController,viewModel = viewModel)
                        }
                    }
                }

            }
        }
    }
}

