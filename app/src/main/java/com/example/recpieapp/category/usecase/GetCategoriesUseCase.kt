package com.example.recpieapp.category.usecase

import com.example.recpieapp.category.model.CategoryResponse
import com.example.recpieapp.category.repository.ICategoryRepository
import javax.inject.Inject

interface IGetCategoriesUseCase{

    suspend operator fun invoke(): CategoryResponse

}

class GetCategoriesUseCase @Inject constructor(
    val repo: ICategoryRepository
) :IGetCategoriesUseCase{

    override suspend fun invoke(): CategoryResponse {
        return repo.getAllCategories()
    }
}