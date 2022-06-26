package com.example.recpieapp.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.recpieapp.R
import com.example.recpieapp.detail.viewmodel.DetailViewModel

@OptIn(ExperimentalCoilApi::class)
@Composable
fun DetailScreen(
    viewmodel: DetailViewModel= hiltViewModel(),
    mealId: String?
){
    //SIDE EFFECT
    DisposableEffect(key1 = Unit){
        if(!mealId.isNullOrBlank()) {
            viewmodel.getDetailsForDishId(mealId)
        }
        onDispose {  }
    }
    val singleDish by remember { viewmodel.meal }
    //collect the state

    val uriHandler = LocalUriHandler.current
    singleDish?.let { //run this code if singleDish is not null
        Column (
            modifier = Modifier
                .background(color = MaterialTheme.colors.primary)
                .fillMaxSize()
                ,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top){
            Text(it.strMeal)
            Text(it.strArea)
            Image(modifier = Modifier.size(60.dp).clickable {
                viewmodel.saveToFavourites(it)
            },
                painter = painterResource(R.drawable.ic_heart), contentDescription = null)
            Image(
                modifier = Modifier.size(80.dp),painter = rememberImagePainter( //using coil
                    it.strMealThumb),
                contentDescription = null
            )

            ClickableText(text = AnnotatedString( it.strYoutube), onClick ={ _ ->
                uriHandler.openUri(it.strYoutube)
            } )
            //Text(it.strYoutube)
            InstructionTextBlock(
                instructions = it.strInstructions
            )

        }
    }
}

fun Modifier.`if`(
    condition: Boolean,
    then: Modifier.() -> Modifier
): Modifier=
    if(condition){
        then()
    }else{
        this
    }



@Composable
fun InstructionTextBlock( modifier: Modifier=Modifier,instructions: String){
    var showmore by remember {
        mutableStateOf(false)
    }
    Column(modifier = modifier
        .padding(16.dp)
        .fillMaxWidth()){
        Box(modifier = Modifier
        .`if`(!showmore){
                height(100.dp)
            }
        ){
            Text(instructions)
        }
        Button(onClick = {
            showmore= !showmore
        },
            colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.background)
        ) {
            Text(text="Show More...")
        }

    }
}
