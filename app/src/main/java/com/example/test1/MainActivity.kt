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
import com.google.firebase.firestore.FirebaseFirestore
import android.util.Log
import com.google.firebase.auth.FirebaseAuth

object SessionManager {
    private const val PREF_NAME = "UserSession"
    private const val KEY_IS_LOGGED_IN = "isLoggedIn"

    fun setLoggedIn(context: Context, isLoggedIn: Boolean) {
        val sharedPref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        with (sharedPref.edit()) {
            putBoolean(KEY_IS_LOGGED_IN, isLoggedIn)
            apply()
        }
    }

    fun isLoggedIn(context: Context): Boolean {
        val sharedPref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        return sharedPref.getBoolean(KEY_IS_LOGGED_IN, false)
    }
}

private const val TAG = "Firestore"
fun saveCountryToFirestore(userEmail: String, country: String) {
    val db = FirebaseFirestore.getInstance()
    val userCollectionRef = db.collection("users")

    // Проверяем, существует ли пользователь с данным email
    userCollectionRef.document(userEmail)
        .get()
        .addOnSuccessListener { documentSnapshot ->
            if (documentSnapshot.exists()) {
                // Если пользователь существует, добавляем страну к его истории поиска
                val userHistory = documentSnapshot.data?.toMutableMap()
                if (userHistory != null) {
                    userHistory[country] = true
                    userCollectionRef.document(userEmail)
                        .set(userHistory)
                        .addOnSuccessListener {
                            Log.d(TAG, "Country added for user with email: $userEmail")
                        }
                        .addOnFailureListener { e ->
                            Log.w(TAG, "Error adding country for user with email: $userEmail", e)
                        }
                } else {
                    Log.w(TAG, "Error retrieving user history for user with email: $userEmail")
                }
            } else {
                // Если пользователь не существует, создаем для него новую историю поиска
                val userHistory = hashMapOf(country to true)
                userCollectionRef.document(userEmail)
                    .set(userHistory)
                    .addOnSuccessListener {
                        Log.d(TAG, "New user document created with email: $userEmail")
                    }
                    .addOnFailureListener { e ->
                        Log.w(TAG, "Error creating new user document with email: $userEmail", e)
                    }
            }
        }
        .addOnFailureListener { e ->
            Log.w(TAG, "Error checking user existence with email: $userEmail", e)
        }
}

@Composable
fun MyScreen(context: Context) {
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

    val context = LocalContext.current
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
            modifier = Modifier.padding(top = 105.dp)
        )
        Text(
            text = "Добро пожаловать!",
            style = TextStyle(
                fontFamily = interFontFamily,
                color = Color.Black,
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal
            ),
            modifier = Modifier.padding(top = 81.dp)
        )
        Text(
            text = "Укажите место, куда бы Вы хотели отправиться",
            style = TextStyle(
                fontFamily = interFontFamily,
                color = Color.Black,
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Center
            ),
            modifier = Modifier.padding(top = 0.dp, start = 12.dp, end = 12.dp)
        )
        val textState = remember { mutableStateOf("") }
        TextField(
            value = textState.value,
            onValueChange = { textState.value = it },
            label = {
                Text(
                    text = "Введите страну/город",
                    style = TextStyle(
                        fontFamily = interFontFamily,
                        color = white,
                        fontSize = 16.sp
                    )
                ) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 40.dp, start = 12.dp, end = 12.dp),
            textStyle = TextStyle(
                color = black
            ),
        )


        if (isLoggedIn) {
            val user = FirebaseAuth.getInstance().currentUser
            val userEmail: String = if (user != null) {
                user.email ?: "default@mail.ru" // Если электронная почта пользователя null, используется значение "default@mail.ru"
            } else {
                "default@mail.ru" // Если пользователь не авторизован, используется значение "default@mail.ru"
            }
            Button(onClick = {
                val searchText = textState.value.trim()
                saveCountryToFirestore(userEmail, searchText)
                val intent = when {
                    searchText.equals("Москва", ignoreCase = true) -> Intent(context, MainActivity2::class.java)
                    else -> Intent(context, MainActivity3::class.java)
                }
                context.startActivity(intent)
            },
                shape = RoundedCornerShape(20.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = green),
                modifier = Modifier
                    .size(width = 400.dp, height = 75.dp) // Устанавливаем размер кнопки
                    .fillMaxWidth()
                    .padding(top = 20.dp, start = 12.dp, end = 12.dp)
            ){
                Text(
                    text = "Поиск",
                    style = TextStyle(
                        fontFamily = interFontFamily,
                        color = white,
                        fontSize = 24.sp
                    )
                )
            }

            Button(onClick = {
                SessionManager.setLoggedIn(context, true)
                val intent = Intent(context, Profile::class.java)
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
                    text = "Личный кабинет",
                    style = TextStyle(
                        fontFamily = interFontFamily,
                        color = white,
                        fontSize = 24.sp
                    )
                )
            }
        } else {
            Button(onClick = {
                val searchText = textState.value.trim()
                val intent = when {
                    searchText.equals("Москва", ignoreCase = true) -> Intent(context, MainActivity2::class.java)
                    else -> Intent(context, MainActivity3::class.java)
                }
                context.startActivity(intent)
            },
                shape = RoundedCornerShape(20.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = green),
                modifier = Modifier
                    .size(width = 400.dp, height = 75.dp) // Устанавливаем размер кнопки
                    .fillMaxWidth()
                    .padding(top = 20.dp, start = 12.dp, end = 12.dp)
            ){
                Text(
                    text = "Поиск",
                    style = TextStyle(
                        fontFamily = interFontFamily,
                        color = white,
                        fontSize = 24.sp
                    )
                )
            }
            Button(
                onClick = {
                    val intent = Intent(context, LoginActivity::class.java)
                    context.startActivity(intent)
                },
                shape = RoundedCornerShape(20.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = grey),
                modifier = Modifier
                    .size(width = 400.dp, height = 75.dp) // Устанавливаем размер кнопки
                    .fillMaxWidth()
                    .padding(top = 20.dp, start = 12.dp, end = 12.dp)
            ) {
                Text(
                    text = "Войти",
                    style = TextStyle(
                        fontFamily = interFontFamily,
                        color = white,
                        fontSize = 24.sp
                    )
                )
            }
        }
    }
}

class MainActivity : ComponentActivity() {
    private lateinit var openButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyScreen(context = this)
        }
    }
}