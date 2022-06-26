package com.example.recpieapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.example.recpieapp.category.CategoryScreen
import com.example.recpieapp.detail.DetailScreen
import com.example.recpieapp.dishes.DishesScreen

import com.example.recpieapp.ui.theme.RecpieAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RecpieAppTheme {
                DisherApp()
            }
        }
    }
}

@Composable
fun DisherApp() {

    val navController = rememberNavController()

    NavHost(navController =navController , startDestination = "category" ){

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

