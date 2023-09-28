package com.composebootcamp.triviva.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.composebootcamp.triviva.R
import com.composebootcamp.triviva.commonui.ScreenWithNavigationDrawerTemplate
import com.composebootcamp.triviva.navigation.Screen
import com.composebootcamp.triviva.ui.theme.ButtonPlayBgColor
import com.composebootcamp.triviva.ui.theme.ButtonPlayCaptionColor
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController?) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    var overFlowMenuExpanded by remember {
        mutableStateOf(false)
    }
    // the drawerState operation request a coroutine scope
    val scope = rememberCoroutineScope()
    ScreenWithNavigationDrawerTemplate(
        drawerState = drawerState,
        title = stringResource(id = R.string.android_trivia),
        onMenuItemClick = { item ->
            navController?.navigate(item.route)
        },
        onLeadingClick = {
            // on DrawerMenu click
            scope.launch {
                drawerState.apply {
                    if (isClosed) open() else close()
                }
            }
        },
        navIcon = Icons.Filled.Menu,
        actionMenu = {
            IconButton(onClick = { overFlowMenuExpanded = !overFlowMenuExpanded }) {
                Icon(imageVector = Icons.Filled.MoreVert, contentDescription = "Overflow menu")
            }
            DropdownMenu(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        navController?.navigate(Screen.AboutScreen.route)
                    },
                expanded = overFlowMenuExpanded,
                onDismissRequest = { overFlowMenuExpanded = false }) {
                Text(modifier = Modifier.align(CenterHorizontally),
                    text = stringResource(id = R.string.about),
                    style = TextStyle(fontSize = 22.sp),
                    textAlign = TextAlign.Center
                )

            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(paddingValues = padding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Image(
                painter = painterResource(id = R.drawable.android_trivia),
                contentDescription = "home image",
                modifier = Modifier.padding(all = 24.dp)
            )
            Button(shape = RectangleShape,
                colors = ButtonDefaults.buttonColors(
                    containerColor = ButtonPlayBgColor
                ),
                onClick = {
                    navController?.navigate(Screen.GameScreen.route)
                }) {
                Text(
                    text = stringResource(id = R.string.play_button_text),
                    style = TextStyle(color = ButtonPlayCaptionColor, fontWeight = FontWeight.Bold)
                )
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(navController = null)
}