package com.example.recpieapp.category

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.recpieapp.category.model.Category
import com.example.recpieapp.category.viewmodel.CategoryViewModel

@Composable
fun CategoryScreen(
    viewmodel: CategoryViewModel = hiltViewModel(),
    onItemClick: (String) -> Unit   //lamda function , Uint is like void

) {
    val listOFCategories by remember { viewmodel.listOfCategories }
    Text(text = "Please choose a category")
    LazyColumn (

        modifier = Modifier.background(color = MaterialTheme.colors.primary)
            ){

        items(listOFCategories) { item ->
            SingleItem(item.strCategory, item.strCategoryThumb) //on kotlin if the last variable is lambda function you can pass it with {} after the (). like that:
            {
                onItemClick(it)
            }
        }
    }
}


@OptIn(ExperimentalCoilApi::class)
@Composable
fun SingleItem(
    title: String,
    thumbnail: String,
    onClick: (String) -> Unit

) {


    Card(
        modifier = Modifier
            .padding(8.dp)
            .background(color = MaterialTheme.colors.primary)
            .fillMaxWidth()
            .clickable { onClick(title) },
        elevation = 8.dp )
    {
        Row( modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically){
            Image(
                modifier = Modifier.size(80.dp),painter = rememberImagePainter(
                    thumbnail),
                contentDescription = null
            )
            Text(text = title, fontSize = 24.sp)

        }
    }
}
