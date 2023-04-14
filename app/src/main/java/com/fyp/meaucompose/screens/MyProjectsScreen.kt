package com.fyp.meaucompose.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.fyp.meaucompose.AppBar
import com.google.android.gms.auth.api.signin.GoogleSignIn

import kotlinx.coroutines.launch


@Composable
fun MyProjectsScreen(navController: NavController) {

    var context = LocalContext.current
    //var userName: String = GoogleSignIn.getLastSignedInAccount(context)?.displayName.toString()
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    Scaffold(
        scaffoldState = scaffoldState,
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = { FloatingActionButton(onClick = {
            navController.navigate(Screens.AddProjectsScreen.route)
        }){
            Text("+")
        } },
        topBar = {
            AppBar(onNavigationIconClick = {
                scope.launch {
                    scaffoldState.drawerState.open()
                }
            },
                context = context,
                navController = navController,
                titleText = "My Projects"
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
            LazyColumn(modifier = Modifier.padding(scaffoldPadding)){

                itemsIndexed(
                    //TODO: Create project item extending realm object and populate
                    listOf("Aucademics",
                        "Uber Placeholder",
                        "Baller App",
                        "Cereal killer",
                        "Loves and Likes",
                        "Beautiful Disaster",
                        "Cheezels",
                        "Game Development",
                        "One piece IRL",


                    )
                ){
                        index, string ->
                    Surface(elevation = 4.dp,modifier= Modifier.padding(4.dp).clickable { navController.navigate(Screens.RecommenderForProjectScreen.route) }) {
                    Column() {
                        Text(
                            text = "$string",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 24.dp)
                        )
                        Row(verticalAlignment = Alignment.CenterVertically, modifier =Modifier.padding(bottom = 4.dp)){
                            Row(Modifier.weight(1f), horizontalArrangement =  Arrangement.Start){
                                Spacer(Modifier.width(6.dp))
                                Box(Modifier.size(15.dp).background(color = Color.Green, shape = CircleShape))
                            }


                            Row(
                                horizontalArrangement = Arrangement.End,
                                modifier = Modifier.weight(2f)

                            ) {
                                Button(
                                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent),
                                    modifier = Modifier
                                        .border(
                                            width = 1.dp,
                                            shape = RoundedCornerShape(50),
                                            color = Color.DarkGray
                                        )
                                        .height(30.dp),
                                    shape = RoundedCornerShape(50),
                                    onClick = { /*TODO*/ }) {
                                    Text(text = "Suspend", fontSize = 10.sp, color = MaterialTheme.colors.onSurface)
                                }
                                Spacer(modifier = Modifier.width(2.dp))
                                Button(
                                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent),
                                    modifier = Modifier
                                        .border(
                                            width = 1.dp,
                                            shape = RoundedCornerShape(50),
                                            color = Color.DarkGray
                                        )
                                        .height(30.dp),
                                    shape = RoundedCornerShape(50),
                                    onClick = { /*TODO*/ }) {
                                    Text(text = "Edit", fontSize = 10.sp, color = MaterialTheme.colors.onSurface)
                                }
                                Spacer(modifier = Modifier.width(2.dp))
                                Button(
                                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent),
                                    modifier = Modifier
                                        .border(
                                            width = 1.dp,
                                            shape = RoundedCornerShape(50),
                                            color = Color.DarkGray
                                        )
                                        .height(30.dp),
                                    shape = RoundedCornerShape(50),
                                    onClick = { /*TODO*/ }) {
                                    Text(text = "Delete", fontSize = 10.sp, color = MaterialTheme.colors.onSurface)
                                }
                                Spacer(modifier = Modifier.width(8.dp))
                            }
                            Spacer(modifier = Modifier.height(4.dp))
                        }



                        }


                    }

                }

            }

    })
}
