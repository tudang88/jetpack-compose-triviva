package com.composebootcamp.triviva.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
fun RuleScreenContent(paddingValues: PaddingValues) {

    Column(
        modifier = Modifier
            .padding(paddingValues)
            .verticalScroll(rememberScrollState())
    ) {
        Image(
            modifier = Modifier.padding(32.dp),
            painter = painterResource(id = R.drawable.trivia_rules),
            contentDescription = "rule image"
        )
        Text(
            modifier = Modifier.padding(8.dp),
            text = stringResource(id = R.string.rules_text),
            style = TextStyle(fontSize = 22.sp, textAlign = TextAlign.Center)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun RuleScreenContentPreview() {
    RuleScreenContent(PaddingValues(24.dp))
}