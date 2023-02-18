package com.fyp.meaucompose

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import com.fyp.meaucompose.ui.theme.MeauComposeTheme

class UserHomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MeauComposeTheme() {
                Navigation()
            }
            //Navigation(mGoogleSignInClient = , signInLauncher = )

        }


    }
}