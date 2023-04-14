package com.fyp.meaucompose.screens

import android.content.SharedPreferences
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.rounded.ExitToApp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.fyp.meaucompose.AppBar
import kotlinx.coroutines.launch

@Composable
fun SettingsScreen(navController: NavController) {
    val scaffoldState = rememberScaffoldState()
    var context = LocalContext.current
    val scope = rememberCoroutineScope()

    val sp : SharedPreferences = LocalContext.current.getSharedPreferences("settings",0)
    val mCheckedState = remember{ mutableStateOf(sp.getBoolean("isDarkTheme",false))} //TODO: get value from data store
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            AppBar(onNavigationIconClick = {
                scope.launch {
                    scaffoldState.drawerState.open()
                }
            },
                context = context,
                navController = navController,
                titleText = "Settings"
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
        content ={ scaffoldPadding ->
//            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center){
//                Text("My Projects")
//            }
            Column(modifier = Modifier
                .padding(scaffoldPadding)
                .fillMaxSize()){
                //Toggle Dark theme
                Surface(elevation = 4.dp,modifier= Modifier.padding(4.dp), shape = RoundedCornerShape(4.dp)) {
                    Column() {
                        Row(
                            Modifier
                                .padding(horizontal = 10.dp)
                                .fillMaxWidth()
                                //.height(50.dp)
                            ,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            //Row(Modifier.padding(vertical = 4.dp, horizontal = 4.dp)){
                            //Icon(imageVector = Icons.Default.Lightbulb, contentDescription = "dark theme icon", modifier = Modifier.size(50.dp))
                            //Spacer(Modifier.width(4.dp))
                            Text(
                                "Dark Theme",
                                fontSize = 18.sp,
                                fontFamily = FontFamily.SansSerif,
                                modifier = Modifier.padding(top = 10.dp)
                            )
                            //}
                            Switch(
                                checked = mCheckedState.value,
                                onCheckedChange = {
                                    mCheckedState.value = it
                                    val editor = sp.edit()
                                    editor.putBoolean("isDarkTheme", it)
                                    editor.commit()
                                }
                            )
                        }
                        Text(text = "Requires Application restart",
                            fontSize = 14.sp,
                            fontWeight= FontWeight.Light,
                            fontFamily = FontFamily.SansSerif,
                            modifier = Modifier.padding(start = 10.dp, bottom = 6.dp))
                    }
                }
                //Spacer(modifier = Modifier.height(5.dp))
                //Divider(color = Color.Gray, thickness = 1.dp)
                //Delete Account
                Surface(elevation = 4.dp,modifier= Modifier
                    .padding(4.dp)
                    .clickable { //TODO: Delete account
                    }, shape = RoundedCornerShape(4.dp)) {
                    Row(
                        Modifier
                            .padding(horizontal = 10.dp, vertical = 12.dp)
                            .fillMaxWidth()

                    ) {
                        Text(
                            fontSize = 18.sp,
                            fontFamily = FontFamily.SansSerif,
                            text = "Delete Account"
                        )
                    }
                }


            }


        })

}