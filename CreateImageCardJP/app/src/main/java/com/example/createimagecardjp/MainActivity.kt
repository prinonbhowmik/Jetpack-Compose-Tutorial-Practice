package com.example.createimagecardjp

import android.accounts.AuthenticatorDescription
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.createimagecardjp.ui.theme.CreateImageCardJPTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val painter = painterResource(id = R.drawable.shoccho)
            val description = "Im Shoccho, I'm 10, I'm in class six,"
            val title = "Hi, This shoccho"
           Row(
               verticalAlignment = Alignment.CenterVertically
           ) {
               Box (modifier = Modifier
                   .fillMaxWidth(0.5f)
                   .padding(16.dp)
                   .background(Color.Transparent)
               )
               {
                   ImageCard(
                       painter = painter,
                       contentDescription = description,
                       title = title
                   )
               }
           }


        }
    }
}

@Composable
fun ImageCard(
    painter : Painter,
    contentDescription: String,
    title:String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        ),
    ) {
        Box (
            modifier = Modifier.height(200.dp)
        ){
            Image(
                painter = painter,
                contentDescription = contentDescription,
                contentScale = ContentScale.Crop
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp),
                contentAlignment = Alignment.BottomStart
                ){
                Text(
                    title,
                    style = TextStyle(color = Color.Black,
                        fontSize = 16.sp)
                )
            }

        }
    }
}