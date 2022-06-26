package com.example.recpieapp.detail.repository

import com.example.recpieapp.detail.model.DetailResponse
import com.example.recpieapp.detail.service.IDetailService
import javax.inject.Inject

interface IDetailRepository{
    suspend fun getDetailsOfDish(id: String) : DetailResponse
}

class DetailRepository  @Inject constructor(
    val service: IDetailService
):IDetailRepository{

    override suspend fun getDetailsOfDish(id: String): DetailResponse {
       return service.getDetailsForDish(id)
    }
}