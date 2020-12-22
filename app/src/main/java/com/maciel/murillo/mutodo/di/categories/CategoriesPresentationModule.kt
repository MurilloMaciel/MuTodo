package com.maciel.murillo.mutodo.di.categories

import com.maciel.murillo.mutodo.modules.categories.presentation.CategoriesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val categoriesPresentationModule = module {

    viewModel { CategoriesViewModel() }
}