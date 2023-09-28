package com.composebootcamp.triviva.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
fun RuleScreen(navController: NavController? = null) {
    ScreenTemplate(
        onBack = { navController?.popBackStack() },
        title = stringResource(id = R.string.rules)
    ) {
        Column(modifier = Modifier.padding(it)) {
            Image(
                modifier = Modifier.padding(16.dp),
                painter = painterResource(id = R.drawable.trivia_rules),
                contentDescription = "rule image"
            )
            Text(
                modifier = Modifier.padding(8.dp),
                text = stringResource(id = R.string.rules_text),
                style = TextStyle(fontSize = 24.sp, textAlign = TextAlign.Center)
            )
        }
    }
}

@Preview
@Composable
fun RuleScreenPreview() {
    RuleScreen()
}