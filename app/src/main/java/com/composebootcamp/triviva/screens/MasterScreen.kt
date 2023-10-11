package com.composebootcamp.triviva.screens

import android.annotation.SuppressLint
import android.content.Intent
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.composebootcamp.triviva.R
import com.composebootcamp.triviva.menu.DrawerBody
import com.composebootcamp.triviva.menu.menuList
import com.composebootcamp.triviva.navigation.AppNavigation
import com.composebootcamp.triviva.navigation.Screen
import kotlinx.coroutines.launch

enum class ActionMenuType {
    Empty,
    HomeMenu,
    ShareMenu
}

data class AppBarState(
    val navIcon: ImageVector,
    val title: String,
    val hasActionMenu: ActionMenuType = ActionMenuType.HomeMenu
)

@Composable
fun CreateActionMenu(
    menuType: ActionMenuType,
    onActionMenuItemClick: (item: String) -> Unit
) {
    var overFlowMenuExpanded by remember {
        mutableStateOf(false)
    }
    when (menuType) {
        ActionMenuType.Empty -> {}
        ActionMenuType.HomeMenu -> {
            IconButton(onClick = { overFlowMenuExpanded = !overFlowMenuExpanded }) {
                Icon(imageVector = Icons.Filled.MoreVert, contentDescription = "Overflow menu")
            }
            DropdownMenu(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        onActionMenuItemClick(Screen.AboutScreen.route)
                        overFlowMenuExpanded = false
                    },
                expanded = overFlowMenuExpanded,
                onDismissRequest = { overFlowMenuExpanded = false }) {
                Text(
                    modifier = Modifier.align(CenterHorizontally),
                    text = stringResource(id = R.string.about),
                    style = TextStyle(fontSize = 22.sp),
                    textAlign = TextAlign.Center
                )

            }
        }
        ActionMenuType.ShareMenu -> {
            IconButton(onClick = {
                // share result through other app
                onActionMenuItemClick("Share")
            }) {
                Icon(painter = painterResource(id = R.drawable.share), contentDescription = "button share")
            }
        }
        else -> {
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MasterScreen() {
    val context = LocalContext.current
    var appBarState by remember {
        mutableStateOf(AppBarState(Icons.Default.Menu, context.getString(R.string.android_trivia)))
    }
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    // the drawerState operation request a coroutine scope
    val scope = rememberCoroutineScope()
    val navController = rememberNavController()
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            DrawerBody(items = menuList) {
                // close drawer menu
                scope.launch {
                    drawerState.apply {
                        if (!isClosed) close()
                    }
                }
                // navigate to destination
                navController.navigate(it.route)
                appBarState = appBarState.copy(navIcon = Icons.Default.ArrowBack)
            }
        }) {
        Scaffold(topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text(appBarState.title)
                },
                navigationIcon = {
                    IconButton(onClick = {// on DrawerMenu click
                        when (appBarState.navIcon) {
                            Icons.Default.Menu -> scope.launch {
                                drawerState.apply {
                                    if (isClosed) open() else close()
                                }
                            }

                            Icons.Default.ArrowBack -> {
                                Log.d("MasterScreen", "onBack button")
                                if (navController.previousBackStackEntry?.destination?.route == Screen.HomeScreen.route) {
                                    appBarState = appBarState.copy(
                                        navIcon = Icons.Default.Menu,
                                        title = context.getString(R.string.android_trivia),
                                        hasActionMenu = ActionMenuType.HomeMenu
                                    )
                                } else {
                                    appBarState = appBarState.copy(
                                        hasActionMenu = ActionMenuType.Empty
                                    )
                                }
                                navController.popBackStack()
                            }

                            else -> Log.d("MasterScreen", "Unknown Navigation Icon State click")
                        }

                    }) {
                        Icon(
                            imageVector = appBarState.navIcon,
                            contentDescription = "Navigation Drawer Button"
                        )
                    }
                },
                actions = {
                    CreateActionMenu(
                        menuType = appBarState.hasActionMenu,
                        onActionMenuItemClick = { item ->
                            when (item) {
                                Screen.AboutScreen.route -> {
                                    appBarState = appBarState.copy(
                                        navIcon = Icons.Default.ArrowBack,
                                        title = context.getString(R.string.about)
                                    )
                                    navController.navigate(item)
                                }
                                "Share" -> {
                                    val sendIntent: Intent = Intent().apply {
                                        action = Intent.ACTION_SEND
                                        putExtra(Intent.EXTRA_TEXT, context.getString(R.string.share_success_text))
                                        type = "text/plain"
                                    }
                                    val shareIntent = Intent.createChooser(sendIntent, null)
                                    context.startActivity(shareIntent)
                                }
                            }
                        }
                    )
                })
        }) {
            AppNavigation(navController, it, onUpdateScore = { score ->
                appBarState = appBarState.copy(
                    title = context.getString(
                        R.string.title_android_trivia_question,
                        score,
                        10
                    )
                )
            }) { route ->
                Log.d("MasterScreen", "Navigate to : $route")
                when (route) {
                    Screen.GameScreen.route ->
                        appBarState = appBarState.copy(
                            navIcon = Icons.Default.ArrowBack,
                            title = context.getString(R.string.title_android_trivia_question, 0, 10),
                            hasActionMenu = ActionMenuType.Empty
                        )

                    Screen.HomeScreen.route ->
                        appBarState = appBarState.copy(
                            navIcon = Icons.Default.Menu,
                            title = context.getString(R.string.android_trivia),
                            hasActionMenu = ActionMenuType.HomeMenu
                        )
                    Screen.GameWonScreen.route -> {
                        appBarState = appBarState.copy(hasActionMenu = ActionMenuType.ShareMenu)
                    }
                    else -> {
                        Log.d("MasterScreen", "Navigate to unsupport : $route")
                        appBarState = appBarState.copy(hasActionMenu = ActionMenuType.Empty)
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun MasterScreenPreview() {
    MasterScreen()
}