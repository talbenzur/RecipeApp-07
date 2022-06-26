package com.example.recpieapp


import android.os.Bundle
import android.util.Log
import android.util.Patterns
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedContentScope.SlideDirection.Companion.Down

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier

import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.example.recpieapp.category.CategoryScreen
import com.example.recpieapp.detail.DetailScreen
import com.example.recpieapp.dishes.DishesScreen

import androidx.compose.foundation.background

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions

import androidx.compose.material.*

import androidx.compose.ui.Alignment
import androidx.compose.ui.focus.FocusDirection

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager

import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle

import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.navigation.NavController
import com.example.recpieapp.MainActivity.Companion.TAG
import com.example.recpieapp.login.LoginScreen
import com.example.recpieapp.login.ManagerScreen


import com.example.recpieapp.ui.theme.RecpieAppTheme
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    companion object {
        val TAG: String = MainActivity::class.java.simpleName
    }

    private val auth by lazy {
        Firebase.auth
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RecpieAppTheme {
                //LoginScreen(auth)
                DisherApp(auth)
            }
        }
    }
}


//vikvik2020@gmail.com
@Composable
fun DisherApp(auth: FirebaseAuth) {

    val navController = rememberNavController()

    NavHost(navController =navController , startDestination = "login" ){

        composable("login") {
            LoginScreen(auth = auth, navController)
        }
        composable("manager") {
            ManagerScreen(navController)
        }
        composable( "category") {
            CategoryScreen { category ->
                navController.navigate("dishes/${category}")
            } //we pass a function
        }
        composable("dishes/{category}", arguments = listOf(navArgument("category"){
            type = NavType.StringType
        })){

            val categoryStr = remember {
                it.arguments?.getString("category")
            }
            DishesScreen(category = categoryStr){dishid->
                navController.navigate("detail/${dishid}")
            }
        }
        composable("detail/{mealid}", arguments = listOf(navArgument("mealid"){
            type = NavType.StringType
        })){
            val mealStrId = remember {
                it.arguments?.getString("mealid")
            }
            DetailScreen(mealId = mealStrId)
        }
    }
}

//@Composable
//fun LoginScreen(auth: FirebaseAuth, navController: NavController){
//    val focusManager = LocalFocusManager.current
//    var email by remember {
//        mutableStateOf("")
//    }
//
//    var password by remember {
//        mutableStateOf("")
//    }
//
//    val isEmailValid by derivedStateOf {
//        Patterns.EMAIL_ADDRESS.matcher(email).matches()
//    }
//
//    val isPasswordValid by derivedStateOf {
//        password.length>7
//    }
//
//    var isPasswordVisible by remember {
//        mutableStateOf(false)
//    }
//
//    Column (
//        modifier = Modifier
//            .background(color = Color.LightGray)
//            .fillMaxSize(),
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Top
//    ) {
//        Text(
//            text = "Welcome back..",
//            fontFamily = FontFamily.Companion.SansSerif,
//            fontWeight = FontWeight.Bold,
//            fontStyle = FontStyle.Italic,
//            fontSize = 32.sp,
//            modifier = Modifier.padding(top=16.dp)
//        )
//
//        Image(
//            painter = painterResource(id = R.drawable.ic_recipebook),
//            contentDescription = "KFC",
//            modifier = Modifier
//                .padding(top = 16.dp)
//                .size(60.dp)
//        )
//
//        Text(
//            text = "...To the house of chicken",
//            fontFamily = FontFamily.Companion.SansSerif,
//            fontWeight = FontWeight.Bold,
//            fontStyle = FontStyle.Italic,
//            fontSize = 20.sp,
//            modifier = Modifier.padding(bottom =16.dp)
//        )
//
//        Card(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(horizontal = 8.dp),
//            shape = RoundedCornerShape(16.dp),
//            border = BorderStroke(1.dp, Color.Black)
//        ) {
//            Column(
//                horizontalAlignment = Alignment.CenterHorizontally,
//                verticalArrangement = Arrangement.spacedBy(8.dp),
//                modifier = Modifier.padding(all=10.dp)
//            ) {
//                OutlinedTextField(value = email,
//                    onValueChange = {email = it},
//                    label = { Text("Email Address")},
//                    placeholder = { Text("abc@domain.com")},
//                    singleLine = true,
//                    modifier = Modifier.fillMaxWidth(),
//                    keyboardOptions = KeyboardOptions(
//                        keyboardType = KeyboardType.Email,
//                        imeAction = ImeAction.Next
//                    ),
//                    keyboardActions =  KeyboardActions(
//                        onNext = { focusManager.moveFocus(FocusDirection.Down)}
//                    ),
//                    isError = !isEmailValid,
//
//                )
//
//                OutlinedTextField(value = password,
//                    onValueChange = {password = it},
//                    label = { Text("password")},
//                    singleLine = true,
//                    modifier = Modifier.fillMaxWidth(),
//                    keyboardOptions = KeyboardOptions(
//                        keyboardType = KeyboardType.Password,
//                        imeAction = ImeAction.Done
//                    ),
//                    keyboardActions =  KeyboardActions(
//                        onNext = { focusManager.clearFocus()}
//                    ),
//                    isError = isPasswordValid,
//
//
//                )
//
//                Button(onClick = {
//                         auth.signInWithEmailAndPassword(email,password)
//                             .addOnCompleteListener{
//                                 if(it.isSuccessful){
//                                     Log.d(TAG,"SUCCESSS!!!!")
//                                     navController.navigate("category")
//                                 } else {
//                                     Log.w(TAG,"FAILED",it.exception)
//                                 }
//                             }
//
//                },
//                    modifier = Modifier.fillMaxWidth(),
//                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red),
//                    enabled = isEmailValid && isPasswordValid) {
//                    Text(text = "Log In",
//                        fontWeight = FontWeight.Bold,
//                        color = Color.Black,
//                        fontSize = 16.sp)
//                }
//            }
//        }
//
//        Row(
//            horizontalArrangement = Arrangement.End,
//            modifier = Modifier.fillMaxWidth()
//        ) {
//            TextButton(onClick = { /*TODO*/ }) {
//                Text(
//                    color = Color.Black,
//                    fontStyle = FontStyle.Italic,
//                    text = "Forgotten Password?",
//                    modifier = Modifier.padding(end = 8.dp)
//                )
//            }
//        }
//
//        Button(
//            onClick = {},
//            enabled = true,
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(all = 16.dp),
//            colors = ButtonDefaults.buttonColors(backgroundColor = Color.White)
//        ) {
//            Text(
//                text = "Register",
//                fontWeight = FontWeight.Bold,
//                color = Color.Black,
//                fontSize = 16.sp
//            )
//        }
//    }
//}