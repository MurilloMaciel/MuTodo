package com.maciel.murillo.mutodo.modules.tasks.presentation

import android.graphics.drawable.Drawable
import android.view.View
import androidx.core.content.ContextCompat
import com.chauthai.swipereveallayout.ViewBinderHelper
import com.maciel.murillo.mutodo.R
import com.maciel.murillo.mutodo.core.extensions.*
import com.maciel.murillo.mutodo.core.presentation.base.BaseViewHolder
import com.maciel.murillo.mutodo.modules.tasks.domain.model.Task
import kotlinx.android.synthetic.main.view_tasks.view.*
import java.util.*

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

        iv_state.setImageDrawable(getIcon(item.alarm?.dateTime))
        tv_title.text = item.title
        tv_description.text = item.description

        tv_schedule_time.text = if (item.alarm.isNull()) {
            itemView.context.getString(R.string.no_alarm_scheduled)
        } else {
            item.alarm?.dateTime?.getDateToString() + " - " + item.alarm?.dateTime?.getTimeToString()
        }
    }

    private fun getIcon(calendar: Calendar?): Drawable? = with(calendar) {
        when {
            this == null -> ContextCompat.getDrawable(context, R.drawable.ic_notifications_off_36)
            isBeforeNow() -> ContextCompat.getDrawable(context, R.drawable.ic_check_36)
            hasLessThenOneHourFromNow() -> ContextCompat.getDrawable(context, R.drawable.ic_unchecked_red_36)
            hasLessThenHalfDayFromNow() -> ContextCompat.getDrawable(context, R.drawable.ic_unchecked_orange_36)
            hasLessThenOneDayFromNow() -> ContextCompat.getDrawable(context, R.drawable.ic_unchecked_yellow_36)
            else -> ContextCompat.getDrawable(context, R.drawable.ic_unchecked_blue_36)
        }
    }
}