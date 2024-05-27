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
import androidx.compose.ui.res.colorResource
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.navigation.compose.rememberNavController
import androidx.activity.compose.setContent
import android.content.Context
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.ui.res.painterResource
import android.net.Uri
import androidx.compose.foundation.text.ClickableText
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.SpanStyle


@Composable
fun MyScreen6(context: Context) {
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
            text = "Отель Viepoint",
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
            painter = painterResource(id = R.drawable.viewpoint),
            contentDescription = "Viewpoint",
            modifier = Modifier.padding(10.dp)
        )
        Text(
            text = "Мечтали пожить в центре Москвы с отличным видом на набережную? Тогда это отличное место, " +
                    "чтобы насладиться атмосферой, наблюдая из окна за прохожими и рассматривая архитектуру" +
                    " вокруг.",
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
        val text1 = buildAnnotatedString {
            append("")
            pushStringAnnotation("WEBSITE", "https://bv-hotel.ru/")
            addStyle(
                style = SpanStyle(
                    color = Color.Black,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal
                ),
                start = 0,
                end = length
            )
            append("https://bv-hotel.ru/")
            pop()
        }
        ClickableText(
            text = text1,
            onClick = { offset ->
                text1.getStringAnnotations("WEBSITE", offset, offset)
                    .firstOrNull()?.let { annotation ->
                        val uri = Uri.parse("${annotation.item}")
                        val intent = Intent(Intent.ACTION_VIEW, uri)
                        context.startActivity(intent)
                    }
            }
        )

        Text(
            text = "Хостел ICON",
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
            painter = painterResource(id = R.drawable.icon),
            contentDescription = "Icon",
            modifier = Modifier
                .padding(12.dp)
        )
        Text(
            text = "С высотками сфотографировались, а места пожить в них осталась... Смело выбирайте " +
                    "данных хостел, чтобы пожить на 43-м этаже башни Империя. Незабываемый вид из окна " +
                    "гарантирован!",
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
        val text2 = buildAnnotatedString {
            append("")
            pushStringAnnotation("WEBSITE", "https://iconhostel.com/")
            addStyle(
                style = SpanStyle(
                    color = Color.Black,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal
                ),
                start = 0,
                end = length
            )
            append("https://iconhostel.com/")
            pop()
        }
        ClickableText(
            text = text2,
            onClick = { offset ->
                text2.getStringAnnotations("WEBSITE", offset, offset)
                    .firstOrNull()?.let { annotation ->
                        val uri = Uri.parse("${annotation.item}")
                        val intent = Intent(Intent.ACTION_VIEW, uri)
                        context.startActivity(intent)
                    }
            }
        )

        Text(
            text = "Pana white moscow hotel",
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
            painter = painterResource(id = R.drawable.pana),
            contentDescription = "Pana",
            modifier = Modifier.padding(10.dp)
        )
        Text(
            text = "Ищете стандартный отель для туристов с белоснежным постельным бельем и вкусным " +
                    "завтраком? Pana white станет отличным выбором - до Красной площади всего две станции" +
                    " на метро, а по вечерам барно-ресторанная жизнь прямо вокруг вас.",
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
        val text3 = buildAnnotatedString {
            append("")
            pushStringAnnotation("WEBSITE", "https://panawhite.ru/")
            addStyle(
                style = SpanStyle(
                    color = Color.Black,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal
                ),
                start = 0,
                end = length
            )
            append("https://panawhite.ru/")
            pop()
        }
        ClickableText(
            text = text3,
            onClick = { offset ->
                text3.getStringAnnotations("WEBSITE", offset, offset)
                    .firstOrNull()?.let { annotation ->
                        val uri = Uri.parse("${annotation.item}")
                        val intent = Intent(Intent.ACTION_VIEW, uri)
                        context.startActivity(intent)
                    }
            }
        )

        Text(
            text = "PR Myasnitsky Boutique Hotel",
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
            painter = painterResource(id = R.drawable.pr),
            contentDescription = "Pr",
            modifier = Modifier.padding(10.dp)
        )
        Text(
            text = "Предпочитаете ремонт в стиле лофт? Кирпичные стены, паркет и дубовые рамы Вы найдете" +
                    " иименно в этом отеле. Предлагаем, не терять время зря и утстроить тематическую " +
                    "фотосессию прямо в свое номере.",
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
        val text4 = buildAnnotatedString {
            append("")
            pushStringAnnotation("WEBSITE", "https://prhotelgroup.ru/")
            addStyle(
                style = SpanStyle(
                    color = Color.Black,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal
                ),
                start = 0,
                end = length
            )
            append("https://prhotelgroup.ru/")
            pop()
        }
        ClickableText(
            text = text4,
            onClick = { offset ->
                text4.getStringAnnotations("WEBSITE", offset, offset)
                    .firstOrNull()?.let { annotation ->
                        val uri = Uri.parse("${annotation.item}")
                        val intent = Intent(Intent.ACTION_VIEW, uri)
                        context.startActivity(intent)
                    }
            }
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

class MoscowHotels : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyScreen6(context = this)
        }
    }
}