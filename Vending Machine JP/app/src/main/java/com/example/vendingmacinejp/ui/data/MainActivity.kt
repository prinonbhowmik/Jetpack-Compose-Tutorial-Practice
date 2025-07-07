package com.example.vendingmacinejp.ui.data

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.vendingmacinejp.ui.theme.VendingMacineJPTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Greeting("Jetpack")
        }
    }
}

@Composable
fun Greeting(name: String, ) {
    val ctx = LocalContext.current
    Surface (
        color = Color.DarkGray,
        modifier = Modifier.fillMaxSize()
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Column {
                TextData(ctx,"Jetpack","Clicked 1st", Color.Red)
                TextData(ctx,"Prinon","Clicked 2nd",Color.Blue)
            }
            Column {
                TextData(ctx,"Android","Clicked 1st", Color.Red)
                TextData(ctx,"Bhowmik","Clicked 2nd",Color.Blue)
            }
        }
    }
}

@Composable
fun TextData(ctx: Context, name:String,msg:String,color: Color){
    Text(
        text = "Hello $name!",
        style = TextStyle(color = Color.White),
        modifier = Modifier
            .padding(8.dp)
            .wrapContentSize()
            .background(color = color, RoundedCornerShape(4.dp))
            .padding(8.dp)
            .clickable(onClick = {
                Toast.makeText(ctx,msg, Toast.LENGTH_SHORT).show()
            })

    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    VendingMacineJPTheme {
        Greeting("Android")
    }
}