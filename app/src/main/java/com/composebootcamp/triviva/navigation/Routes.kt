package com.composebootcamp.triviva.navigation

open class Screen (val route: String) {
    object HomeScreen : Screen(route = "${ScreenIds.HomeScreen}")
    object GameScreen : Screen(route = "${ScreenIds.GameScreen}")
    object GameOverScreen : Screen(route = "${ScreenIds.GameOverScreen}")
    object GameWonScreen : Screen(route = "${ScreenIds.GameWonScreen}")
    object AboutScreen: Screen(route = "${ScreenIds.AboutScreen}")
    object RuleScreen: Screen(route = "${ScreenIds.RuleScreen}")
}

enum class ScreenIds(val screenId: String) {
    HomeScreen("home"),
    GameScreen("game"),
    GameOverScreen("game_over"),
    GameWonScreen("game_won"),
    AboutScreen("about"),
    RuleScreen("rule");

    override fun toString() = screenId
}