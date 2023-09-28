package com.composebootcamp.triviva.menu

import com.composebootcamp.triviva.R
import com.composebootcamp.triviva.navigation.Screen

enum class MenuItemId(val id: String) {
    Rules("rules"),
    About("about")
}

data class MenuItem(
    val id: String,
    val title: String,
    val contentDescription: String,
    val iconResource: Int,
    val route:String
)

// declare all supported menu here
val menuList = listOf(
    MenuItem(
        id = MenuItemId.Rules.id, title = "Rules", contentDescription = "go to Rules Page",
        iconResource = R.drawable.rules,
        route = Screen.RuleScreen.route
    ),
    MenuItem(
        id = MenuItemId.About.id, title = "About", contentDescription = "go to About Page",
        iconResource = R.drawable.android,
        route = Screen.AboutScreen.route
    ),
)

