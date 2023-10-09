package com.composebootcamp.triviva.screens

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.NavController
import com.composebootcamp.triviva.R
import com.composebootcamp.triviva.commonui.ScreenTemplate
import com.composebootcamp.triviva.navigation.Screen
import com.composebootcamp.triviva.ui.theme.ButtonPlayBgColor
import com.composebootcamp.triviva.ui.theme.ButtonPlayCaptionColor

@Composable
fun GameWonScreen(navController: NavController? = null, numOfCorrect: Int, totalQuiz: Int) {
    ScreenTemplate(onLeadingClick = {
        navController?.popBackStack()
    }, title = stringResource(id = R.string.android_trivia),
        actionMenu = {
            // get the local context for accessing resource and send intent
            val mContext = LocalContext.current
            IconButton(onClick = {
                // share result through other app
                val sendIntent: Intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(
                        Intent.EXTRA_TEXT,
                        mContext.getString(R.string.share_success_text, numOfCorrect, totalQuiz)
                    )
                    type = "text/plain"
                }
                val shareIntent = Intent.createChooser(sendIntent, null)
                mContext.startActivity(shareIntent)
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.share),
                    contentDescription = "button share"
                )
            }
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Image(
                modifier = Modifier.padding(32.dp),
                painter = painterResource(id = R.drawable.you_win),
                contentDescription = "you win image"
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
}

@Composable
fun GameWonScreenContent(navController: NavController? = null) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Image(
            modifier = Modifier.padding(32.dp),
            painter = painterResource(id = R.drawable.you_win),
            contentDescription = "you win image"
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
fun GameWonScreenContentPreview() {
    GameOverScreenContent()
}
@Preview
@Composable
fun GameWonScreenPreview() {
    GameWonScreen(numOfCorrect = 10, totalQuiz = 10)
}