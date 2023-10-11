package com.composebootcamp.triviva.navigation

import android.util.Log
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.composebootcamp.triviva.screens.AboutScreenContent
import com.composebootcamp.triviva.screens.GameOverScreenContent
import com.composebootcamp.triviva.screens.GameScreenContent
import com.composebootcamp.triviva.screens.GameWonScreenContent
import com.composebootcamp.triviva.screens.HomeScreenContent
import com.composebootcamp.triviva.screens.RuleScreenContent
import com.composebootcamp.triviva.viewmodel.GameScreenViewModel

// refer original document
//https://developer.android.com/jetpack/compose/navigation
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AppNavigation(
    navController: NavHostController,
    paddingValues: PaddingValues,
    onUpdateScore: (score: Int) -> Unit,
    onNavigate: (screenId: String) -> Unit
) {
    // we need to specify starting point here
    NavHost(navController = navController, startDestination = Screen.HomeScreen.route) {
        // define route for Home screen
        composable(route = Screen.HomeScreen.route) {
            HomeScreenContent(navController, onNavigate)
        }
        // define route for Game screen
        composable(route = Screen.GameScreen.route,
            enterTransition = {
                slideInHorizontally(
                    initialOffsetX = { it }, // it == fullWidth
                    animationSpec = tween(
                        durationMillis = 200,
                        easing = LinearEasing
                    )
                )
            },
            exitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { it },
                    animationSpec = tween(
                        durationMillis = 200,
                        easing = LinearEasing
                    )
                )
            }
        ) {
            GameScreenContent(
                navController = navController,
                viewModel = GameScreenViewModel(),
                onNavigate = onNavigate
            ) { score ->
                Log.d("AppNavigation", "Score: $score")
                onUpdateScore(score)
            }
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
            ),
            enterTransition = {
                slideInHorizontally(
                    initialOffsetX = { it }, // it == fullWidth
                    animationSpec = tween(
                        durationMillis = 200,
                        easing = LinearEasing
                    )
                )
            },
            exitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { it },
                    animationSpec = tween(
                        durationMillis = 200,
                        easing = LinearEasing
                    )
                )
            }
        ) {
            GameWonScreenContent(navController, onNavigate)
        }
        // define route for GameOver screen
        composable(route = Screen.GameOverScreen.route,
            enterTransition = {
                slideInHorizontally(
                    initialOffsetX = { it }, // it == fullWidth
                    animationSpec = tween(
                        durationMillis = 200,
                        easing = LinearEasing
                    )
                )
            },
            exitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { it },
                    animationSpec = tween(
                        durationMillis = 200,
                        easing = LinearEasing
                    )
                )
            }) {
            GameOverScreenContent(navController, onNavigate)
        }
        // define route for About screen
        composable(route = Screen.AboutScreen.route,
            enterTransition = {
                slideInHorizontally(
                    initialOffsetX = { it }, // it == fullWidth
                    animationSpec = tween(
                        durationMillis = 200,
                        easing = LinearEasing
                    )
                )
            },
            exitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { it },
                    animationSpec = tween(
                        durationMillis = 200,
                        easing = LinearEasing
                    )
                )
            }) {
            AboutScreenContent(paddingValues)
        }
        // define route for Rule screen
        composable(route = Screen.RuleScreen.route,
            enterTransition = {
                slideInHorizontally(
                    initialOffsetX = { it }, // it == fullWidth
                    animationSpec = tween(
                        durationMillis = 200,
                        easing = LinearEasing
                    )
                )
            },
            exitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { it },
                    animationSpec = tween(
                        durationMillis = 200,
                        easing = LinearEasing
                    )
                )
            }) {
            RuleScreenContent(paddingValues)
        }
    }
}