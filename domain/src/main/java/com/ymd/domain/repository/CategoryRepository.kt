package com.ymd.domain.repository

import kotlinx.coroutines.flow.Flow

interface CategoryRepository {
    fun getAllCategories(): Flow<List<String>>
}