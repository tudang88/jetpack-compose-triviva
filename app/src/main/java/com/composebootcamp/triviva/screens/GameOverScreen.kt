package com.composebootcamp.triviva.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.composebootcamp.triviva.navigation.Screen
import com.composebootcamp.triviva.ui.theme.ButtonPlayBgColor
import com.composebootcamp.triviva.ui.theme.ButtonPlayCaptionColor

@Composable
fun GameOverScreenContent(navController: NavController? = null) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Image(
                modifier = Modifier.padding(32.dp),
                painter = painterResource(id = R.drawable.try_again),
                contentDescription = "you lose image"
            )
            Button(
                shape = RectangleShape,
                colors = ButtonDefaults.buttonColors(
                    containerColor = ButtonPlayBgColor
                ),
                onClick = {
                    // back to Home for playing again
                    navController?.popBackStack(Screen.HomeScreen.route, inclusive = false)
                }
            ) {
                Text(
                    text = stringResource(id = R.string.try_again),
                    style = TextStyle(color = ButtonPlayCaptionColor, fontWeight = FontWeight.Bold)
                )
            }
        }
}

@Preview(showBackground = true)
@Composable
fun GameOverScreenContentPreview() {
    GameOverScreenContent()
}