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
import com.composebootcamp.triviva.menu.DrawerBody
import com.composebootcamp.triviva.menu.MenuItem
import com.composebootcamp.triviva.menu.menuList


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenTemplate(
    title: String = "Template",
    onLeadingClick: () -> Unit = {},
    navIcon: ImageVector = Icons.Filled.ArrowBack,
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
                IconButton(onClick = { onLeadingClick() }) {
                    Icon(
                        imageVector = navIcon,
                        contentDescription = "Navigation Drawer Button"
                    )
                }
            },
            actions = { actionMenu() }
        )
    }) {
        content(it)
    }
}

@Composable
fun ScreenContent(content: @Composable (PaddingValues) -> Unit) {
}

@Preview(showBackground = true)
@Composable
fun ScreenContentPreview() {
    ScreenContent() {}
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenWithNavigationDrawerTemplate(
    drawerState: DrawerState,
    title: String = "Template",
    onMenuItemClick: (MenuItem) -> Unit,
    onLeadingClick: () -> Unit = {},
    navIcon: ImageVector,
    actionMenu: @Composable RowScope.() -> Unit = {
        Spacer(modifier = Modifier.width(0.dp))
    }, content: @Composable (PaddingValues) -> Unit
) {
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            DrawerBody(items = menuList) {
                onMenuItemClick(it)
            }
        }) {
        ScreenTemplate(
            title = title, onLeadingClick = onLeadingClick,
            navIcon = navIcon,
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
    ScreenWithNavigationDrawerTemplate(
        drawerState = DrawerState(initialValue = DrawerValue.Open),
        navIcon = Icons.Default.Menu,
        onMenuItemClick = {}
    ) {}
}

