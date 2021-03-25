package com.maciel.murillo.mutodo.modules.tasks.presentation.addtask

import android.text.TextWatcher
import androidx.navigation.fragment.navArgs
import com.maciel.murillo.mutodo.R
import com.maciel.murillo.mutodo.core.extensions.getDateToString
import com.maciel.murillo.mutodo.core.extensions.getTimeToString
import com.maciel.murillo.mutodo.core.extensions.newTextWatcher
import com.maciel.murillo.mutodo.core.helper.EventObserver
import com.maciel.murillo.mutodo.core.presentation.base.BaseBindingFragment
import com.maciel.murillo.mutodo.core.presentation.dialog.DateSelectorCallback
import com.maciel.murillo.mutodo.core.presentation.dialog.DateSelectorDialog
import com.maciel.murillo.mutodo.core.presentation.dialog.TimeSelectorCallback
import com.maciel.murillo.mutodo.core.presentation.dialog.TimeSelectorDialog
import com.maciel.murillo.mutodo.databinding.FragmentAddTaskBinding
import com.maciel.murillo.mutodo.modules.categories.presentation.model.CategoryTypePresentation
import com.maciel.murillo.mutodo.modules.categories.presentation.model.mapToCategoryType
import kotlinx.android.synthetic.main.fragment_add_task.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class AddTaskFragment : BaseBindingFragment<FragmentAddTaskBinding>() {

    override val layoutId: Int = R.layout.fragment_add_task

    override val loadVm: (FragmentAddTaskBinding) -> Unit = { it.vm = addTaskViewModel }

    private val addTaskViewModel: AddTaskViewModel  by viewModel()

    private val arguments: AddTaskFragmentArgs by navArgs()
    private val taskCategoryType: CategoryTypePresentation by lazy { arguments.category }
    private val taskToEditId: Long by lazy { arguments.taskId }

    private val titleTextWatcher: TextWatcher = newTextWatcher(onTextChanged = { cs, _, _, _ -> onChangeTitle(cs.toString()) })
    private val descriptionTextWatcher: TextWatcher = newTextWatcher(onTextChanged = { cs, _, _, _ -> onChangeDescription(cs.toString()) })

    override fun onResume() {
        super.onResume()

        addTaskViewModel.onInitializeScreen(
            taskCategoryType.mapToCategoryType(),
            if (taskToEditId < 0) null else taskToEditId,
            Calendar.getInstance().getDateToString(),
            Calendar.getInstance().getTimeToString()
        )
    }

    override fun setUpListeners() {
        super.setUpListeners()

        iv_back.setOnClickListener { activity?.onBackPressed() }

        et_task_title.addTextChangedListener(titleTextWatcher)
        et_task_description.addTextChangedListener(descriptionTextWatcher)
    }

    override fun setUpObservers() {
        super.setUpObservers()

        setTaskInsertFinishObserver()
        setPickDateObserver()
        setPickTimeObserver()
        setGoBackObserver()
    }

    private fun setTaskInsertFinishObserver() {
        addTaskViewModel.taskInsertFinish.observe(viewLifecycleOwner, EventObserver {
            activity?.onBackPressed()
        })
    }

    private fun setPickDateObserver() {
        addTaskViewModel.pickDate.observe(viewLifecycleOwner, EventObserver { date ->
            DateSelectorDialog.show(childFragmentManager, date, object : DateSelectorCallback {
                override fun onDateSet(year: Int, month: Int, day: Int) {
                    addTaskViewModel.onAlarmDateSet(year, month, day)
                }
            })
        })
    }

    private fun setPickTimeObserver() {
        addTaskViewModel.pickTime.observe(viewLifecycleOwner, EventObserver { time ->
            TimeSelectorDialog.show(childFragmentManager, time, object : TimeSelectorCallback {
                override fun onTimeSet(hourOfDay: Int, minute: Int) {
                    addTaskViewModel.onAlarmTimeSet(hourOfDay, minute)
                }
            })
        })
    }

    private fun setGoBackObserver() {
        addTaskViewModel.goBack.observe(viewLifecycleOwner, EventObserver {
            activity?.onBackPressed()
        })
    }

    private fun onChangeTitle(title: String) = addTaskViewModel.onChangeTitle(title)

    private fun onChangeDescription(description: String) = addTaskViewModel.onChangeDescription(description)
}