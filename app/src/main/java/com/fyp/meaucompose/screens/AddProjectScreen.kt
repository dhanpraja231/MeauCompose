package com.fyp.meaucompose.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.fyp.meaucompose.AppBar
import com.google.android.gms.auth.api.signin.GoogleSignIn
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AddProjectScreen(navController: NavController){

    var context = LocalContext.current
    var userName: String = GoogleSignIn.getLastSignedInAccount(context)?.displayName.toString()
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    val scrollState = rememberScrollState()

    val etProjectNameState = remember{ mutableStateOf("") }
    val etProjectDescription = remember{ mutableStateOf("") }
    val etProjectComment = remember{ mutableStateOf("") }
    val etProjectIsSuspend = remember{ mutableStateOf(false) }


    val options = listOf("Android", "Machine-Learning", "Python", "Java", "C++")
    var isExpanded by remember { mutableStateOf(false) }
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
        floatingActionButton = { FloatingActionButton(onClick = {

        }){
            Text("  Request Approval  ")
        } },
        topBar = {
            AppBar(onNavigationIconClick = {
                scope.launch {
                    scaffoldState.drawerState.open()
                }
            },
                context = context,
                navController = navController,
                titleText = "Add new Project"
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
            Surface() {
                Column(
                    Modifier
                        .fillMaxSize()
                        .verticalScroll(scrollState)
                        .padding(scaffoldPadding)) {
                    Column(Modifier.padding(horizontal = 6.dp)) {
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(text = "Enter Project Name")
                        OutlinedTextField(
                            modifier = Modifier.fillMaxWidth(),
                            value = etProjectNameState.value,
                            onValueChange = { etProjectNameState.value = it })
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(text = "Enter Project Description")
                        OutlinedTextField(modifier = Modifier.fillMaxWidth(),value = etProjectDescription.value, onValueChange = {etProjectDescription.value = it})
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(text = "Additional Comments")
                        OutlinedTextField(modifier = Modifier.fillMaxWidth(),value = etProjectComment.value, onValueChange = {etProjectComment.value = it})
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(text = "Suspend Project?")
                        Text(text= "Suspended projects will not be visible to other users", fontStyle = FontStyle.Italic, fontSize = 12.sp)
//                        Row(Modifier.fillMaxWidth()) {
//                        }
                        Spacer(Modifier.height(3.dp))
                        Row(Modifier.fillMaxWidth()) {
                            Row(Modifier.weight(1f), verticalAlignment = Alignment.CenterVertically) {
                                RadioButton(selected = etProjectIsSuspend.value, onClick = { etProjectIsSuspend.value = true })
                                Text("Yes")
                            }
                            Row(Modifier.weight(1f), verticalAlignment = Alignment.CenterVertically){
                                RadioButton(selected = !etProjectIsSuspend.value, onClick = { etProjectIsSuspend.value = false })
                                Text("No")
                            }
                        }
                        Column(
                            Modifier
                                //.weight(1f)
                                .fillMaxWidth()
                        ) {
                            Spacer(Modifier.height(8.dp))
                            ExposedDropdownMenuBox(
                                modifier = Modifier
                                    .fillMaxWidth()
                                ,
                                expanded = isExpanded,
                                onExpandedChange = {
                                    isExpanded = !isExpanded
                                }
                            ) {
                                TextField(
                                    modifier = Modifier.fillMaxWidth(),
                                    readOnly = true,
                                    value = selectedOptionText,
                                    onValueChange = { },
                                    label = {
                                        Text(
                                            "Select requirements from options",
                                            color = MaterialTheme.colors.onPrimary
                                        )
                                    },
                                    trailingIcon = {
                                        ExposedDropdownMenuDefaults.TrailingIcon(
                                            expanded = isExpanded
                                        )
                                    },
                                    colors = ExposedDropdownMenuDefaults.textFieldColors()
                                )
                                ExposedDropdownMenu(
                                    modifier = Modifier.fillMaxWidth(),
                                    expanded = isExpanded,
                                    onDismissRequest = {
                                        isExpanded = false
                                    }
                                ) {
                                    options.forEach { selectionOption ->
                                        DropdownMenuItem(
                                            modifier = Modifier.fillMaxWidth(),
                                            onClick = {
                                                selectedOptionText = selectionOption
                                                isExpanded = false
                                            }
                                        ) {
                                            Text(text = selectionOption, fontSize = 12.sp)
                                        }
                                    }
                                }
                            }

                            Spacer(modifier = Modifier.height(10.dp))

                            Text("Or specify your niche requirements here")
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
                            Text(text = "Your Requirements")
                            Spacer(modifier = Modifier.height(2.dp))
                            Column(
                                Modifier
                                    //.weight(2f)
                                    .fillMaxWidth()
                            ) {
                                Surface(elevation = 5.dp, modifier = Modifier.fillMaxSize()) {
                                    LazyColumn(
                                        Modifier
                                            .height(300.dp)
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
                            Spacer(Modifier.height(6.dp))
                        }



                    }

                }
            }



        })

}
