package com.maciel.murillo.mutodo.modules.categories.presentation

import android.view.View
import androidx.core.content.ContextCompat
import com.maciel.murillo.mutodo.R
import com.maciel.murillo.mutodo.core.extensions.isNull
import com.maciel.murillo.mutodo.core.extensions.safe
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

        tv_tasks_count.text = setTasksCount(item.count.safe())
        cl_image_container.backgroundTintList = ContextCompat.getColorStateList(context, item.category.backgroundColor)?.withAlpha(60)
        iv_category.setImageDrawable(ContextCompat.getDrawable(context, item.category.icon))
        iv_category.setColorFilter(ContextCompat.getColor(context, item.category.backgroundColor))
        tv_title.text = resources.getString(item.category.title)
    }

    private fun setTasksCount(remaining: Int): String {
        return when {
            remaining.isNull() -> " "
            remaining == 0 -> context.getString(R.string.no_tasks_remaining_into_category)
            else -> context.getString(R.string.tasks_remaining).replace("{count}", "$remaining")
        }
    }
}