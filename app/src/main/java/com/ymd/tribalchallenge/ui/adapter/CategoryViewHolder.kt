package com.ymd.tribalchallenge.ui.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ymd.tribalchallenge.R

class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val titleCategory: TextView = itemView.findViewById(R.id.title_category)

    fun bind(category: String, listener: CategoryListener) {

        titleCategory.text = category

        titleCategory.setOnClickListener {
            listener.onSelectCategory(category, itemView)
        }

    }

}