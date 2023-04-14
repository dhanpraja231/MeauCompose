package com.fyp.meaucompose.screens

import androidx.annotation.DrawableRes
import com.fyp.meaucompose.R

data class ProjectItemCard(
    val name: String,
    @DrawableRes val drawableResId: Int,
)

data class ProfileItemCard(
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
)

val sampleProfileItemList = listOf(
    ProfileItemCard("Sudharshan Thirukkumaran",R.drawable.icons8_google),
    ProfileItemCard("Vishnupriya",R.drawable.icons8_google),
    ProfileItemCard("Sudharshan Thirukkumaran 2",R.drawable.icons8_google),
    ProfileItemCard("Subashini",R.drawable.icons8_google),
    ProfileItemCard("Logesh Suresh",R.drawable.icons8_google),
    ProfileItemCard("Subramania Raja",R.drawable.icons8_google),


)