package com.composebootcamp.triviva.screens

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DrawerValue
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.composebootcamp.triviva.R
import com.composebootcamp.triviva.menu.DrawerBody
import com.composebootcamp.triviva.menu.menuList
import com.composebootcamp.triviva.navigation.AppNavigation
import kotlinx.coroutines.launch

data class AppBarState(val navIcon: ImageVector, val title: String)

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MasterScreen() {
    val context = LocalContext.current
    val appBarState by remember {
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

                            Icons.Default.ArrowBack -> navController.popBackStack()
                            else -> Log.d("MasterScreen", "Unknown Navigation Icon State click")
                        }

                    }) {
                        Icon(
                            imageVector = appBarState.navIcon,
                            contentDescription = "Navigation Drawer Button"
                        )
                    }
                },
                actions = { })
        }) {
            AppNavigation(navController, it) {
                Log.d("MasterScreen", "Navigate to : $it")
            }
        }
    }
}

@Preview
@Composable
fun MasterScreenPreview() {
    MasterScreen()
}