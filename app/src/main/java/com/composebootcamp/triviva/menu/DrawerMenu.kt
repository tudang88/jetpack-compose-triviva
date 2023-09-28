package com.composebootcamp.triviva.menu

import com.composebootcamp.triviva.R

enum class MenuItemId(val id: String) {
    Rules("rules"),
    About("about")
}

data class MenuItem(
    val id: String,
    val title: String,
    val contentDescription: String,
    val iconResource: Int
)

// declare all supported menu here
val menuList = listOf(
    MenuItem(
        id = MenuItemId.Rules.id, title = "Rules", contentDescription = "go to Rules Page",
        iconResource = R.drawable.rules
    ),
    MenuItem(
        id = MenuItemId.About.id, title = "About", contentDescription = "go to About Page",
        iconResource = R.drawable.android
    ),
)

