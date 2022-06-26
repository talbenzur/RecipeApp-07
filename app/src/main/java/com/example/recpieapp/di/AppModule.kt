package com.example.recpieapp.di

import com.example.recpieapp.category.repository.CategoryRepository
import com.example.recpieapp.category.repository.ICategoryRepository
import com.example.recpieapp.category.services.ICategoryService
import com.example.recpieapp.category.usecase.GetCategoriesUseCase
import com.example.recpieapp.category.usecase.IGetCategoriesUseCase
import com.example.recpieapp.detail.repository.DetailRepository
import com.example.recpieapp.detail.repository.IDetailRepository
import com.example.recpieapp.detail.service.IDetailService
import com.example.recpieapp.detail.usecase.GetDetailsUseCase
import com.example.recpieapp.detail.usecase.IGetDetailsUseCase
import com.example.recpieapp.dishes.repository.DishesRepository
import com.example.recpieapp.dishes.repository.IDishesRepository
import com.example.recpieapp.dishes.service.IDishesService
import com.example.recpieapp.dishes.usecase.GetDishesUseCase
import com.example.recpieapp.dishes.usecase.IGetDishesUseCase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://www.themealdb.com/api/json/v1/1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideCategoryService(retrofit: Retrofit): ICategoryService{
        return retrofit.create(ICategoryService::class.java)
    }

    @Provides
    @Singleton
    fun provideDishesService(retrofit: Retrofit): IDishesService {
        return retrofit.create(IDishesService::class.java)
    }
    @Provides
    @Singleton
    fun provideDetailService(retrofit: Retrofit): IDetailService {
        return retrofit.create(IDetailService::class.java)
    }

    @Provides
    @Singleton
    fun provideDispatcher(): CoroutineDispatcher{
        return Dispatchers.IO
    }

    @Module
    @InstallIn(SingletonComponent::class)
    interface  AppModuleInt{

        @Binds
        @Singleton //only one repository
        fun provideDishesRepository(repo: DishesRepository) : IDishesRepository

        @Binds
        @Singleton
        fun provideDishesUseCase(uc: GetDishesUseCase) : IGetDishesUseCase

        @Binds
        @Singleton //only one repository
        fun provideDetailRepository(repo: DetailRepository) : IDetailRepository

        @Binds
        @Singleton
        fun provideDetailUseCase(uc: GetDetailsUseCase) : IGetDetailsUseCase

        @Binds
        @Singleton //only one repository
        fun provideCategoryRepository(repo: CategoryRepository) :ICategoryRepository

        @Binds
        @Singleton
        fun provideGetCategoryUseCase(uc: GetCategoriesUseCase) : IGetCategoriesUseCase


    }
}