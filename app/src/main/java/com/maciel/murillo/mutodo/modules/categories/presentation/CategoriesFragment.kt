package com.maciel.murillo.mutodo.modules.categories.presentation

import android.os.Bundle
import android.view.View
import com.maciel.murillo.mutodo.R
import com.maciel.murillo.mutodo.core.presentation.base.BaseBindingFragment
import com.maciel.murillo.mutodo.databinding.FragmentCategoriesBinding
import com.maciel.murillo.mutodo.modules.categories.presentation.model.CategoryPresentation
import kotlinx.android.synthetic.main.fragment_categories.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import androidx.navigation.fragment.findNavController
import com.maciel.murillo.mutodo.core.helper.EventObserver
import com.maciel.murillo.mutodo.modules.categories.presentation.model.CategoriesPresentation
import com.maciel.murillo.mutodo.modules.categories.presentation.model.CategoryTypePresentation

class CategoriesFragment : BaseBindingFragment<FragmentCategoriesBinding>() {

    override val layoutId = R.layout.fragment_categories

    override val loadVm: (FragmentCategoriesBinding) -> Unit = { it.vm = categoriesViewModel }

    private val categoriesViewModel: CategoriesViewModel by viewModel()

    private val categories = CategoryPresentation.getCategories().sortedBy { it.order }

    private val navController by lazy { findNavController() }

    private val categoriesAdapter = CategoriesAdapter(
        categories = categories,
        callback = object : CategoryListener {
            override fun onClickCategory(position: Int) {
                navController.navigate(CategoriesFragmentDirections.goToTasksFrag(categories[position].category.type))
            }
        }
    )

    override fun onResume() {
        super.onResume()

        categoriesViewModel.onInitializeScreen()
    }

    override fun setUpView(view: View, savedInstanceState: Bundle?) {
        super.setUpView(view, savedInstanceState)

        rv_categories.adapter = categoriesAdapter

        setUserNameText()
        setAllTasksText()
    }

    override fun setUpObservers() {
        super.setUpObservers()

        setGoToSettingsObserver()
        setGoToAllTasksObserver()
        setAllTasksCountObserver()
        setWorkTaskCountObserver()
        setStudiesTaskCountObserver()
        setPersonalTaskCountObserver()
        setGymTaskCountObserver()
        setGeneralTaskCountObserver()
        setFunTaskCountObserver()
        setUpdateUserNameObserver()
        setGoToAboutObserver()
    }

    private fun setUpdateUserNameObserver() {
        categoriesViewModel.userName.observe(viewLifecycleOwner, { userName ->
            setUserNameText(userName)
        })
    }

    private fun setGoToAboutObserver() {
        categoriesViewModel.goToAbout.observe(viewLifecycleOwner, EventObserver {
            navController.navigate(CategoriesFragmentDirections.goToAboutFrag())
        })
    }

    private fun setGoToSettingsObserver() {
        categoriesViewModel.goToSettings.observe(viewLifecycleOwner, EventObserver {
            navController.navigate(CategoriesFragmentDirections.goToSettingsFrag())
        })
    }

    private fun setGoToAllTasksObserver() {
        categoriesViewModel.goToAllCategories.observe(viewLifecycleOwner, EventObserver {
            navController.navigate(CategoriesFragmentDirections.goToTasksFrag(CategoryTypePresentation.ALL))
        })
    }

    private fun setAllTasksCountObserver() {
        categoriesViewModel.allTasks.observe(viewLifecycleOwner, { count ->
            setAllTasksText(count)
        })
    }

    private fun setWorkTaskCountObserver() {
        categoriesViewModel.workTaskCount.observe(viewLifecycleOwner, { count ->
            updateCategoryCount(CategoriesPresentation.WORK, count)
        })
    }

    private fun setStudiesTaskCountObserver() {
        categoriesViewModel.studiesTaskCount.observe(viewLifecycleOwner, { count ->
            updateCategoryCount(CategoriesPresentation.STUDIES, count)
        })
    }

    private fun setPersonalTaskCountObserver() {
        categoriesViewModel.personalTaskCount.observe(viewLifecycleOwner, { count ->
            updateCategoryCount(CategoriesPresentation.PERSONAL, count)
        })
    }

    private fun setGymTaskCountObserver() {
        categoriesViewModel.gymTaskCount.observe(viewLifecycleOwner, { count ->
            updateCategoryCount(CategoriesPresentation.GYM, count)
        })
    }

    private fun setGeneralTaskCountObserver() {
        categoriesViewModel.generalTaskCount.observe(viewLifecycleOwner, { count ->
            updateCategoryCount(CategoriesPresentation.GENERAL, count)
        })
    }

    private fun setFunTaskCountObserver() {
        categoriesViewModel.funTaskCount.observe(viewLifecycleOwner, { count ->
            updateCategoryCount(CategoriesPresentation.FUN, count)
        })
    }

    private fun updateCategoryCount(categoryToFind: CategoriesPresentation, taskCount: Int) {
        categories.find { category -> category.category == categoryToFind }?.count = taskCount
        categoriesAdapter.notifyDataSetChanged()
    }

    private fun setUserNameText(userName: String? = null) {
        val userNameGreetings = getString(R.string.welcome).replace("{name}", userName ?: getString(R.string.user))
        categoriesViewModel.onUpdateUserName(userNameGreetings)
    }

    private fun setAllTasksText(allTasks: Int? = null) {
        val allTasksText = allTasks?.run {
            getString(R.string.all_tasks).replace("{count}", "$allTasks")
        } ?: getString(R.string.no_task_remaining)
        categoriesViewModel.onUpdateAllTasksText(allTasksText)
    }
}