package com.ymd.infrastructure.di

import com.ymd.domain.repository.CategoryRepository
import com.ymd.domain.service.CategoryService
import com.ymd.infrastructure.api.ApiCategory
import com.ymd.infrastructure.service.CategoryServiceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CategoryModule {

    @Provides
    fun provideCategoryService(categoryRepository: CategoryRepository) : CategoryService =
        CategoryService(categoryRepository)

    @Provides
    fun provideCategoryRepository(category: ApiCategory): CategoryRepository {
        return CategoryServiceImpl(category)
    }

    @Singleton
    @Provides
    fun provideCategoryApi(retrofit: Retrofit): ApiCategory {
        return retrofit.create(ApiCategory::class.java)
    }

}