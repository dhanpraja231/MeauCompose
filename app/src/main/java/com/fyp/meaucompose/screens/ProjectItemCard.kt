package com.fyp.meaucompose.screens

import androidx.annotation.DrawableRes
import com.fyp.meaucompose.R

data class ProjectItemCard(
    val name: String,
    @DrawableRes val drawableResId: Int,
)

val sampleProjectList = listOf(
    ProjectItemCard("Android Project", R.drawable.icons8_google),
    ProjectItemCard("Image Processing Project",R.drawable.icons8_google),
    ProjectItemCard("Uber Clone",R.drawable.icons8_google),
    ProjectItemCard("Sample Project4", R.drawable.icons8_google),
    ProjectItemCard("Google Clone", R.drawable.icons8_google),
    ProjectItemCard("Sample Project 5", R.drawable.icons8_google),
//    MatchProfile("Bertram Gilfoyle", R.drawable.gilfoyle),
//
//    MatchProfile("Peter Gregory", R.drawable.peter),
//    MatchProfile("Jared Dunn", R.drawable.jared),
//    MatchProfile("Nelson Bighetti", R.drawable.big_head),
//    MatchProfile("Gavin Belson", R.drawable.gavin),
//    MatchProfile("Jian Yang", R.drawable.jian),
//    MatchProfile("Jack Barker", R.drawable.barker),
)