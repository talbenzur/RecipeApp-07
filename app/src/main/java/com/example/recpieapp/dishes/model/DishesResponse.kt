package com.example.recpieapp.dishes.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter

data class DishesResponse(
    val meals: List<Meal>
)

const val DELIM= "---"

//class NotoriousConverter {
//
//    @TypeConverter
//    fun toStr(lm: List<Meal>): String {
//        return lm.map{
//            "${it.idMeal}${DELIM}${it.strMeal}${DELIM}${it.strMealThumb}"
//        }.joinToString(separator = ",")
//    }
//
//    @TypeConverter
//    fun toObj(str: String):List<Meal>{
//        var out= mutableListOf<Meal>()
//        str.split(",").forEach{
//            val spl=it.split(DELIM)
//            out.add(
//                Meal(
//                    spl[0],
//                    spl[1],
//                    spl[2]
//                )
//            )
//        }
//        return out
//    }
//}



//TODO typeconvertecr

//class CategoryConverter{
//    @TypeConverter
//    fun listOfCategoryToString(list: List<Category>):String {
//        list.map {
//            "${it.idCategory}---${it.strCategory}---${it.strCategoryDescription}---${it.strCategoryThumb}"
//        }.
//        return ""
//    }
//
//
//}