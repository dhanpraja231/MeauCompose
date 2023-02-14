package com.fyp.meaucompose.screens
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
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
    items: List<NavDrawerItem>,
    modifier: Modifier = Modifier,
    itemTextStyle: TextStyle = TextStyle(fontSize = 18.sp),
    onItemCLick: (NavDrawerItem) -> Unit
) {
    LazyColumn(modifier){
        items(items){
                item ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .clickable {
                        onItemCLick(item)
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