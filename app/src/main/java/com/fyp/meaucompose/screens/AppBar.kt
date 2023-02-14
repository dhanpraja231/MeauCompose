package com.fyp.meaucompose

import android.content.Context
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.fyp.meaucompose.screens.Screens
import com.fyp.meaucompose.screens.signOut
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient

@Composable
fun AppBar(onNavigationIconClick: () -> Unit, context: Context, mGoogleSignInClient: GoogleSignInClient, navController:NavController) {
    TopAppBar(
        title = {
            Text(text = "Welcome ${GoogleSignIn.getLastSignedInAccount(context)?.displayName.toString()}",
                fontSize = 18.sp,
                textAlign = TextAlign.Center)
            Spacer(modifier = Modifier.width(20.dp))
            Button(
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.DarkGray),
                onClick = {

                signOut(context, mGoogleSignInClient) // TODO may need to delete
                navController.navigate(Screens.LoginScreen.route)

            }) {
                Text(text = "sign out", fontSize = 15.sp, textAlign = TextAlign.Center)
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