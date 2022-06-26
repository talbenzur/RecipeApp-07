package com.example.recpieapp.dishes

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import coil.annotation.ExperimentalCoilApi
import com.example.recpieapp.category.SingleItem
import com.example.recpieapp.dishes.model.DishesResponse
import com.example.recpieapp.dishes.model.Meal
import com.example.recpieapp.dishes.viewmodel.DishesViewModel
import com.example.recpieapp.dishes.viewmodel.ViewState

@OptIn(ExperimentalCoilApi::class)

//TODO view states
@Composable
fun DishesScreen (
    viewmodel: DishesViewModel= hiltViewModel(),
    category: String?,
    onDishClick: (String) ->Unit
) {
    DisposableEffect(key1 = Unit){
        if(!category.isNullOrBlank()){
            viewmodel.geyDishesForCategory(category)
        }
        onDispose {}
    }
    val viewState by remember{ viewmodel.viewState }

    when(val state= viewState){
        is ViewState.Success-> {
            DishesList(state.data, onDishClick)
        }
        is ViewState.Error -> {
            Text(text = "Error: ${state.errorMessage}")
        }
        else -> {
            Text(text = "Loading")
        }
    }
}

@Composable
fun DishesList(dishes: DishesResponse, onDishClick: (String) -> Unit) {
    Column{
        LazyColumn {
            items(dishes.meals) { item ->
                SingleItem(title = item.strMeal, thumbnail =item.strMealThumb){
                    onDishClick(item.idMeal)
                }

            }
        }
    }

}
