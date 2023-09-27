package com.composebootcamp.triviva.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

data class Question(
    val text: String,
    val answers: List<String>,
    val key: String
)

// test data of game
private val questions = listOf(
    Question(
        text = "What is Android Jetpack?",
        answers = listOf("all of these", "tools", "documentation", "libraries"),
        key = "all of these"
    ),
    Question(
        text = "Base class for Layout?",
        answers = listOf("ViewGroup", "ViewSet", "ViewCollection", "ViewRoot"),
        key = "ViewGroup"
    ),
    Question(
        text = "Layout for complex Screens?",
        answers = listOf("ConstraintLayout", "GridLayout", "LinearLayout", "FrameLayout"),
        key = "ConstraintLayout"
    ),
    Question(
        text = "Pushing structured data into a Layout?",
        answers = listOf("Data Binding", "Data Pushing", "Set Text", "OnClick"),
        key = "Data Binding"
    ),
    Question(
        text = "Inflate layout in fragments?",
        answers = listOf("onCreateView", "onViewCreated", "onCreateLayout", "onInflateLayout"),
        key = "onCreateView"
    ),
    Question(
        text = "Build system for Android?",
        answers = listOf("Gradle", "Graddle", "Grodle", "Groyle"),
        key = "Gradle"
    ),
    Question(
        text = "Android vector format?",
        answers = listOf(
            "VectorDrawable",
            "AndroidVectorDrawable",
            "DrawableVector",
            "AndroidVector"
        ),
        key = "VectorDrawable"
    ),
    Question(
        text = "Android Navigation Component?",
        answers = listOf("NavController", "NavCentral", "NavMaster", "NavSwitcher"),
        key = "NavController"
    ),
    Question(
        text = "Registers app with launcher?",
        answers = listOf("intent-filter", "app-registry", "launcher-registry", "app-launcher"),
        key = "intent-filter"
    ),
    Question(
        text = "Mark a layout for Data Binding?",
        answers = listOf("<layout>", "<binding>", "<data-binding>", "<dbinding>"),
        key = "<layout>"
    )
)
enum class FinalDestination {
    GameWon,
    GameOver,
    None
}
class GameScreenViewModel : ViewModel() {

    private var _currentQuizIndex by mutableStateOf(0)
    private var _listOfIndex = mutableListOf<Int>()
    private var transitionGoal by mutableStateOf(FinalDestination.None)
    private var numOfQuiz = 0
    private var numOfCorrect = 0;
    init {
        reset()
    }
    fun getQuiz(): Question {
        return questions[_currentQuizIndex]
    }

    fun submitAnswer(answer: String, question: Question) {
        if (answer == question.key) {
            correctedAnswer()
        } else {
            gameOver()
        }
    }
    private fun correctedAnswer() {
        when {
            (numOfQuiz > 1) -> {
                --numOfQuiz
                ++numOfCorrect
                // update remain list and next question index
                _listOfIndex.remove(_currentQuizIndex)
                _listOfIndex.apply { shuffle() }
                _currentQuizIndex = _listOfIndex[0]
            }
            else -> gameWon()
        }
    }
    fun getTotalQuiz() = questions.size
    fun reset() {
        questions.forEach() { item ->
            _listOfIndex.add(questions.indexOf(item))
        }
        numOfQuiz = getTotalQuiz()
        numOfCorrect = 0
        transitionGoal = FinalDestination.None
    }

    fun getDestination() = transitionGoal
    fun getNumOfCorrect() = numOfCorrect
    private fun gameWon() {
        transitionGoal = FinalDestination.GameWon
    }
    private fun gameOver() {
        transitionGoal = FinalDestination.GameOver
    }
    
}