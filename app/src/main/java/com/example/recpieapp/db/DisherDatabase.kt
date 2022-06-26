package com.example.recpieapp.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.recpieapp.category.model.Category
import com.example.recpieapp.category.model.CategoryResponse
import com.example.recpieapp.detail.model.MealDetail
import com.example.recpieapp.detail.model.SmallerMeal
import com.example.recpieapp.dishes.model.DishesResponse
import com.example.recpieapp.dishes.model.Meal

@Database(
    entities =[SmallerMeal::class, Category::class, Meal::class],
    version =1,
    exportSchema = false
)

abstract class DisherDatabase :RoomDatabase(){

    abstract fun provideDao(): DisherDao


}