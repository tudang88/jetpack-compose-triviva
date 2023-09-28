package com.composebootcamp.triviva.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.composebootcamp.triviva.R
import com.composebootcamp.triviva.commonui.ScreenTemplate

@Composable
fun AboutScreen(navController: NavController? = null) {
    ScreenTemplate(
        onLeadingClick = {
            navController?.popBackStack()
        },
        title = stringResource(id = R.string.about)
    ) {
        Column(modifier = Modifier
            .padding(it)
            .padding(top = 4.dp)) {
            Image(
                modifier = Modifier.padding(16.dp),
                painter = painterResource(id = R.drawable.about_android_trivia),
                contentDescription = "about image"
            )
            Text(
                modifier = Modifier
                    .fillMaxHeight()
                    .wrapContentSize(align = Alignment.Center)
                    .padding(8.dp),
                text = stringResource(id = R.string.about_text),
                style = TextStyle(
                    fontSize = 22.sp, textAlign = TextAlign.Center
                )
            )
        }

    }
}

@Preview
@Composable
fun AboutScreenPreview() {
    AboutScreen()
}