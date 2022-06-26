package com.example.recpieapp.dishes.repository

import com.example.recpieapp.category.services.ICategoryService
import com.example.recpieapp.db.DisherDao
import com.example.recpieapp.dishes.model.DishesResponse
import com.example.recpieapp.dishes.service.IDishesService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject


interface  IDishesRepository{

    suspend fun getDishesForCategory(categoryName: String): DishesResponse
}

class DishesRepository @Inject constructor(
    val service: IDishesService,
    val dao: DisherDao,
    val dispatcher: CoroutineDispatcher
) : IDishesRepository {

    override suspend fun getDishesForCategory(categoryName: String): DishesResponse {
        return withContext(dispatcher) {
            try{
                val response=  service.getDishesForCategory(categoryName)
                response.meals.forEach{ it.categoryId = categoryName }
                dao.saveDishesResponse(response.meals)
                response
            }catch (e:Exception){
                val list = dao.getDishesForCategory(categoryName)
                DishesResponse(list)
            }
        }
    }
}