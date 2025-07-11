package dev.korryr.farmbrige.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
//import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import dev.korryr.farmbrige.ui.features.auth.view.LoginScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import dev.korryr.farmbrige.ui.features.auth.view.SignUpScreen
import dev.korryr.farmbrige.ui.features.home.view.HomeScreen
import android.widget.Toast
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import dev.korryr.farmbrige.ui.features.auth.preference.AuthPreferenceRepo
import dev.korryr.farmbrige.ui.features.marketPlace.view.MarketScreen
import dev.korryr.farmbrige.ui.features.message.view.MessageScreen
import dev.korryr.farmbrige.ui.features.profile.view.ProfileScreen

@Composable
fun NavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController,

    ) {
    val context = LocalContext.current

    val authPreferenceRepo = AuthPreferenceRepo(context) // did here
    val isLoggedIn by authPreferenceRepo.isLoggedIn.collectAsState(false) // here
    val startRoute = when {
        isLoggedIn -> Screen.Home.route
        else -> Screen.Login.route
    }

    Scaffold(
        containerColor = androidx.compose.material3.MaterialTheme.colorScheme.surface,
        bottomBar = {
            BottomNavigationBar(navController)
        },

    ) { innerPadding ->

        NavHost(
            navController = navController,
            startDestination = startRoute,
            modifier = modifier.padding(innerPadding)
        ) {
            composable(Screen.Login.route) {
                LoginScreen(
                    onNavigateToSignUp = {
                        navController.navigate(Screen.SignUp.route)
                    },
                    onClickLogin = {
                        navController.navigate(Screen.Home.route)
                    },
                    onClickGoogle = {
                        Toast.makeText(context, "feature soon", Toast.LENGTH_SHORT).show()
                    },
                    navController = navController
                )
            }

            composable(Screen.SignUp.route) {
                SignUpScreen(
                    onNavigateToLogin = {
                        navController.navigate(Screen.Login.route)
                    },
                    onSignedUp = {
                        navController.navigate(Screen.Login.route)
                    }
                )
            }

            composable(Screen.Home.route) {
                HomeScreen()
            }

            composable(Screen.MarketPlace.route) {

                MarketScreen()
            }
            composable(Screen.Messages.route) {

                MessageScreen()
            }
            composable(Screen.Profile.route) {

                ProfileScreen()
            }

        }
    }

}