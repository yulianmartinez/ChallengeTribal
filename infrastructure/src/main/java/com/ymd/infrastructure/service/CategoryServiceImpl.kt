package com.ymd.infrastructure.service

import com.ymd.domain.exception.CategoryException
import com.ymd.domain.repository.CategoryRepository
import com.ymd.infrastructure.api.ApiCategory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CategoryServiceImpl @Inject constructor(private val apiCategory: ApiCategory):
    CategoryRepository {

    override fun getAllCategories(): Flow<List<String>> =
        flow {
            emit(apiCategory.getAllCategories())
        }
        .catch {
            it.printStackTrace()
            throw CategoryException("Error al consultar las categorias")
        }

}