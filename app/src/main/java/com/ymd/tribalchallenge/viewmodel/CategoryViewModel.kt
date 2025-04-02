package com.ymd.tribalchallenge.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ymd.domain.service.CategoryService
import com.ymd.infrastructure.di.IoDispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val categoryService: CategoryService,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
): ViewModel() {

    private val _categories: MutableStateFlow<List<String>?> = MutableStateFlow(null)
    val categories: StateFlow<List<String>?> get() = _categories

    fun getAllCategories() =
        viewModelScope.launch(ioDispatcher) {
            try {
                categoryService.getAllCategories().collectLatest { categoryList ->
                    _categories.value = categoryList
                }
            } catch (exception: Exception) {
                _categories.value = null
            }
        }

}