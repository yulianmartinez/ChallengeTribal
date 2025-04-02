package com.ymd.domain.service

import com.ymd.domain.repository.CategoryRepository

class CategoryService(private val categoryRepository: CategoryRepository) {

    fun getAllCategories() =
        categoryRepository.getAllCategories()

}