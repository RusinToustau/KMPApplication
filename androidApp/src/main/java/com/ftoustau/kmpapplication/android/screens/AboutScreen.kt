package com.ftoustau.kmpapplication.android.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.ftoustau.kmpapplication.Platform

@Composable
fun AboutScreen(
    onUpButtonClick: () -> Unit
) {
    Column {
        Toolbar(onUpButtonClick)
        ContentView()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Toolbar(onUpButtonClick: () -> Unit) {
    TopAppBar(
        title = { Text(text = "About Device") },
        navigationIcon = {
            IconButton(onClick = onUpButtonClick) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back Button"
                )
            }
        }
    )
}

@Composable
private fun ContentView() {
    val items = makeItems()

    LazyColumn (Modifier.fillMaxSize()) {
        items(items.size) { index ->
            val row = items[index]
            RowView(row.first, row.second)
        }
    }
}

private fun makeItems(): List<Pair<String, String>> {

    val platform = Platform()

    platform.logSystemInfo()

    return listOf(
        "osName" to platform.osName,
        "osVersion" to platform.osVersion,
        "deviceModel" to platform.deviceModel,
        "density" to platform.density.toString()
    )
}

@Composable
private fun RowView(
    title: String,
    subtitle: String
) {
    Column (Modifier.padding(8.dp)) {
        Text(
            text = title,
            style = MaterialTheme.typography.bodySmall,
            color = Color.Gray
        )
        Text(
            text = subtitle,
            style = MaterialTheme.typography.bodyLarge
        )
        Divider()
    }
}
