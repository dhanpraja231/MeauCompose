package com.fyp.meaucompose

import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.*


import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.NavController
import com.fyp.meaucompose.screens.signOut
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions

@Composable
fun AppBar(onNavigationIconClick: () -> Unit, context: Context,
           //mGoogleSignInClient: GoogleSignInClient,
           navController:NavController) {
    var userName = GoogleSignIn.getLastSignedInAccount(context)?.displayName.toString()
    if (userName.length > 10){
        userName = userName.substring(0,11)
    }

//    val isSignedIn by remember {
//        mutableStateOf(GoogleSignIn.getLastSignedInAccount(context))
//    }
//    val username by remember {
//        mutableStateOf(isSignedIn?.displayName.toString())
//    }

    TopAppBar(

        title = {
            Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxSize()) {
                Box(Modifier.fillMaxHeight(), contentAlignment = Alignment.Center){
                Text(text = "Welcome ${userName}",
                    fontSize = 18.sp,
                    modifier = Modifier.fillMaxHeight().widthIn(min = 150.dp,max = 190.dp).padding(top= 10.dp),
                    textAlign = TextAlign.Center,
                )}
                Spacer(modifier = Modifier.width(20.dp))
                Button(

                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.DarkGray),
                    onClick = {

                        signOut(context) // TODO may need to delete
                        val intent = Intent(context, LoginActivity::class.java)
                        startActivity(context,intent,null)
//                val intent = Intent(context,LoginActivity::class)
                        //navController.navigate(Screens.LoginScreen.route)

                    }) {
                    Text(text = "sign out", fontSize = 15.sp, textAlign = TextAlign.Center)
                }
            }


        },
        backgroundColor = MaterialTheme.colors.primary,
        contentColor = MaterialTheme.colors.onPrimary,
        navigationIcon = {
            IconButton(onClick = onNavigationIconClick){
                Icon(imageVector = Icons.Default.Menu
                    , contentDescription = "Toggle Drawer" )
            }
        }
    )
}

private fun signOut(context:Context) {
    val gso =
        GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build()
    val mGoogleSignInClient = GoogleSignIn.getClient(context, gso)
    mGoogleSignInClient.signOut()
        .addOnCompleteListener() {
            Log.d("Signed Out: ", "Successful")
            Toast.makeText(context, "Signed out successfully", Toast.LENGTH_SHORT)
                .show()
        }
}