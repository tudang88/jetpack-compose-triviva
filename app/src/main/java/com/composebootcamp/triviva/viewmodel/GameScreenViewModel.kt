package com.composebootcamp.triviva.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlin.random.Random

data class Question(
    val text: String,
    val answers: List<String>
)

// test data of game
private val questions: MutableList<Question> = mutableListOf(
    Question(
        text = "What is Android Jetpack?",
        answers = listOf("all of these", "tools", "documentation", "libraries")
    ),
    Question(
        text = "Base class for Layout?",
        answers = listOf("ViewGroup", "ViewSet", "ViewCollection", "ViewRoot")
    ),
    Question(
        text = "Layout for complex Screens?",
        answers = listOf("ConstraintLayout", "GridLayout", "LinearLayout", "FrameLayout")
    ),
    Question(
        text = "Pushing structured data into a Layout?",
        answers = listOf("Data Binding", "Data Pushing", "Set Text", "OnClick")
    ),
    Question(
        text = "Inflate layout in fragments?",
        answers = listOf("onCreateView", "onViewCreated", "onCreateLayout", "onInflateLayout")
    ),
    Question(
        text = "Build system for Android?",
        answers = listOf("Gradle", "Graddle", "Grodle", "Groyle")
    ),
    Question(
        text = "Android vector format?",
        answers = listOf(
            "VectorDrawable",
            "AndroidVectorDrawable",
            "DrawableVector",
            "AndroidVector"
        )
    ),
    Question(
        text = "Android Navigation Component?",
        answers = listOf("NavController", "NavCentral", "NavMaster", "NavSwitcher")
    ),
    Question(
        text = "Registers app with launcher?",
        answers = listOf("intent-filter", "app-registry", "launcher-registry", "app-launcher")
    ),
    Question(
        text = "Mark a layout for Data Binding?",
        answers = listOf("<layout>", "<binding>", "<data-binding>", "<dbinding>")
    )
)

class GameScreenViewModel : ViewModel() {

    // public questions
    private var _currentQuizIndex by mutableStateOf(0)
    private var _listOfIndex = mutableListOf<Int>()
    var numOfQuiz = 0
    init {
        reset()
    }
    fun getQuiz(): Question {
        return questions[_currentQuizIndex]
    }

    fun reduceQuestions() = run {
        when {
            (numOfQuiz > 1) ->  {
                --numOfQuiz
                // update remain list and next question index
                _listOfIndex.remove(_currentQuizIndex)
                _currentQuizIndex = Random.nextInt(numOfQuiz)
            }
            else -> return
        }

    }

    fun reset() {
        questions.forEach() { item ->
            _listOfIndex.add(questions.indexOf(item))
        }
        numOfQuiz = _listOfIndex.size
    }
    
}