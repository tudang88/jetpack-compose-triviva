package com.composebootcamp.triviva.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.composebootcamp.triviva.R
import com.composebootcamp.triviva.commonui.ScreenWithNavigationDrawer
import com.composebootcamp.triviva.navigation.Screen
import com.composebootcamp.triviva.ui.theme.ButtonPlayBgColor
import com.composebootcamp.triviva.ui.theme.ButtonPlayCaptionColor
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController?) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    // the drawerState operation request a coroutine scope
    val scope = rememberCoroutineScope()
    ScreenWithNavigationDrawer(
        navController = navController,
        drawerState = drawerState,
        title = stringResource(id = R.string.android_trivia),
        onLeadingClick = {
            // on DrawerMenu click
            scope.launch {
                drawerState.apply {
                    if (isClosed) open() else close()
                }
            }
        },
        navIcon = Icons.Filled.Menu
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