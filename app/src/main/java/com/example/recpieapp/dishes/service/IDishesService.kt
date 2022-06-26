package com.example.recpieapp.dishes.service

import com.example.recpieapp.dishes.model.DishesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface IDishesService {

    @GET("filter.php")
    suspend fun getDishesForCategory(
        @Query("c") categoryName: String
    ): DishesResponse
}
//www.themealdb.com/api/json/v1/1/filter.php?c=Seafood