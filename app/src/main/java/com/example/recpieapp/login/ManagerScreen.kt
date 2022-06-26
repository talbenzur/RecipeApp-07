package com.example.recpieapp.login

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.platform.UriHandler
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.recpieapp.MainActivity
import com.google.firebase.auth.FirebaseAuth

@Composable
fun ManagerScreen(navController: NavController){

    val uriHandler = LocalUriHandler.current
    Text("HELLO MANAGER")
    val firebaseUrl = "https://console.firebase.google.com/u/0/"

    val apiUrl = "https://www.themealdb.com/api.php"

    Column (
        modifier = Modifier
            .background(color = MaterialTheme.colors.primary)
            .fillMaxSize().fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Button(onClick = { uriHandler.openUri(firebaseUrl) },
            modifier = Modifier.fillMaxWidth().padding(10.dp)) {
            Text(
                text = "FIREBASE",
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                fontSize = 16.sp
            )
        }

        Button(onClick = { uriHandler.openUri(apiUrl) },
            modifier = Modifier.fillMaxWidth().padding(10.dp)) {
            Text(
                text = "API",
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                fontSize = 16.sp
            )
        }

        Button(
            onClick = { navController.navigate("category") },
            modifier = Modifier.fillMaxWidth().padding(10.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = MaterialTheme.colors.primary,
                contentColor = Color.White
            )
        ) {
            Text(
                text = "Go To App",
                fontWeight = FontWeight.Bold,
                color = Color.Blue,
                fontSize = 16.sp
            )
        }
    }
}