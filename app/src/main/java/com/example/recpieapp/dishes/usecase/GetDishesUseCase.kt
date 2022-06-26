package com.example.recpieapp.dishes.usecase

import com.example.recpieapp.category.model.CategoryResponse
import com.example.recpieapp.dishes.model.DishesResponse
import com.example.recpieapp.dishes.repository.IDishesRepository
import javax.inject.Inject


interface IGetDishesUseCase{

    suspend operator fun invoke(categoryName: String): DishesResponse

}
class GetDishesUseCase @Inject constructor(
    val repo: IDishesRepository
): IGetDishesUseCase{

    override suspend fun invoke(categoryName: String): DishesResponse {
        return repo.getDishesForCategory(categoryName)
    }
}