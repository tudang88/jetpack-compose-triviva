package com.composebootcamp.triviva.navigation

// refer original document
//https://developer.android.com/jetpack/compose/navigation
open class Screen(val route: String) {
    object HomeScreen : Screen(route = "${ScreenIds.HomeScreen}")
    object GameScreen : Screen(route = "${ScreenIds.GameScreen}")
    object GameOverScreen : Screen(route = "${ScreenIds.GameOverScreen}")

    // this is a mandatory navigation argument -> /{argName1}/{argName2}
    // in case the nav args is optional -> please use -> ?argName={argName}
    object GameWonScreen : Screen(route = "${ScreenIds.GameWonScreen}")
    object AboutScreen : Screen(route = "${ScreenIds.AboutScreen}")
    object RuleScreen : Screen(route = "${ScreenIds.RuleScreen}")

    fun buildRouteWithIntArgs(vararg args: Int): String {
        return buildString {
            append(route)
            args.forEach {
                append("/$it")
            }
        }
    }
}
const val GameWonNumOfCorrect = "numOfCorrect"
const val GameWonTotalQuiz = "totalQuiz"

enum class ScreenIds(val screenId: String) {
    HomeScreen("home"),
    GameScreen("game"),
    GameOverScreen("game_over"),
    GameWonScreen("game_won"),
    AboutScreen("about"),
    RuleScreen("rule");

    override fun toString() = screenId
}