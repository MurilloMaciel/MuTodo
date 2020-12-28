package com.maciel.murillo.mutodo.modules.tasks.presentation

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.maciel.murillo.mutodo.R
import com.maciel.murillo.mutodo.core.extensions.visibility
import com.maciel.murillo.mutodo.core.helper.EventObserver
import com.maciel.murillo.mutodo.core.presentation.base.BaseBindingFragment
import com.maciel.murillo.mutodo.databinding.FragmentTasksBinding
import com.maciel.murillo.mutodo.modules.categories.presentation.model.CategoryTypePresentation
import com.maciel.murillo.mutodo.modules.categories.presentation.model.getCategoryNameResource
import com.maciel.murillo.mutodo.modules.categories.presentation.model.mapToCategoryType
import kotlinx.android.synthetic.main.fragment_tasks.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class TasksFragment : BaseBindingFragment<FragmentTasksBinding>() {

    override val layoutId = R.layout.fragment_tasks

    override val loadVm: (FragmentTasksBinding) -> Unit = { it.vm = tasksViewModel }

    private val tasksViewModel: TasksViewModel by viewModel()

    private val arguments: TasksFragmentArgs by navArgs()
    private val taskCategoryType: CategoryTypePresentation by lazy { arguments.category }

    private val navController by lazy { findNavController() }

    private lateinit var tasksAdapter: TasksAdapter

    override fun onResume() {
        super.onResume()

        tasksViewModel.onInitializeScreen(taskCategoryType.mapToCategoryType())
    }

    override fun setUpView(view: View, savedInstanceState: Bundle?) {
        super.setUpView(view, savedInstanceState)

        tasksAdapter = TasksAdapter(
            tasks = tasksViewModel.tasks,
            callback = object : TasksListener {
                override fun onClickEdit(position: Int) = tasksViewModel.onClickEditTask(position)
                override fun onClickDelete(position: Int) = tasksViewModel.onClickDeleteTask(position)
            }
        )

        rv_tasks.adapter = tasksAdapter

        tv_category.text = getString(taskCategoryType.getCategoryNameResource())
    }

    override fun setUpObservers() {
        super.setUpObservers()

        setUpdateTasksObserver()
        setGoBackObserver()
        setAddTaskObserver()
    }

    private fun setUpdateTasksObserver() {
        tasksViewModel.updateTasks.observe(viewLifecycleOwner, EventObserver {
            tasksAdapter.setTasks(tasksViewModel.tasks)
        })
    }

    private fun setGoBackObserver() {
        tasksViewModel.goBack.observe(viewLifecycleOwner, EventObserver {
            activity?.onBackPressed()
        })
    }

    private fun setAddTaskObserver() {
        tasksViewModel.addOrUpdateTask.observe(viewLifecycleOwner, EventObserver { taskId ->
            navController.navigate(TasksFragmentDirections.goToAddTaskFrag(taskCategoryType, taskId))
        })
    }
}