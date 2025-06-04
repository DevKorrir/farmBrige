package dev.korryr.farmbrige.ui.navigation

sealed class Screen(
    val route: String
) {
    object Login : Screen("login")
    object SignUp : Screen("signUp")
    object ForgotPassword : Screen("forgotPassword")
    object Home : Screen("home")
    object MarketPlace : Screen("marketPlace")
    object Profile : Screen("profile")
}