package com.ymd.tribalchallenge.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.ymd.tribalchallenge.R
import com.ymd.tribalchallenge.ui.adapter.CategoryAdapter
import com.ymd.tribalchallenge.ui.adapter.CategoryListener
import com.ymd.tribalchallenge.viewmodel.CategoryViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), CategoryListener {

    private lateinit var viewModel: CategoryViewModel

    private lateinit var categoryAdapter: CategoryAdapter
    private val categoryLayoutManager: GridLayoutManager by lazy { GridLayoutManager(this, 1) }
    private var categoryList: MutableList<String> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initViewModel()
        observerViewModel()
        initUi()
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this)[CategoryViewModel::class.java]
        viewModel.getAllCategories()
    }

    private fun observerViewModel() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.categories.collect { categoryList ->
                    categoryList?.let {
                        processCategory(it)
                    }
                }
            }
        }
    }

    private fun initUi() {
        val categoryRecyclerView: RecyclerView = findViewById(R.id.category_recyclerview)
        categoryRecyclerView.apply {
            layoutManager = categoryLayoutManager
            categoryAdapter = CategoryAdapter(categoryList, this@MainActivity)
            adapter = categoryAdapter
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun processCategory(categoryList: List<String>) {
        this.categoryList.apply {
            clear()
            addAll(categoryList)
        }
        categoryAdapter.notifyDataSetChanged()
    }

    override fun onSelectCategory(category: String, view: View) {
        Snackbar.make(view, "Selecionando la categoría: $category", Snackbar.LENGTH_SHORT).show()
    }
}