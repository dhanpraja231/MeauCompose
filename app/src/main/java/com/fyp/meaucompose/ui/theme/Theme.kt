package com.fyp.meaucompose.ui.theme

import android.content.SharedPreferences
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorPalette = darkColors(
    primary = Color.Black,
    primaryVariant = Color(0xFF363636),
    secondary = Color(0xFF474444),
    onPrimary = Color.LightGray,
    onSecondary = Color(0xFFECE1E1),
    onBackground = Color(0xFFA7A1A1),
    onSurface = Color(0xFFECE1E1),
    background = Color(0xFF727272),
)

private val LightColorPalette = lightColors(
    primary = Color.Black,
    primaryVariant = Color.Black,
    secondary = White,
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,

)

@Composable
fun MeauComposeTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {

    val sp : SharedPreferences = LocalContext.current.getSharedPreferences("settings",0)

    //val spEditor = sp.edit()


    var customDarkTheme = remember{ mutableStateOf(sp.getBoolean("isDarkTheme",false))}

    val colors = if (customDarkTheme.value) {
        DarkColorPalette
    } else {
        //DarkColorPalette
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}