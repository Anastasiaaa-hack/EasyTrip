package com.example.test1

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity;
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.Alignment
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.TextField
import androidx.compose.ui.res.colorResource
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.navigation.compose.rememberNavController
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.ui.res.painterResource
import androidx.compose.material.Surface
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.ui.PlayerView
import androidx.compose.ui.platform.LocalContext


@Composable
fun VideoPlayer(videoUrl: String) {
    val context = LocalContext.current
    val player = remember { SimpleExoPlayer.Builder(context).build() }
    val mediaItem = remember { MediaItem.fromUri(videoUrl) }

    player.setMediaItem(mediaItem)
    player.prepare()
    player.playWhenReady = true

    Surface(modifier = Modifier.fillMaxSize()) {
        PlayerView(context).apply {
            setPlayer(player)
        }
    }
}

@Composable
fun MyScreen3(context: Context) {
    val textState = remember { mutableStateOf("") }
    val navController = rememberNavController()
    val kaushanFontFamily = FontFamily(
        Font(R.font.kaushan),
    )

    val interFontFamily = FontFamily(
        Font(R.font.inter),
    )
    val grey = colorResource(id = R.color.grey)
    val white = colorResource(id = R.color.white)
    val black = colorResource(id = R.color.black)
    val green = colorResource(id = R.color.green)

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
        contentAlignment = Alignment.TopEnd // Выравнивание по верхнему правому углу
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo",
            modifier = Modifier.padding(5.dp),
        )
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "EasyTrip",
            style = TextStyle(
                fontFamily = kaushanFontFamily,
                color = Color.Black,
                fontSize = 32.sp,
                fontWeight = FontWeight.Normal
            ),
            modifier = Modifier.padding(top = 105.dp)
        )
        Image(
            painter = painterResource(id = R.drawable.loading),
            contentDescription = "Loading",
            modifier = Modifier.padding(10.dp)
        )
        Button(onClick = {
            val intent = Intent(context, MainActivity::class.java)
            context.startActivity(intent)
        },
            shape = RoundedCornerShape(20.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = grey),
            modifier = Modifier
                .size(width = 400.dp, height = 75.dp) // Устанавливаем размер кнопки
                .fillMaxWidth()
                .padding(top = 20.dp, start = 12.dp, end = 12.dp)
        ){
            Text(
                text = "Назад",
                style = TextStyle(
                    fontFamily = interFontFamily,
                    color = white,
                    fontSize = 24.sp
                )
            )
        }
    }
}

class MainActivity3 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyScreen3(context = this)
        }
    }
}