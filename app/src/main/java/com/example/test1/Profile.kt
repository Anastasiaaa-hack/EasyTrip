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
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.layout.Box
import androidx.compose.ui.platform.LocalContext
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import android.util.Log
import androidx.compose.runtime.LaunchedEffect

private const val TAG = "Firestore"

fun getUserHistoryFromFirestore(userEmail: String, onSuccess: (Map<String, Any>?) -> Unit, onFailure: (Exception) -> Unit) {
    val db = FirebaseFirestore.getInstance()
    val userCollectionRef = db.collection("users")
    userCollectionRef.document(userEmail)
        .get()
        .addOnSuccessListener { documentSnapshot ->
            val userHistory = documentSnapshot.data
            onSuccess(userHistory)
        }
        .addOnFailureListener { e ->
            onFailure(e)
        }
}

@Composable
fun MyScreen5(context: Context) {
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
    val red = colorResource(id = R.color.red)
    val back = colorResource(id = R.color.my_background_color)

    val isLoggedIn = SessionManager.isLoggedIn(context)

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
            modifier = Modifier.padding(top = 12.dp)
        )
        val db = FirebaseFirestore.getInstance()
        val userCollectionRef = db.collection("users")
        val user = FirebaseAuth.getInstance().currentUser
        val userEmail: String = if (user != null) {
            user.email ?: "default@mail.ru" // Если электронная почта пользователя null, используется значение "default@mail.ru"
        } else {
            "default@mail.ru" // Если пользователь не авторизован, используется значение "default@mail.ru"
        }
        Text(
            text = userEmail,
            style = TextStyle(
                fontFamily = interFontFamily,
                color = Color.Black,
                fontSize = 20.sp,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Left
            ),
            modifier = Modifier.padding(top = 10.dp, start = 12.dp, end = 12.dp)
        )
        Text(
            text = "История поиска",
            style = TextStyle(
                fontFamily = interFontFamily,
                color = Color.Black,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Left
            ),
            modifier = Modifier.padding(top = 10.dp, start = 12.dp, end = 12.dp)
        )
        val (userHistory, setUserHistory) = remember { mutableStateOf<Map<String, Any>?>(null) }
        LaunchedEffect(userEmail) {
            getUserHistoryFromFirestore(userEmail,
                onSuccess = { userHistory ->
                    setUserHistory(userHistory)
                },
                onFailure = { e ->
                    Log.e(TAG, "Error getting user history for user with email: $userEmail", e)
                }
            )
        }
        if (userHistory == null) {
            Text(
                text = "У пользователя нет истории поиска",
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Normal,
                    textAlign = TextAlign.Center
                ),
                modifier = Modifier.padding(8.dp)
            )
        }
        // Обработчик нажатия на город
        userHistory?.forEach { (country, _) ->
            Button(
                onClick = {
                    val intent = when {
                        country.equals("Москва", ignoreCase = true) -> Intent(context, MainActivity2::class.java)
                        else -> Intent(context, MainActivity3::class.java)
                    }
                    context.startActivity(intent)
                },
                modifier = Modifier
                    .padding(top = 20.dp, start = 12.dp, end = 12.dp)
                    .fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(backgroundColor = back)
            ) {
                Text(
                    text = country,
                    style = TextStyle(
                        fontFamily = interFontFamily,
                        color = black,
                        fontSize = 16.sp
                    )
                )
            }
        }
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
        val context = LocalContext.current

        Button(onClick = {
            SessionManager.setLoggedIn(context, false)
            val intent = Intent(context, MainActivity::class.java)
            context.startActivity(intent)
        },
            shape = RoundedCornerShape(20.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = red),
            modifier = Modifier
                .size(width = 400.dp, height = 75.dp) // Устанавливаем размер кнопки
                .fillMaxWidth()
                .padding(top = 20.dp, start = 12.dp, end = 12.dp)
        ){
            Text(
                text = "Выйти",
                style = TextStyle(
                    fontFamily = interFontFamily,
                    color = white,
                    fontSize = 24.sp
                )
            )
        }
    }
}

class Profile : ComponentActivity() {
    private lateinit var openButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyScreen5(context = this)
        }
    }
}