package com.fyp.meaucompose.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.fyp.meaucompose.AppBar
import com.google.android.gms.auth.api.signin.GoogleSignIn
import kotlinx.coroutines.launch


data class FAQItem(val question:String, val answer:String)

@Composable
fun FAQScreen(navController:NavController) {
    var context = LocalContext.current
    var userName: String = GoogleSignIn.getLastSignedInAccount(context)?.displayName.toString()
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            AppBar(onNavigationIconClick = {
                scope.launch {
                    scaffoldState.drawerState.open()
                }
            },
                context = context,
                navController = navController
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
            FAQMainContent(scaffoldPadding = scaffoldPadding)
        })
}

@Composable
fun FAQMainContent(scaffoldPadding: PaddingValues){
    LazyColumn(modifier = Modifier.padding(scaffoldPadding)){

        itemsIndexed(
            listOf(
                FAQItem("What is MeAU?","MeAU is an application used to connect mentors and mentees with regards to an" +
                        "academic or industrial project"),
                FAQItem("How can I log in? ","Use your google account to login, it's a simple one-tap sign in process"),
                FAQItem("Do I need to create an account?","No. There is no need to create an account"),
                FAQItem("If I swipe left accidentally will I be able to see the profile again?","Edit your tags to reset the project suggestions," +
                        " and you will be able to see the corresponding profiles and projects again"),
                FAQItem("Is my data shared with 3rd party services?","We respect your privacy and your data will not be shared with any 3rd party services"),
                FAQItem("Who is this application managed by?","This application is managed and maintained by the ECE department of CEG"),


                )
        ){
                index, FAQItem ->
            Surface(elevation = 4.dp,modifier= Modifier.padding(4.dp), shape = RoundedCornerShape(4.dp)) {
                Column() {
                    Text(
                        text = "${FAQItem.question}",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Left,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp, horizontal = 4.dp)
                    )
                    Text(
                        text = "${FAQItem.answer}",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Light,
                        textAlign = TextAlign.Left,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 6.dp, horizontal = 4.dp)
                    )
                }
            }

        }

    }
}