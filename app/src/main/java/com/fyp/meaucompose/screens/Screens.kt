package com.fyp.meaucompose.screens

//import com.fyp.meaucompose.screens.Screens.LoginScreen.route

sealed class Screens(val route:String) {

//    object LoginScreen : Screens("login_screen")
    object UserHomeScreen : Screens("user_home_screen")
    object MyProjectsScreen: Screens("my_projects_screen")
    object EditPreferencesScreen : Screens("edit_preferences_screen")
    object FAQScreen: Screens("faq_screen")

}