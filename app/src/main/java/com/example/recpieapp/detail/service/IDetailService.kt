package com.example.recpieapp.detail.service

import com.example.recpieapp.detail.model.DetailResponse
import com.example.recpieapp.dishes.model.DishesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface IDetailService {

    @GET("lookup.php")
    suspend fun getDetailsForDish(
        @Query("i") mealId: String
    ): DetailResponse
}
//www.themealdb.com/api/json/v1/1/lookup.php?i=52772