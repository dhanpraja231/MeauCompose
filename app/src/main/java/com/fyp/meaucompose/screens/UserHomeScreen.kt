package com.fyp.meaucompose.screens

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.alexstyl.swipeablecard.Direction
import com.alexstyl.swipeablecard.ExperimentalSwipeableCardApi
import com.alexstyl.swipeablecard.rememberSwipeableCardState
import com.alexstyl.swipeablecard.swipableCard
import com.fyp.meaucompose.AppBar
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@OptIn(ExperimentalSwipeableCardApi::class)
@Composable
fun UserHomeScreen(navController: NavController
 //                  , mGoogleSignInClient: GoogleSignInClient
)
{
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
            navController = navController

                )
        },
        drawerContent = {
            DrawerHeader(
            )
            DrawerBody(
//                items =
//            listOf(
//                NavDrawerItem(
//                    id = "home",
//                    title = "Home",
//                    contentDescription = "Go to home screen",
//                    icon = Icons.Default.Home
//                ),
//                NavDrawerItem(
//                    id = "settings",
//                    title = "Settings",
//                    contentDescription = "Go to settings screen",
//                    icon = Icons.Default.Settings
//                ),
//            ), onItemCLick = {
//                Toast.makeText(context, "clicked on ${it.title}", Toast.LENGTH_SHORT).show()
//            }
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
//                Row(
//                    modifier = Modifier.fillMaxWidth(),
//                    horizontalArrangement = Arrangement.SpaceEvenly
//                ) {
//                    Text(text = "welcome $userName", fontSize = 20.sp, textAlign = TextAlign.Center)
//                    Button(onClick = {
//
//                        signOut(context, mGoogleSignInClient) // TODO may need to delete
//                        navController.navigate(Screens.LoginScreen.route)
//
////                GlobalScope.launch {
////                    signOut(context,mGoogleSignInClient)
////                }.invokeOnCompletion { navController.navigate(Screens.LoginScreen.route)}
//
//
//                    }) {
//                        Text(text = "sign out", fontSize = 15.sp, textAlign = TextAlign.Center)
//                    }
//                }


                Box() {
                    val states = sampleProjectList.reversed()
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
//                        CircleButton(
//                            onClick = {
//                                scope.launch {
//                                    val last = states.reversed()
//                                        .firstOrNull {
//                                            it.second.offset.value == Offset(0f, 0f)
//                                        }?.second
//                                    last?.swipe(Direction.Left)
//                                }
//                            },
//                            icon = Icons.Rounded.Close
//                        )
//                        CircleButton(
//                            onClick = {
//                                scope.launch {
//                                    val last = states.reversed()
//                                        .firstOrNull {
//                                            it.second.offset.value == Offset(0f, 0f)
//                                        }?.second
//
//                                    last?.swipe(Direction.Right)
//                                }
//                            },
//                            icon = Icons.Rounded.Favorite
//                        )
                    }
                }
            }
        }

        )


}


@Composable
private fun CircleButton(
    onClick: () -> Unit,
    icon: ImageVector,
) {
    IconButton(
        modifier = Modifier
            .clip(CircleShape)
            .background(MaterialTheme.colors.primary)
            .size(56.dp)
            .border(2.dp, MaterialTheme.colors.primary, CircleShape),
        onClick = onClick
    ) {
        Icon(icon, null,
            tint = MaterialTheme.colors.onPrimary)
    }
}

@Composable
private fun ProfileCard(
    modifier: Modifier,
    matchProfile: ProjectItemCard,
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

@Composable
private fun Hint(text: String) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .padding(horizontal = 24.dp, vertical = 32.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = text,
            color = Color.DarkGray,
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp,
            textAlign = TextAlign.Center
        )
    }
}

//@Composable
//private fun TransparentSystemBars() {
//    val systemUiController = rememberSystemUiController()
//    val useDarkIcons = false
//
//    DisposableEffect(systemUiController, useDarkIcons) {
//        systemUiController.setSystemBarsColor(
//            color = Color.Transparent,
//            darkIcons = useDarkIcons,
//            isNavigationBarContrastEnforced = false
//        )
//        onDispose {}
//    }
//}

private fun stringFrom(direction: Direction): String {
    return when (direction) {
        Direction.Left -> "Left 👈"
        Direction.Right -> "Right 👉"
        Direction.Up -> "Up 👆"
        Direction.Down -> "Down 👇"
    }
}


@Composable
fun Scrim(modifier: Modifier = Modifier) {
    Box(
        modifier
            .background(Brush.verticalGradient(listOf(Color.Transparent, Color.Black)))
            .height(180.dp)
            .fillMaxWidth())
}

fun signOut(context: Context, mGoogleSignInClient: GoogleSignInClient) {
    mGoogleSignInClient.signOut()
        .addOnCompleteListener(
            //context.mainExecutor
        ) {
            Log.d("Signed Out: ", "Successful")
            Toast.makeText(context, "Signed out successfully", Toast.LENGTH_SHORT)
                .show()
        }

}
