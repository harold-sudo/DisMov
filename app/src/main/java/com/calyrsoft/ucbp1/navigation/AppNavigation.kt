package com.calyrsoft.ucbp1.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.calyrsoft.ucbp1.features.cardexample.presentation.CardScreen
import com.calyrsoft.ucbp1.features.dollar.presentation.DollarScreen
import com.calyrsoft.ucbp1.features.forgot_password.presentation.ForgotPasswordScreen
import com.calyrsoft.ucbp1.features.github.presentation.GithubScreen
import com.calyrsoft.ucbp1.features.login.presentation.LoginScreen
import com.calyrsoft.ucbp1.features.profile.application.ProfileScreen

@Composable
fun AppNavigation() {
    val navController: NavHostController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Dollar.route
    ) {
        composable(Screen.Login.route) {
            LoginScreen(
                onForgotPasswordClicked = {
                    navController.navigate(Screen.ForgotPassword.route)
                },
                onLoginSuccess = {
                    navController.navigate(Screen.Home.route) // Navega a la pantalla Home
                }
            )
        }
        composable(Screen.ForgotPassword.route) {
            ForgotPasswordScreen(
                onBackClicked = {
                    navController.popBackStack()
                }
            )
        }
        composable(Screen.Github.route) {
            GithubScreen(modifier = Modifier)
        }
        composable(Screen.Home.route) {

        }
        composable(Screen.Profile.route) {
            ProfileScreen()
        }

        composable(Screen.CardExamples.route) { CardScreen() }


        composable(Screen.Dollar.route) {
            DollarScreen()
        }
    }
}