package com.fyp.meaucompose



import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.fyp.meaucompose.screens.*
import com.google.android.gms.auth.api.signin.GoogleSignInClient

@Composable
fun Navigation( ) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screens.UserHomeScreen.route){
//        composable(route = Screens.LoginScreen.route){
//            LoginScreen(mGoogleSignInClient = mGoogleSignInClient, signInLauncher = signInLauncher, navController= navController )
//        }
        composable(route = Screens.UserHomeScreen.route){
//            UserHomeScreen(navController = navController,mGoogleSignInClient = mGoogleSignInClient)
            UserHomeScreen(navController = navController)
        }
        composable(route = Screens.MyProjectsScreen.route){
            MyProjectsScreen(navController = navController)
        }
        composable(route = Screens.EditPreferencesScreen.route){
            EditPreferencesScreen(navController = navController)
        }
        composable(route = Screens.FAQScreen.route){
            FAQScreen(navController = navController)
        }
        composable(route = Screens.RecommenderForProjectScreen.route){
            RecommenderForProjectScreen(navController = navController)
        }
        composable(route = Screens.SettingsScreen.route){
            SettingsScreen(navController = navController)
        }
        composable(route = Screens.EditTagsScreen.route){
            EditTagsScreen(navController = navController)
        }
        composable(route = Screens.AddProjectsScreen.route){
            AddProjectScreen(navController = navController)
        }
        composable(route = Screens.ViewMatchesScreen.route) {
            ViewMatchesScreen(navController = navController)
        }
    }


}

