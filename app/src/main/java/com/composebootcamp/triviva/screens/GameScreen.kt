package com.composebootcamp.triviva.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.composebootcamp.triviva.commonui.ScreenTemplate
import com.composebootcamp.triviva.ui.theme.ButtonPlayBgColor
import com.composebootcamp.triviva.ui.theme.ButtonPlayCaptionColor
import com.composebootcamp.triviva.viewmodel.GameScreenViewModel

@Composable
fun GameScreen(
    navController: NavController?,
    viewModel: GameScreenViewModel = androidx.lifecycle.viewmodel.compose.viewModel()

) {
    val (selectedOption, onOptionSelected) = remember {
        mutableStateOf(viewModel.getQuiz().answers[0])
    }
    ScreenTemplate(onBack = {
        navController?.popBackStack()
    }, title = stringResource(id = R.string.android_trivia)) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Image(
                painter = painterResource(id = R.drawable.android_category_simple),
                contentDescription = ""
            )
            Text(text = viewModel.getQuiz().text, style = MaterialTheme.typography.bodyLarge)
            // Question area
            Column() {
                viewModel.getQuiz().answers.forEach { text ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .selectable(
                                selected = (text == selectedOption),
                                onClick = {
                                    onOptionSelected(text)
                                }
                            )
                            .padding(horizontal = 16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = (text == selectedOption),
                            onClick = {
                                onOptionSelected(text)
                            })
                        Text(
                            text = text,
                            style = MaterialTheme.typography.bodyMedium.merge(),
                            modifier = Modifier.padding(start = 16.dp)
                        )
                    }

                }
            }
            // Submit button
            Button(shape = RectangleShape,
                colors = ButtonDefaults.buttonColors(
                    containerColor = ButtonPlayBgColor
                ),
                onClick = {
                    // go to next question
                    viewModel.increaseIndex()
                }) {
                Text(
                    text = stringResource(id = R.string.submit_button),
                    style = TextStyle(color = ButtonPlayCaptionColor, fontWeight = FontWeight.Bold)
                )
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun GameScreenPreview() {
    GameScreen(navController = null)
}