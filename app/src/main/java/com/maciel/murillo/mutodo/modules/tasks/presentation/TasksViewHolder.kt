package com.maciel.murillo.mutodo.modules.tasks.presentation

import android.view.View
import com.chauthai.swipereveallayout.ViewBinderHelper
import com.maciel.murillo.mutodo.core.presentation.base.BaseViewHolder
import com.maciel.murillo.mutodo.modules.tasks.domain.model.Task
import kotlinx.android.synthetic.main.view_tasks.view.*

class TasksViewHolder(
        view: View,
        private val callback: TasksListener,
) : BaseViewHolder<Task>(view) {

    private val viewBinderHelper = ViewBinderHelper()

    init {
        view.cl_edit.setOnClickListener { callback.onClickEdit(adapterPosition) }
        view.cl_delete.setOnClickListener { callback.onClickDelete(adapterPosition) }
    }

    override fun bind(item: Task): Unit = with(itemView) {
        viewBinderHelper.setOpenOnlyOne(true)
        viewBinderHelper.bind(swipe_id, item.id.toString())
        viewBinderHelper.closeLayout(item.id.toString())

//        iv_category.setImageDrawable(ContextCompat.getDrawable(context, item.categoryPresentation.icon))
//        iv_category.setColorFilter(ContextCompat.getColor(context, item.categoryPresentation.backgroundColor))
//        iv_state
        tv_title.text = item.title
        tv_description.text = item.description
    }
}