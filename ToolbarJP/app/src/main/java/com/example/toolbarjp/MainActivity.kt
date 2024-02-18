package com.example.toolbarjp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.toolbarjp.ui.theme.ToolbarJPTheme
import kotlin.system.exitProcess

class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ToolbarJPTheme {
                var presses by remember { mutableIntStateOf(0) }
                Scaffold(
                    topBar = {
                        TopAppBar(
                            colors = topAppBarColors(
                                containerColor = Color.Blue,
                                titleContentColor = Color.White,
                            ),
                            title = {
                                Text("Top app bar")
                            },
                            navigationIcon = {
                                IconButton(
                                    onClick = { exitProcess(0) }) {
                                    Icon(
                                        Icons.Filled.KeyboardArrowLeft,
                                        modifier = Modifier.size(50.dp),
                                        contentDescription = "")
                                }
                            },
                            actions = {
                                IconButton(
                                    onClick = { }) {
                                    Icon(Icons.Filled.Notifications, contentDescription = "")
                                }
                            }

                        )
                    },
                    bottomBar = {
                        BottomAppBar(
                            containerColor = Color.Blue,
                            contentColor = Color.White,
                            modifier = Modifier.height(65.dp)
                        ) {
                            Text(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                textAlign = TextAlign.Center,
                                text = "Bottom app bar",
                            )
                        }
                    },
                    floatingActionButton = {
                        FloatingActionButton(
                            onClick = { presses++ },
                            containerColor = Color.Blue
                            ) {
                            Icon(Icons.Default.Add, contentDescription = "Add")
                        }
                    }
                ) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .padding(innerPadding),
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                    ) {
                        Text(
                            modifier = Modifier.padding(8.dp),
                            text = " You have pressed the floating action button $presses times.",
                        )
                    }
                }
            }
        }
    }
}
