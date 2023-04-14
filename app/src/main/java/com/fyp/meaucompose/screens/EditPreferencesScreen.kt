package com.fyp.meaucompose.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.fyp.meaucompose.AppBar
import com.fyp.meaucompose.R
import com.google.accompanist.flowlayout.FlowRow
import com.google.android.gms.auth.api.signin.GoogleSignIn
import kotlinx.coroutines.launch


@Composable
fun EditPreferencesScreen(navController: NavController){

    var context = LocalContext.current
    var userName: String = GoogleSignIn.getLastSignedInAccount(context)?.displayName.toString()
    val userEmail: String = GoogleSignIn.getLastSignedInAccount(context)?.email.toString()
    val scaffoldState = rememberScaffoldState()
    val scrollState = rememberScrollState()
    val scope = rememberCoroutineScope()

    //states TODO: populate values from DB
    val displayNameValue = remember { mutableStateOf("Example name")}
    val displayDescriptionValue = remember { mutableStateOf("Example about")}
    val displayAddTagsValue = remember { mutableStateOf("")}

    val displayDepartmentValue = remember { mutableStateOf("")}

    Scaffold(
        scaffoldState = scaffoldState,
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = { FloatingActionButton(onClick = {}){
            Text("Save")
        } },
        topBar = {
            AppBar(onNavigationIconClick = {
                scope.launch {
                    scaffoldState.drawerState.open()
                }
            },
                context = context,
                navController = navController,
                titleText = "Edit Preferences"
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
            Surface(
                Modifier
                    .padding(scaffoldPadding)
                    .fillMaxSize()) {
                Column(
                    modifier = Modifier
                        .verticalScroll(scrollState)
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
//            verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(Modifier.fillMaxWidth(0.9f)) {
                        //display email - non edit
                        Spacer(Modifier.height(8.dp))
                        Text(
                            text = "Email: $userEmail",
                            fontSize = 20.sp
                        ) //TODO: get mail from db
                        Spacer(modifier = Modifier.height(20.dp))
                        //display userId - non edit
//                    Text(text = "User: Example@gmail.com") //TODO: get mail from db
                        //display name
                        Text(text = "Display Name")
                        OutlinedTextField(
                            value = displayNameValue.value,
                            onValueChange = { displayNameValue.value = it },
                            modifier = Modifier.fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        //display description
                        Text(text = "About")
                        OutlinedTextField(
                            value = displayDescriptionValue.value,
                            onValueChange = { displayDescriptionValue.value = it },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(80.dp)
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        //display tags and search bar
                        Text(text = "Your skills")
                        Spacer(modifier = Modifier.height(5.dp))
//                        LazyVerticalGrid(columns = GridCells.Adaptive(300.dp),Modifier.height(120.dp).fillMaxWidth()){
//                            items(listOf("Android","Python","BDSM","Rainbow")) {
//                                CategoryChip(textContent = it, isSelected = false)
//                            }
//                        }
                        FlowRow(modifier = Modifier.fillMaxWidth()) {
                            FLowRowContent()
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        Button(
                            onClick = { navController.navigate(Screens.EditTagsScreen.route) }, modifier = Modifier
                                .fillMaxWidth()
//                            .border(
//                                1.dp, Color.Black,
//                                RoundedCornerShape(50)
//                            )
                            , shape = RoundedCornerShape(50),
                            colors = ButtonDefaults.buttonColors(Color.White)
                        ) {
                            Text("Edit Tags", color = Color.Black)
                        }
//                        OutlinedTextField(value = displayAddTagsValue.value, onValueChange = {displayAddTagsValue.value= it}, modifier = Modifier.fillMaxWidth() )
//                        Spacer(modifier = Modifier.height(10.dp))


                    }

                }
            }


                //display contact information email
                //display contact information mobile number
                //display contact information mobile other
                
                //display Save button
    
        })
}

@Composable
fun FLowRowContent() {
    val tagsList: List<String> = listOf("Android","Python","BDSM","Rainbow-Harsh","Andrew Tate Top G","Python","BDSM","Rainbow","Python","BDSM","Rainbow",
        "Python","BDSM","Rainbow","Python","BDSM","Rainbow") //TODO: Get tags list from network call
    for(tag in tagsList){
        CategoryChip(textContent = tag, isSelected = false)
    }

}

@Composable
fun CategoryChip(
    textContent: String,
    isSelected: Boolean = false,
//    onSelectedCategoryChanged: (String) -> Unit,
//    onExecuteSearch: () -> Unit
) {
    Surface(
        modifier = Modifier
            .padding(end = 8.dp, bottom = 8.dp)
            .border(1.dp, Color.Black, RoundedCornerShape(16.dp)),
        //elevation = 8.dp,
        shape = RoundedCornerShape(16.dp),
        color = when {
            isSelected -> colorResource(R.color.teal_200)
            else -> MaterialTheme.colors.secondary
        }
    ) {
        Row(modifier = Modifier
//            .toggleable(
//                value = isSelected,
//                onValueChange = {
//                    onSelectedCategoryChanged(textContent)
//                    onExecuteSearch()
//                }
//            )
        ) {
            Text(
                text = textContent,
                fontSize = 12.sp,
                color = MaterialTheme.colors.onSecondary,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}


@Preview
@Composable
fun Preview() {
    EditPreferencesScreen(rememberNavController())
}