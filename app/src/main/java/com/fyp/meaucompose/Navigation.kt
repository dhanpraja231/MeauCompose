package com.fyp.meaucompose

import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.fyp.meaucompose.screens.Screens
import com.fyp.meaucompose.screens.UserHomeScreen
import com.google.android.gms.auth.api.signin.GoogleSignInClient

@Composable
fun Navigation( mGoogleSignInClient: GoogleSignInClient, signInLauncher: ActivityResultLauncher<Intent>) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screens.LoginScreen.route){
        composable(route = Screens.LoginScreen.route){
            LoginScreen(mGoogleSignInClient = mGoogleSignInClient, signInLauncher = signInLauncher, navController= navController )
        }
        composable(route = Screens.UserHomeScreen.route){
            UserHomeScreen(navController = navController,mGoogleSignInClient = mGoogleSignInClient)
        }
    }


}
