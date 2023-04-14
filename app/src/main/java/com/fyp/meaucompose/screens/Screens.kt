package com.fyp.meaucompose.screens

//import com.fyp.meaucompose.screens.Screens.LoginScreen.route

sealed class Screens(val route:String) {

//    object LoginScreen : Screens("login_screen")
    object UserHomeScreen : Screens("user_home_screen")
    object MyProjectsScreen: Screens("my_projects_screen")
    object EditPreferencesScreen : Screens("edit_preferences_screen")
    object FAQScreen: Screens("faq_screen")
    object RecommenderForProjectScreen: Screens("recommender_for_project_screen")
    object SettingsScreen:Screens("settings_screen")
    object EditTagsScreen:Screens("edit_tags_screen")
    object AddProjectsScreen:Screens("add_projects_screen")
    object ViewMatchesScreen:Screens("view_matches_screen")
}