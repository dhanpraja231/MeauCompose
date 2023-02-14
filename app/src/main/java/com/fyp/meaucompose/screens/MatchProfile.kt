package com.fyp.meaucompose.screens

import androidx.annotation.DrawableRes
import com.fyp.meaucompose.R

data class MatchProfile(
    val name: String,
    @DrawableRes val drawableResId: Int,
)

val profiles = listOf(
    MatchProfile("Erlich Bachman", R.drawable.icons8_google),
    MatchProfile("Richard Hendricks",R.drawable.icons8_google),
    MatchProfile("Laurie Bream",R.drawable.icons8_google),
    MatchProfile("Russ Hanneman", R.drawable.icons8_google),
    MatchProfile("Dinesh Chugtai", R.drawable.icons8_google),
    MatchProfile("Monica Hall", R.drawable.icons8_google),
//    MatchProfile("Bertram Gilfoyle", R.drawable.gilfoyle),
//
//    MatchProfile("Peter Gregory", R.drawable.peter),
//    MatchProfile("Jared Dunn", R.drawable.jared),
//    MatchProfile("Nelson Bighetti", R.drawable.big_head),
//    MatchProfile("Gavin Belson", R.drawable.gavin),
//    MatchProfile("Jian Yang", R.drawable.jian),
//    MatchProfile("Jack Barker", R.drawable.barker),
)