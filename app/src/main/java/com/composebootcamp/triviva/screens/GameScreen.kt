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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.composebootcamp.triviva.navigation.Screen
import com.composebootcamp.triviva.ui.theme.ButtonPlayBgColor
import com.composebootcamp.triviva.ui.theme.ButtonPlayCaptionColor
import com.composebootcamp.triviva.viewmodel.GameScreenViewModel

@Composable
fun GameScreen(
    navController: NavController?,
    viewModel: GameScreenViewModel = androidx.lifecycle.viewmodel.compose.viewModel()

) {
    val question = viewModel.getQuiz()
    val answers = question.answers.shuffled()
    var selectedOption by remember {
        mutableStateOf( answers[0])
    }
    // observer transition state to transition
    if (viewModel.transitionToGameOver) {
        viewModel.reset()
        navController?.navigate(Screen.GameOverScreen.route)
    }

    ScreenTemplate(onBack = {
        navController?.popBackStack()
    }, title = stringResource(id = R.string.android_trivia) + "(${viewModel.numOfCorrect}/${viewModel.getTotalQuiz()})"
    ) {
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
            Text(
                text = question.text,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold
            )
            // Question area
            Column() {
                answers.forEach { text ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .selectable(
                                selected = (text == selectedOption),
                                onClick = {
                                    selectedOption =
                                        text // use setter of MutableState selectedOption to update value
                                }
                            )
                            .padding(horizontal = 16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = (text == selectedOption),
                            onClick = {
                                selectedOption = text // use setter of MutableState selectedOption to update value
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
                    if (viewModel.numOfQuiz == 1) {
                        viewModel.reset()
                        navController?.navigate(Screen.GameWonScreen.route)
                    } else {
                        viewModel.submitAnswer(selectedOption, question)
                    }
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