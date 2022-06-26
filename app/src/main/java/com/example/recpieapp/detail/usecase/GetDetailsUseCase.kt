package com.example.recpieapp.detail.usecase

import com.example.recpieapp.detail.model.DetailResponse
import com.example.recpieapp.detail.repository.IDetailRepository
import javax.inject.Inject

interface  IGetDetailsUseCase{
    operator  suspend fun invoke( id: String) :DetailResponse
}

class GetDetailsUseCase @Inject constructor(
    val repo: IDetailRepository
) :IGetDetailsUseCase{

    override suspend fun invoke(id: String): DetailResponse {
        return repo.getDetailsOfDish(id)
    }

}