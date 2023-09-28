package com.composebootcamp.triviva.menu

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.composebootcamp.triviva.R
import com.composebootcamp.triviva.ui.theme.Purple80

@Composable
fun DrawerHeader() {
    Box(
        modifier = Modifier
            .background(color = Purple80)
            .fillMaxWidth()
            .padding(32.dp),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.android_trivia),
            contentDescription = "drawer header"
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DrawerBody(
    modifier: Modifier = Modifier, items: List<MenuItem>,
    itemTextStyle: TextStyle = TextStyle(fontSize = 24.sp),
    onItemClick: (MenuItem) -> Unit
) {
    ModalDrawerSheet(modifier) {
        DrawerHeader()
        Divider()
        LazyColumn(modifier) {
            items(items) { item ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .clickable { onItemClick(item) }
                ) {
                    Icon(
                        modifier = Modifier.align(CenterVertically),
                        painter = painterResource(id = item.iconResource),
                        contentDescription = "menu icon"
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(
                        text = item.title,
                        style = itemTextStyle,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.align(CenterVertically)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun DrawerHeaderPreview() {
    DrawerHeader()
}

@Preview
@Composable
fun DrawerBodyPreview() {
    DrawerBody(items = menuList, onItemClick = {})
}