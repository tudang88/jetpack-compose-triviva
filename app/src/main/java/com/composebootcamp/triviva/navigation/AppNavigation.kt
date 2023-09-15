package com.composebootcamp.triviva.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.composebootcamp.triviva.AboutScreen
import com.composebootcamp.triviva.GameOverScreen
import com.composebootcamp.triviva.GameScreen
import com.composebootcamp.triviva.GameWonScreen
import com.composebootcamp.triviva.HomeScreen
import com.composebootcamp.triviva.RuleScreen

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
        // define route for GameWon screen
        composable(route = Screen.GameWonScreen.route) {
            GameWonScreen(navController)
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