package dev.korryr.farmbrige.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Message
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Message
import androidx.compose.material.icons.filled.Store
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(
    val route: String
) {
    object Login : Screen("login")
    object SignUp : Screen("signUp")
    object ForgotPassword : Screen("forgotPassword")
    object Home : Screen("home")
    object MarketPlace : Screen("marketPlace")
    object Profile : Screen("profile")
    object Messages : Screen("message")
}

sealed class BottomScreen(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object Home : BottomScreen(
        route = Screen.Home.route,
        title = "Home",
        icon = Icons.Default.Home
    )
    object MarketPlace : BottomScreen(
        route = Screen.MarketPlace.route,
        title = "Market",
        icon = Icons.Default.Store
    )
    object Messages : BottomScreen(
        route = Screen.Messages.route,
        title = "Messages",
        icon = Icons.AutoMirrored.Filled.Message
    )
    object Profile : BottomScreen(
        route = Screen.Profile.route,
        title = "Profile",
        icon = Icons.Default.AccountCircle
    )

}