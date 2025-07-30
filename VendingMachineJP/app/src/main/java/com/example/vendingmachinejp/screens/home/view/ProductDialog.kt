package com.example.vendingmachinejp.screens.home.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog


@Composable
fun ProductDialog(shouldShowDialog: MutableState<Boolean>, headerTxt: String) {
    if (shouldShowDialog.value) {
        Dialog(onDismissRequest = { shouldShowDialog.value = false }) {
            Column(
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color.White)
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = headerTxt,
                    style = MaterialTheme.typography.displayMedium,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center
                )
                Button(
                    modifier = Modifier.padding(top = 8.dp),
                    onClick = { shouldShowDialog.value = false },
                    colors =  ButtonDefaults.buttonColors(
                        containerColor = Color.Red
                    )
                ) {
                    Text("Go Back",
                        color = Color.White,
                        style = MaterialTheme.typography.titleLarge,
                        fontSize = 12.sp)
                }
            }
        }
    }

}