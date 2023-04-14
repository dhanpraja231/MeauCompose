package com.fyp.meaucompose.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import kotlinx.coroutines.launch


@Composable
fun ViewMatchesScreen(navController: NavHostController) {
    val scaffoldState = rememberScaffoldState()
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    Scaffold(
        scaffoldState = scaffoldState,
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = { FloatingActionButton(onClick = {navController.navigate(Screens.EditPreferencesScreen.route)}){
            Text("Save")
        } },
        topBar = {
            com.fyp.meaucompose.AppBar(
                onNavigationIconClick = {
                    scope.launch {
                        scaffoldState.drawerState.open()
                    }
                },
                context = context,
                navController = navController,
                titleText = "Edit Skills"
            )
        },
        drawerContent = {
            DrawerHeader(
            )
            DrawerBody(

                context = context,
                navController = navController

            )


        },
        content ={
            Surface(Modifier.padding(it)) {
                LazyColumn(
                    Modifier
                        .fillMaxSize()
                        .padding(top = 4.dp, bottom = 10.dp)
                ) {
                    items(listOf("","")) { item ->
                        Surface(
                            Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 5.dp), elevation = 6.dp
                        ) {


                        }
                    }

                }
            }
        })
}