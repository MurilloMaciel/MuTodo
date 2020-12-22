package com.maciel.murillo.mutodo.modules.settings.presentation

import com.maciel.murillo.mutodo.R
import com.maciel.murillo.mutodo.core.presentation.base.BaseBindingFragment
import com.maciel.murillo.mutodo.databinding.FragmentSettingsBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class SettingsFragment : BaseBindingFragment<FragmentSettingsBinding>() {

    override val layoutId = R.layout.fragment_settings

    override val loadVm: (FragmentSettingsBinding) -> Unit = { it.vm = settingsViewModel }

    private val settingsViewModel: SettingsViewModel by viewModel()

}