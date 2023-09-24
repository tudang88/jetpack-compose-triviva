package com.composebootcamp.triviva.commonui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenTemplate(
    title: String = "Template",
    onBack: () -> Unit = {},
    navIcon: @Composable () -> Unit = {
        IconButton(onClick = { onBack() }) {
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

@Preview(showBackground = true)
@Composable
fun ScreenTemplatePreview() {
    ScreenTemplate() {
    }
}

