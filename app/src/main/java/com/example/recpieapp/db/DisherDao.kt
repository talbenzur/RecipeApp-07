package com.example.recpieapp.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import androidx.room.Update
import com.example.recpieapp.category.model.Category
import com.example.recpieapp.category.model.CategoryResponse
import com.example.recpieapp.detail.model.MealDetail
import com.example.recpieapp.detail.model.SmallerMeal
import com.example.recpieapp.dishes.model.DishesResponse
import com.example.recpieapp.dishes.model.Meal


@Dao
interface DisherDao {


    @Query("SELECT*FROM meal_details")
    suspend fun getListOfString(): List<SmallerMeal>

    @Insert(onConflict = REPLACE)
    suspend fun saveMeal(meal: SmallerMeal)

    @Query("SELECT*FROM categories")
    suspend fun getAllCategories(): List<Category>

    @Insert(onConflict = REPLACE)
    suspend fun saveCategoryResponse(list: List<Category>)

    @Insert(onConflict = REPLACE)
    suspend fun saveDishesResponse(list: List<Meal>)

    @Query("SELECT*FROM dishes WHERE categoryId= :categoryIdIn")
    suspend fun getDishesForCategory(categoryIdIn: String):List<Meal>

}