package com.fyp.meaucompose.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.alexstyl.swipeablecard.Direction
import com.alexstyl.swipeablecard.ExperimentalSwipeableCardApi
import com.alexstyl.swipeablecard.rememberSwipeableCardState
import com.alexstyl.swipeablecard.swipableCard
import com.fyp.meaucompose.AppBar
import com.google.android.gms.auth.api.signin.GoogleSignIn
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalSwipeableCardApi::class)
@Composable
fun RecommenderForProjectScreen(navController: NavController){
    //TODO: get the project name, ID and tags and display it
    val projectName: String = "Sample project"
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
                //mGoogleSignInClient = mGoogleSignInClient,
                navController = navController,
                titleText =  projectName
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
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(scaffoldPadding)
                    .background(
                        Brush.verticalGradient(
                            listOf(
                                //Color(0xffffffff),
                                MaterialTheme.colors.secondary,
                                MaterialTheme.colors.secondary,
                            )
                        )
                    )
                    .systemBarsPadding()
            ) {
                Box() {
                    val states = sampleProfileItemList.reversed()
                        .map { it to rememberSwipeableCardState() }
                    var hint by remember {
                        mutableStateOf("Swipe a card or press a button below")
                    }

                    //Hint(hint)

                    val scope: CoroutineScope = rememberCoroutineScope()
                    Box(
                        Modifier
                            .padding(24.dp)
                            .fillMaxSize()
                            .aspectRatio(1f)
                            .align(Alignment.Center)
                    ) {
                        states.forEach { (matchProfile, state) ->
                            if (state.swipedDirection == null) {
                                ProfileCard(
                                    modifier = Modifier
                                        .requiredHeight(600.dp)
                                        .swipableCard(
                                            state = state,
                                            blockedDirections = listOf(Direction.Down),
                                            onSwiped = {
                                                // swipes are handled by the LaunchedEffect
                                                // so that we track button clicks & swipes
                                                // from the same place
                                            },
                                            onSwipeCancel = {
                                                Log.d("Swipeable-Card", "Cancelled swipe")
                                                hint = "You canceled the swipe"
                                            }
                                        ),
                                    matchProfile = matchProfile
                                )
                            }
                            LaunchedEffect(matchProfile, state.swipedDirection) {
                                if (state.swipedDirection != null) {
                                    hint = "You swiped ${stringFrom(state.swipedDirection!!)}"
                                }
                            }
                        }
                    }
                    Row(
                        Modifier
                            .align(Alignment.BottomCenter)
                            .padding(horizontal = 24.dp, vertical = 32.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {

                    }
                }
            }
        }

    )


}

private fun stringFrom(direction: Direction): String {
    return when (direction) {
        Direction.Left -> "Left 👈"
        Direction.Right -> "Right 👉"
        Direction.Up -> "Up 👆"
        Direction.Down -> "Down 👇"
    }
}


@Composable
private fun ProfileCard(
    modifier: Modifier,
    matchProfile: ProfileItemCard,
) {
    //TODO: change card height
    Card(modifier = modifier) {
        Box() {
            Image(contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize(),
                painter = painterResource(matchProfile.drawableResId),
                contentDescription = null)
            Scrim(Modifier.align(Alignment.BottomCenter))
            Column(Modifier.align(Alignment.BottomStart)) {
                Text(text = matchProfile.name,
                    color = MaterialTheme.colors.onPrimary,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(10.dp))
            }
        }
    }
}