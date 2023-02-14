package com.fyp.meaucompose

import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.fyp.meaucompose.screens.Screens
import com.fyp.meaucompose.ui.theme.MeauComposeTheme
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun LoginScreen(mGoogleSignInClient: GoogleSignInClient,
                signInLauncher: ActivityResultLauncher<Intent>,
                navController: NavController
                ) {

    lateinit var auth: FirebaseAuth

    var context: Context = LocalContext.current

    MeauComposeTheme {

        var username by remember { mutableStateOf<String>("") }

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            val context = LocalContext.current

            Button(
                onClick = {

                    signIn(mGoogleSignInClient, signInLauncher)
                    //TODO update your single source of truth and watch for changes there and let use username on the second activity
                    // be derived from the single source of truth
                    navController.navigate(Screens.UserHomeScreen.route)

//                    GlobalScope.launch {
//                        signIn(mGoogleSignInClient, signInLauncher)
//                        delay(1000)
//                        Toast.makeText(context, "Signed in successfully", Toast.LENGTH_SHORT)
//                            .show()
//                        navController.navigate(Screens.UserHomeScreen.route)
//                    }
                },

                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 10.dp),
                //.height(100.dp)
                //.padding(start = 16.dp, end = 16.dp),
                shape = RoundedCornerShape(6.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.Black,
                    contentColor = Color.White
                )
            ) {
                Image(
                    painter = painterResource(id = R.drawable.icons8_google),
                    contentDescription = ""
                )
                Text(text = "Sign in with Google", modifier = Modifier.padding(6.dp))
            }
//            Button(
//                onClick = {
//                    //startForResult.launch(googleSignInClient?.signInIntent)
//                    signOut(context,mGoogleSignInClient)
//                    username = "anonymous"
//                },
//
//                modifier = Modifier
//                    .fillMaxWidth()
//                    //.height(100.dp)
//                    .padding(start = 16.dp, end = 16.dp),
//                shape = RoundedCornerShape(6.dp),
//                colors = ButtonDefaults.buttonColors(
//                    backgroundColor = Color.Black,
//                    contentColor = Color.White
//                )
//            ) {
//                Image(
//                    painter = painterResource(id = R.drawable.icons8_google),
//                    contentDescription = ""
//                )
//                Text(text = "Sign out with Google", modifier = Modifier.padding(6.dp))
//            }
//            Text(text = "$username", fontSize = 30.sp, textAlign = TextAlign.Center)
        }
    }


}
 fun signIn(mGoogleSignInClient: GoogleSignInClient,signInLauncher: ActivityResultLauncher<Intent>) {
    println("void sign in")
    val signInIntent = mGoogleSignInClient.signInIntent
    signInLauncher.launch(signInIntent)
    println("got sign in intent")
}

//fun handleSignInResult(completedTask: Task<GoogleSignInAccount>, context: Context) {
//    try {
//        val account = completedTask.getResult(ApiException::class.java)
//        val idToken = account.idToken
//
//        Toast.makeText(context, "Signed in successfully", Toast.LENGTH_SHORT)
//            .show()
//        navController.navigate(Screens.UserHomeScreen.route)
////            val intent = Intent(this@LoginActivity, UserHomeActivity::class.java)
////            startActivity(intent)
//    } catch (e: ApiException) {
//        Log.w("Sign In Error", "signInResult:failed code=" + e.statusCode)
//        Toast.makeText(context, "Sign in failed", Toast.LENGTH_SHORT).show()
//    }
//}


//fun signOut(context: Context, mGoogleSignInClient: GoogleSignInClient) {
//    mGoogleSignInClient.signOut()
//        .addOnCompleteListener(
//            //context.mainExecutor
//        ) {
//            Log.d("Signed Out: ", "Successful")
//            Toast.makeText(context, "Signed out successfully", Toast.LENGTH_SHORT)
//                .show()
//
//
//        }
//}


