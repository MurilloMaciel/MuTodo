package com.maciel.murillo.mutodo.modules.categories.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.maciel.murillo.mutodo.R
import com.maciel.murillo.mutodo.modules.categories.presentation.model.CategoryPresentation

class CategoriesAdapter(
    private val categories: List<CategoryPresentation>,
    private val callback: CategoryListener
) : RecyclerView.Adapter<CategoriesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        val view = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.view_task_categories, parent, false)
        return CategoriesViewHolder(view, callback)
    }

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        holder.bind(categories[position])
    }

    override fun getItemCount(): Int = categories.size
}