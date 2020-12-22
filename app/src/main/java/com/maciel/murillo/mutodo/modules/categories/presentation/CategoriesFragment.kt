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
                navController.navigate(CategoriesFragmentDirections.goToTasksFrag(categories[position].categoryPresentation.type))
            }
        }
    )

    override fun setUpView(view: View, savedInstanceState: Bundle?) {
        super.setUpView(view, savedInstanceState)

        setUpCategoriesRecyclerView()

        tv_hello.text = getString(R.string.welcome).replace("{name}", "name")
        tv_all_tasks_remaining.text = getString(R.string.all_tasks_remaining).replace("{count}", categoriesViewModel.allTasksRemaining.value.toString())
    }

    override fun setUpObservers() {
        super.setUpObservers()

    }


    private fun setUpCategoriesRecyclerView() = with(rv_categories) {
        adapter = categoriesAdapter
    }
}