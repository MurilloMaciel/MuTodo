package com.maciel.murillo.mutodo.modules.tasks.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.maciel.murillo.mutodo.R
import com.maciel.murillo.mutodo.modules.tasks.domain.model.Task

class TasksAdapter(
    private var tasks: List<Task>,
    private val callback: TasksListener,
) : RecyclerView.Adapter<TasksViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TasksViewHolder {
        val view = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.view_tasks, parent, false)
        return TasksViewHolder(view, callback)
    }

    override fun onBindViewHolder(holder: TasksViewHolder, position: Int) {
        holder.bind(tasks[position])
    }

    override fun getItemCount(): Int = tasks.size

    fun setTasks(tasks: List<Task>) {
        this.tasks = tasks
        notifyDataSetChanged()
    }
}