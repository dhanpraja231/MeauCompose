package com.fyp.meaucompose.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.fyp.meaucompose.R
import com.google.accompanist.flowlayout.FlowRow
import com.google.android.gms.auth.api.signin.GoogleSignIn
import kotlinx.coroutines.launch
import java.util.*
import kotlin.collections.ArrayList

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun EditTagsScreen(navController: NavController){

    var context = LocalContext.current
    var userName: String = GoogleSignIn.getLastSignedInAccount(context)?.displayName.toString()
    val userEmail: String = GoogleSignIn.getLastSignedInAccount(context)?.email.toString()
    val scaffoldState = rememberScaffoldState()
    val scrollState = rememberScrollState()
    val scope = rememberCoroutineScope()

    val options = listOf("Android", "Machine-Learning", "Python", "Java", "C++")

    var expanded by remember { mutableStateOf(false) }
    var selectedOptionText by remember { mutableStateOf(options[0]) }
    val skillNameState = remember {
        mutableStateOf("")
    }

    val existingSkillList = mutableListOf<String>("Animation",
        "Deep Learning","CAD Design",
        "Image-Processing",
        "Rust",
        "Structural-Analysis",
        "Rust",
        "Structural-Analysis",
        "Rust",
        "Structural-Analysis",
        "Rust",
        "Structural-Analysis"
        )

    Scaffold(
        scaffoldState = scaffoldState,
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = { FloatingActionButton(onClick = {navController.navigate(Screens.EditPreferencesScreen.route)}){
            Text("Save")
        } },
        topBar = {
            com.fyp.meaucompose.AppBar(
                onNavigationIconClick = {
                    scope.launch {
                        scaffoldState.drawerState.open()
                    }
                },
                context = context,
                navController = navController,
                titleText = "Edit Skills"
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
                    Modifier
                        .fillMaxSize()
                        .padding(horizontal = 6.dp)) {


                    Column(
                        modifier = Modifier
                            //.verticalScroll(scrollState)
                            .fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Column(
                            Modifier
                                .weight(1f)
                                .fillMaxWidth()
                        ) {
                            Spacer(Modifier.height(8.dp))
                            ExposedDropdownMenuBox(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    ,
                                expanded = expanded,
                                onExpandedChange = {
                                    expanded = !expanded
                                }
                            ) {
                                TextField(
                                    modifier = Modifier.fillMaxWidth(),
                                    readOnly = true,
                                    value = selectedOptionText,
                                    onValueChange = { },
                                    label = {
                                        Text(
                                            "Select skills from options",
                                            color = MaterialTheme.colors.onPrimary
                                        )
                                    },
                                    trailingIcon = {
                                        ExposedDropdownMenuDefaults.TrailingIcon(
                                            expanded = expanded
                                        )
                                    },
                                    colors = ExposedDropdownMenuDefaults.textFieldColors()
                                )
                                ExposedDropdownMenu(
                                    modifier = Modifier.fillMaxWidth(),
                                    expanded = expanded,
                                    onDismissRequest = {
                                        expanded = false
                                    }
                                ) {
                                    options.forEach { selectionOption ->
                                        DropdownMenuItem(
                                            modifier = Modifier.fillMaxWidth(),
                                            onClick = {
                                                selectedOptionText = selectionOption
                                                expanded = false
                                            }
                                        ) {
                                            Text(text = selectionOption, fontSize = 12.sp)
                                        }
                                    }
                                }
                            }

                            Spacer(modifier = Modifier.height(10.dp))

                            Text("Or specify your niche skills here")
                            Spacer(modifier = Modifier.height(2.dp))
                            Surface(
                                elevation = 5.dp, modifier = Modifier
                                    .fillMaxWidth()
                            ) {
                                Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                                    OutlinedTextField(
                                        value = skillNameState.value,
                                        onValueChange = { skillNameState.value = it },
                                        modifier = Modifier.weight(8f)
                                    )
                                    IconButton(
                                        modifier = Modifier.weight(1f),
                                        onClick = { }
                                    ) {
                                        Icon(
                                            Icons.Default.Add,
                                            "contentDescription",
                                        )
                                    }
                                }
                            }
                            Spacer(modifier = Modifier.height(10.dp))
                            Text(text = "Your Skills")
                            Spacer(modifier = Modifier.height(2.dp))
                            Column(
                                Modifier
                                    .weight(2f)
                                    .fillMaxWidth()
                            ) {
                                Surface(elevation = 5.dp, modifier = Modifier.fillMaxSize()) {
                                    LazyColumn(
                                        Modifier
                                            .fillMaxSize()
                                            .padding(top = 4.dp, bottom = 10.dp)
                                    ) {
                                        items(existingSkillList) { item ->
                                            Surface(
                                                Modifier
                                                    .fillMaxWidth()
                                                    .padding(horizontal = 5.dp), elevation = 6.dp
                                            ) {
                                                Row(
                                                    Modifier
                                                        .fillMaxWidth()
                                                        .height(50.dp),
                                                    verticalAlignment = Alignment.CenterVertically
                                                ) {
                                                    Text(
                                                        "$item", modifier = Modifier
                                                            .padding(start = 5.dp)
                                                            .weight(6f)
                                                    )
                                                    IconButton(
                                                        modifier = Modifier.weight(1f),
                                                        onClick = { }
                                                    ) {
                                                        Icon(
                                                            Icons.Default.Delete,
                                                            "contentDescription",
                                                        )
                                                    }

                                                }
                                            }
                                            Spacer(Modifier.height(4.dp))

                                        }
                                    }
                                }
                            }

                        }


                    }
                }
            }
        })



}

