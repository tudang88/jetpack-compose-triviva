package com.composebootcamp.triviva.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.composebootcamp.triviva.screens.AboutScreen
import com.composebootcamp.triviva.screens.GameOverScreen
import com.composebootcamp.triviva.screens.GameScreen
import com.composebootcamp.triviva.screens.GameWonScreen
import com.composebootcamp.triviva.screens.HomeScreen
import com.composebootcamp.triviva.screens.RuleScreen

// refer original document
//https://developer.android.com/jetpack/compose/navigation
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    // we need to specify starting point here
    NavHost(navController = navController, startDestination = Screen.HomeScreen.route) {
        // define route for Home screen
        composable(route = Screen.HomeScreen.route) {
            HomeScreen(navController)
        }
        // define route for Game screen
        composable(route = Screen.GameScreen.route) {
            GameScreen(navController)
        }
        // define route for GameWon screen with argument
        composable(
            route = Screen.GameWonScreen.route + "/{$GameWonNumOfCorrect}/{$GameWonTotalQuiz}",
            arguments = listOf(navArgument(GameWonNumOfCorrect) {
                type = NavType.IntType
                defaultValue = 0
            },
                navArgument(GameWonTotalQuiz) {
                    type = NavType.IntType
                    defaultValue = 0
                }
            )
        ) { entry ->
            val correctNum = entry.arguments?.getInt(GameWonNumOfCorrect, 0) ?: 0
            val totalNum = entry.arguments?.getInt(GameWonTotalQuiz, 0) ?: 0
            GameWonScreen(navController, numOfCorrect = correctNum, totalQuiz = totalNum)
        }
        // define route for GameOver screen
        composable(route = Screen.GameOverScreen.route) {
            GameOverScreen(navController)
        }
        // define route for About screen
        composable(route = Screen.AboutScreen.route) {
            AboutScreen(navController)
        }
        // define route for Rule screen
        composable(route = Screen.RuleScreen.route) {
            RuleScreen(navController)
        }
    }
}