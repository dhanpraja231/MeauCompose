package com.fyp.meaucompose

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.fyp.meaucompose.ui.theme.MeauComposeTheme
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task


class LoginActivity : ComponentActivity() {

    //private lateinit var auth: FirebaseAuth
    lateinit var mGoogleSignInClient: GoogleSignInClient
    private lateinit var signInLauncher: ActivityResultLauncher<Intent>



    override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val account = GoogleSignIn.getLastSignedInAccount(this);
        if (account != null) {
//            System.out.println("launching intent to next page");
            val intent = Intent(this, UserHomeActivity::class.java);
            startActivity(intent);
            finish()
            //account.getEmail()?.let { Log.d("Signed In: ", it) };
        } else {
            Log.d("Signed In: ", "Not signed in");
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        val gso =
            GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build()

        mGoogleSignInClient = GoogleSignIn.getClient(
            this,
            gso
        )// since googleApiClient is deprecated, have to use workaround

        signInLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult(),
            ActivityResultCallback<ActivityResult> { result ->
                val task: Task<GoogleSignInAccount> =
                    GoogleSignIn.getSignedInAccountFromIntent(result.getData())
                handleSignInResult(task)
            })

        super.onCreate(savedInstanceState)

        setContent{
//            LoginScreen(mGoogleSignInClient = mGoogleSignInClient, signInLauncher = signInLauncher)

//            Navigation(mGoogleSignInClient = mGoogleSignInClient, signInLauncher = signInLauncher)

//            LoginScreen(mGoogleSignInClient,signInLauncher, navController = rememberNavController(
//            ))
//        }

//            var username by remember{mutableStateOf<String>("")}
//
            MeauComposeTheme {

                Column(modifier = Modifier.fillMaxSize()
                    , horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center
                ) {
                    val context = LocalContext.current

                    Button(
                        onClick = {
                            //startForResult.launch(googleSignInClient?.signInIntent)
                                  signIn()


//                            val account = GoogleSignIn.getLastSignedInAccount(context);
//                            if (account != null) username = GoogleSignIn.getLastSignedInAccount(context)?.displayName.toString() else username = "not found"


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
//                    Button(
//                        onClick = {
//                            //startForResult.launch(googleSignInClient?.signInIntent)
//                            signOut()
//                            //username = "anonymous"
//                        },
//
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            //.height(100.dp)
//                            .padding(start = 16.dp, end = 16.dp),
//                        shape = RoundedCornerShape(6.dp),
//                        colors = ButtonDefaults.buttonColors(
//                            backgroundColor = Color.Black,
//                            contentColor = Color.White
//                        )
//                    ) {
//                        Image(
//                            painter = painterResource(id = R.drawable.icons8_google),
//                            contentDescription = ""
//                        )
//                        Text(text = "Sign out with Google", modifier = Modifier.padding(6.dp))
//                    }
//                    Text(text = "$username", fontSize = 30.sp, textAlign = TextAlign.Center)
                }
            }
        }
    }

    private fun signIn() {
        println("void sign in")
        val signInIntent = mGoogleSignInClient.signInIntent
        signInLauncher.launch(signInIntent)
        println("got sign in intent")
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>
//                                   ,navController: NavController
) {
        try {
            val account = completedTask.getResult(ApiException::class.java)
            val idToken = account.idToken
            Toast.makeText(this, "Signed in successfully", Toast.LENGTH_SHORT)
                .show()
//            navController.navigate(Screens.UserHomeScreen.route)
            val intent = Intent(this@LoginActivity, UserHomeActivity::class.java)
            startActivity(intent)
        } catch (e: ApiException) {
            Log.w("Sign In Error", "signInResult:failed code=" + e.statusCode)
            Toast.makeText(this, "Sign in failed", Toast.LENGTH_SHORT).show()
        }
    }

//    private fun signOut() {
//        mGoogleSignInClient.signOut()
//            .addOnCompleteListener(this) {
//                Log.d("Signed Out: ", "Successful")
//                Toast.makeText(this, "Signed out successfully", Toast.LENGTH_SHORT)
//                    .show()
//            }
//    }

}



//
//@Composable
//fun Greeting(name: String) {
//    Text(text = "Hello $name!")
//}
//
//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//    MeauComposeTheme {
//        Greeting("Android")
//    }
//}