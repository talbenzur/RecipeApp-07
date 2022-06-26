package com.example.recpieapp.login

import android.content.res.Resources
import android.util.Log
import android.util.Patterns
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.recpieapp.MainActivity
import com.google.firebase.auth.FirebaseAuth
import androidx.compose.runtime.R as R

@Composable
fun LoginScreen(auth: FirebaseAuth, navController: NavController){



    val focusManager = LocalFocusManager.current
    var email by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    val isEmailValid by derivedStateOf {
        Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    val isPasswordValid by derivedStateOf {
        password.length>5
    }

    var isPasswordVisible by remember {
        mutableStateOf(false)
    }

    Column (
        modifier = Modifier
            .background(color = MaterialTheme.colors.primary)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = "Welcome",
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Italic,
            fontSize = 32.sp,
            modifier = Modifier.padding(top=16.dp)
        )

        Image(
            painter = painterResource(com.example.recpieapp.R.drawable.ic_recipebook),
            contentDescription = "Recipe",
            modifier = Modifier
                .padding(top = 16.dp)
                .size(250.dp)
        )

        Text(
            text = "To Cooking World",
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Italic,
            fontSize = 20.sp,
            modifier = Modifier.padding(bottom =16.dp)
        )

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            shape = RoundedCornerShape(16.dp),
            border = BorderStroke(1.dp, Color.Black)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.padding(all=10.dp)
            ) {
                OutlinedTextField(value = email,
                    onValueChange = {email = it},
                    label = { Text("Email Address") },
                    placeholder = { Text("abc@domain.com") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions =  KeyboardActions(
                        onNext = { focusManager.moveFocus(FocusDirection.Down)}
                    ),
                    isError = !isEmailValid,

                    )

                OutlinedTextField(value = password,
                    onValueChange = {password = it},
                    label = { Text("password") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions =  KeyboardActions(
                        onNext = { focusManager.clearFocus()}
                    ),
                    //isError = isPasswordValid,


                    )

                Button(onClick = {
                    auth.signInWithEmailAndPassword(email,password)
                        .addOnCompleteListener{
                            if(it.isSuccessful && email=="vikvik2020@gmail.com"){
                                Log.d(MainActivity.TAG,"SUCCESSS!!!!")
                                navController.navigate("manager")
                            } else if(it.isSuccessful){
                                Log.d(MainActivity.TAG,"SUCCESSS!!!!")
                                navController.navigate("category")}
                            else{
                                Log.w(MainActivity.TAG,"FAILED",it.exception)
                            }
                        }

                },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primary, contentColor = Color.White),
                    enabled = isEmailValid && isPasswordValid) {
                    Text(text = "Log In",
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        fontSize = 16.sp)
                }
            }
        }

        Row(
            horizontalArrangement = Arrangement.End,
            modifier = Modifier.fillMaxWidth()
        ) {
            TextButton(onClick = { /*TODO*/ }) {
                Text(
                    color = Color.Black,
                    fontStyle = FontStyle.Italic,
                    text = "Forgotten Password?",
                    modifier = Modifier.padding(end = 8.dp)
                )
            }
        }

        Button(
            onClick = {},
            enabled = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 16.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.White)
        ) {
            Text(
                text = "Register",
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                fontSize = 16.sp
            )
        }
    }
}