package com.ymd.tribalchallenge.ui.adapter
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ymd.tribalchallenge.R

class CategoryAdapter(private var categoryList: List<String>, private val listener: CategoryListener) :
    RecyclerView.Adapter<CategoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false)
        return CategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(categoryList[position], listener)
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

}