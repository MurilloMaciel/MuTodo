package com.maciel.murillo.mutodo.modules.categories.presentation

import android.view.View
import androidx.core.content.ContextCompat
import com.maciel.murillo.mutodo.R
import com.maciel.murillo.mutodo.core.extensions.isNull
import com.maciel.murillo.mutodo.core.presentation.base.BaseViewHolder
import com.maciel.murillo.mutodo.modules.categories.presentation.model.CategoryPresentation
import kotlinx.android.synthetic.main.view_task_categories.view.*

class CategoriesViewHolder(
        view: View,
        callback: CategoryListener
) : BaseViewHolder<CategoryPresentation>(view) {

    init {
        view.cl_category.setOnClickListener {
            callback.onClickCategory(adapterPosition)
        }
    }

    override fun bind(item: CategoryPresentation): Unit = with(itemView) {

        tv_tasks_remaining.text = if (item.remaining.isNull()) {
            " "
        } else {
            resources.getString(R.string.tasks_remaining).replace("{count}", item.remaining.toString())
        }
        cl_image_container.backgroundTintList = ContextCompat.getColorStateList(context, item.categoryPresentation.backgroundColor)?.withAlpha(60)
        iv_category.setImageDrawable(ContextCompat.getDrawable(context, item.categoryPresentation.icon))
        iv_category.setColorFilter(ContextCompat.getColor(context, item.categoryPresentation.backgroundColor))
        tv_title.text = resources.getString(item.categoryPresentation.title)
    }
}