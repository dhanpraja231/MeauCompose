package com.fyp.meaucompose.screens
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.navigation.NavHostController

data class NavDrawerItem (
    val id:String,
    val title:String,
    val contentDescription:String,
    val icon: ImageVector
)



@Composable
fun DrawerHeader() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Gray)
            .padding(vertical = 24.dp),
        contentAlignment = Alignment.Center
    ){
        Text(text="Replace image",fontSize = 40.sp)
    }
}

@Composable
fun DrawerBody(
    //items: List<NavDrawerItem>,
    //will need the navigation controller and context
    navController: NavController,
    context: Context,
    modifier: Modifier = Modifier,
    itemTextStyle: TextStyle = TextStyle(fontSize = 18.sp),
 //   onItemCLick: (NavDrawerItem) -> Unit
) {

    val items =
        listOf(
            NavDrawerItem(
                id = "Home Screen",
                title = "Home",
                contentDescription = "Go to User home screen",
                icon = Icons.Default.Home
            ),
            NavDrawerItem(
                id = "Edit Preferences",
                title = "Edit Preferences",
                contentDescription = "Go to Edit Preferences screen",
                icon = Icons.Default.Favorite
            ),
            NavDrawerItem(
                id = "My Projects",
                title = "My Projects",
                contentDescription = "Go to My projects screen",
                icon = Icons.Default.Build
            ),
            NavDrawerItem(
                id = "settings",
                title = "Settings",
                contentDescription = "Go to settings screen",
                icon = Icons.Default.Settings
            ),
            NavDrawerItem(
                id = "Matches",
                title = "View Matches",
                contentDescription = "View your matches",
                icon = Icons.Default.Check
            ),
            NavDrawerItem(
                id = "FAQ",
                title = "FAQ",
                contentDescription = "Go to FAQ screen",
                icon = Icons.Default.Info
            ),
        )

    val onItemCLick: (NavDrawerItem,NavController) -> Unit = {
        it, navController ->

        when(it.id){
            "Home Screen" -> navController.navigate(Screens.UserHomeScreen.route)
            "My Projects" -> navController.navigate(Screens.MyProjectsScreen.route)
            "Edit Preferences" -> navController.navigate(Screens.EditPreferencesScreen.route)
            "FAQ" -> navController.navigate(Screens.FAQScreen.route)
            else -> Toast.makeText(context, "clicked on ${it.title}", Toast.LENGTH_SHORT).show()
        }

    }



    LazyColumn(modifier){
        items(items){
                item ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .clickable {
                        onItemCLick(item,navController)
                    }
            ){
                Icon(imageVector = item.icon, contentDescription = item.contentDescription )
                Spacer(modifier = Modifier.width(16.dp))
                Text(text = item.title,
                    modifier = Modifier.weight(1f))
            }
        }
    }

}