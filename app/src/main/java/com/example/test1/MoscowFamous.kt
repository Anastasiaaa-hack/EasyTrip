package com.example.test1

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
import android.content.Context
import android.content.Intent
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.rememberScrollState

@Composable
fun MyScreen4(context: Context) {
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
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
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
            modifier = Modifier.padding(top = 12.dp)
        )
        Text(
            text = "Moscow",
            style = TextStyle(
                fontFamily = kaushanFontFamily,
                color = Color.Black,
                fontSize = 30.sp,
                fontWeight = FontWeight.Normal
            ),
            modifier = Modifier.padding(top = 10.dp)
        )

        Text(
            text = "Московский кремль",
            style = TextStyle(
                fontFamily = interFontFamily,
                color = Color.Black,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Left
            ),
            modifier = Modifier.padding(top = 10.dp, start = 12.dp, end = 12.dp)
        )
        Image(
            painter = painterResource(id = R.drawable.kremlin),
            contentDescription = "Kremlin",
            modifier = Modifier.padding(10.dp)
        )
        Text(
            text = "Самое изветсное место столицы, все туристы стремятся увидеть именно его! " +
                    "Обязательно купите билет внутрь, чтобы увидеть императорскую корону, скипетр, " +
                    "державу и горы драгоценных камней.",
            style = TextStyle(
                fontFamily = interFontFamily,
                color = Color.Black,
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal
            ),
            modifier = Modifier
                .padding(top = 10.dp, start = 12.dp, end = 12.dp)
                .fillMaxWidth()
        )

        Text(
            text = "Красная площадь",
            style = TextStyle(
                fontFamily = interFontFamily,
                color = Color.Black,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Left
            ),
            modifier = Modifier.padding(top = 10.dp, start = 12.dp, end = 12.dp)
        )
        Image(
            painter = painterResource(id = R.drawable.red_square),
            contentDescription = "Red square",
            modifier = Modifier
                .padding(12.dp)
        )
        Text(
            text = "Главная площадь страны получила свое название не из-за своего цвета, красная - значит красивая!" +
                    " Если приедете зимой, то обязательно сходите на главный каток страны и насладитесь катанием " +
                    "под музыку из совестких фильмов.",
            style = TextStyle(
                fontFamily = interFontFamily,
                color = Color.Black,
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal
            ),
            modifier = Modifier
                .padding(top = 10.dp, start = 12.dp, end = 12.dp)
                .fillMaxWidth()
        )

        Text(
            text = "Большой театр",
            style = TextStyle(
                fontFamily = interFontFamily,
                color = Color.Black,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Left
            ),
            modifier = Modifier.padding(top = 10.dp, start = 12.dp, end = 12.dp)
        )
        Image(
            painter = painterResource(id = R.drawable.bolshoy_theater),
            contentDescription = "Theater",
            modifier = Modifier.padding(10.dp)
        )
        Text(
            text = "Один из самых красивых театров мира! Если вас интересует больше интерьер театра," +
                    " а не опера и балет, то обзательно возьмите экскурсию.",
            style = TextStyle(
                fontFamily = interFontFamily,
                color = Color.Black,
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal
            ),
            modifier = Modifier
                .padding(top = 10.dp, start = 12.dp, end = 12.dp)
                .fillMaxWidth()
        )

        Text(
            text = "Третьяковская галерея",
            style = TextStyle(
                fontFamily = interFontFamily,
                color = Color.Black,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Left
            ),
            modifier = Modifier.padding(top = 10.dp, start = 12.dp, end = 12.dp)
        )
        Image(
            painter = painterResource(id = R.drawable.gallery),
            contentDescription = "Gallery",
            modifier = Modifier.padding(10.dp)
        )
        Text(
            text = "Запаситесь терпением, чтобы потратить некоторое время на очередь при входе, но обязательно " +
                    "посетите главный музей классического русского искусства. Советуем взять аудиогид и " +
                    "насладитесь шедеврами в 62-х залах.",
            style = TextStyle(
                fontFamily = interFontFamily,
                color = Color.Black,
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal
            ),
            modifier = Modifier
                .padding(top = 10.dp, start = 12.dp, end = 12.dp)
                .fillMaxWidth()
        )

        Text(
            text = "ВДНХ",
            style = TextStyle(
                fontFamily = interFontFamily,
                color = Color.Black,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Left
            ),
            modifier = Modifier.padding(top = 10.dp, start = 12.dp, end = 12.dp)
        )
        Image(
            painter = painterResource(id = R.drawable.vdnh),
            contentDescription = "VDNH",
            modifier = Modifier.padding(10.dp)
        )
        Text(
            text = "Будьте готовы провести здесь целый день и вдоволь насладиться прогулкой! Обязательно зайдите " +
                    "в интересующие Вас павильоны, загляните в океанариум и пройдитесь по многочисленным аллеям.",
            style = TextStyle(
                fontFamily = interFontFamily,
                color = Color.Black,
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal
            ),
            modifier = Modifier
                .padding(top = 10.dp, start = 12.dp, end = 12.dp)
                .fillMaxWidth()
        )

        Text(
            text = "Москва-Сити",
            style = TextStyle(
                fontFamily = interFontFamily,
                color = Color.Black,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Left
            ),
            modifier = Modifier.padding(top = 10.dp, start = 12.dp, end = 12.dp)
        )
        Image(
            painter = painterResource(id = R.drawable.city),
            contentDescription = "Moscow-city",
            modifier = Modifier.padding(10.dp)
        )
        Text(
            text = "Давно мечтали побывать в Нью-Йорке, но никак не получалось? Загляните в данный район! " +
                    "Сфотографируйтесь с небоскребами и почувствуйте себя настоящим бизнесменом, бегущим" +
                    " на деловую встречу в одну из башен.",
            style = TextStyle(
                fontFamily = interFontFamily,
                color = Color.Black,
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal
            ),
            modifier = Modifier
                .padding(top = 10.dp, start = 12.dp, end = 12.dp)
                .fillMaxWidth()
        )

        Button(onClick = {
            val intent = Intent(context, MainActivity2::class.java)
            context.startActivity(intent)
        },
            shape = RoundedCornerShape(20.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = grey),
            modifier = Modifier
                .size(width = 400.dp, height = 75.dp) // Устанавливаем размер кнопки
                .fillMaxWidth()
                .padding(top = 12.dp, start = 12.dp, end = 12.dp, bottom = 12.dp)
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

class MoscowFamous : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyScreen4(context = this)
        }
    }
}