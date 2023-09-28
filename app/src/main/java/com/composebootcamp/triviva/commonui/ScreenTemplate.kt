package com.composebootcamp.triviva.commonui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.composebootcamp.triviva.menu.DrawerBody
import com.composebootcamp.triviva.menu.MenuItemId
import com.composebootcamp.triviva.menu.menuList
import com.composebootcamp.triviva.navigation.Screen


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenTemplate(
    title: String = "Template",
    onLeadingClick: () -> Unit = {},
    navIcon: @Composable () -> Unit = {
        IconButton(onClick = { onLeadingClick() }) {
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = "Navigation Drawer Button"
            )
        }
    },
    actionMenu: @Composable RowScope.() -> Unit = {
        Spacer(modifier = Modifier.width(0.dp))
    }, content: @Composable (PaddingValues) -> Unit
) {
    Scaffold(topBar = {
        CenterAlignedTopAppBar(
            colors = TopAppBarDefaults.smallTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                titleContentColor = MaterialTheme.colorScheme.primary,
            ),
            title = {
                Text(title)
            },
            navigationIcon = {
                navIcon()
            },
            actions = { actionMenu() }
        )
    }) {
        content(it)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenWithNavigationDrawer(
    navController: NavController? = null,
    drawerState: DrawerState,
    title: String = "Template",
    onLeadingClick: () -> Unit = {},
    navIcon: ImageVector,
    actionMenu: @Composable RowScope.() -> Unit = {
        Spacer(modifier = Modifier.width(0.dp))
    }, content: @Composable (PaddingValues) -> Unit
) {
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            DrawerBody(items = menuList, onItemClick = {
                when (it.id) {
                    MenuItemId.Rules.id -> navController?.navigate(Screen.RuleScreen.route)
                    MenuItemId.About.id -> navController?.navigate(Screen.AboutScreen.route)
                    else -> {}
                }
            })
        }) {
        ScreenTemplate(
            title = title, onLeadingClick = onLeadingClick,
            navIcon = {
                IconButton(onClick = { onLeadingClick() }) {
                    Icon(
                        imageVector = navIcon,
                        contentDescription = "Navigation Drawer Button"
                    )
                }
            },
            actionMenu = actionMenu
        ) {
            content(it)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ScreenTemplatePreview() {
    ScreenTemplate() {
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun ScreenWithNavigationDrawerPreview() {
    ScreenWithNavigationDrawer(
        drawerState = DrawerState(initialValue = DrawerValue.Open),
        navIcon = Icons.Default.Menu
    ) {}
}

