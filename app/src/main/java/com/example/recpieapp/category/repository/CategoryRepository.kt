package com.example.recpieapp.category.repository

import android.util.Log
import com.example.recpieapp.category.model.CategoryResponse
import com.example.recpieapp.category.services.ICategoryService
import com.example.recpieapp.db.DisherDao
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface ICategoryRepository{
    suspend fun getAllCategories():CategoryResponse
}

class CategoryRepository @Inject constructor(
    val service: ICategoryService,
    val dao: DisherDao,
    val dispatcher: CoroutineDispatcher

):ICategoryRepository{
    override suspend fun getAllCategories(): CategoryResponse {
        return withContext(dispatcher) {
            val response = try {
                val hold = service.getAllCategories()
                dao.saveCategoryResponse(hold.categories)
                hold
            } catch (e: Exception) {
                Log.d("BK", "Failed" + e.localizedMessage)
                CategoryResponse(dao.getAllCategories())
            }
            response
        }
    }
}