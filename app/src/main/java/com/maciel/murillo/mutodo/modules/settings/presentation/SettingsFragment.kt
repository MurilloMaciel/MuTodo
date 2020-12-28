package com.maciel.murillo.mutodo.modules.settings.presentation

import android.text.TextWatcher
import com.maciel.murillo.mutodo.R
import com.maciel.murillo.mutodo.core.extensions.newTextWatcher
import com.maciel.murillo.mutodo.core.helper.EventObserver
import com.maciel.murillo.mutodo.core.presentation.base.BaseBindingFragment
import com.maciel.murillo.mutodo.databinding.FragmentSettingsBinding
import kotlinx.android.synthetic.main.fragment_settings.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class SettingsFragment : BaseBindingFragment<FragmentSettingsBinding>() {

    override val layoutId = R.layout.fragment_settings

    override val loadVm: (FragmentSettingsBinding) -> Unit = { it.vm = settingsViewModel }

    private val settingsViewModel: SettingsViewModel by viewModel()

    private val userNameTextWatcher: TextWatcher = newTextWatcher(onTextChanged = { cs, _, _, _ -> onChangeUserName(cs.toString()) })

    override fun onResume() {
        super.onResume()

        settingsViewModel.onInitializeScreen()
    }

    override fun setUpObservers() {
        super.setUpObservers()

        setGoBackObserver()
    }

    private fun setGoBackObserver() {
        settingsViewModel.goBack.observe(viewLifecycleOwner, EventObserver {
            activity?.onBackPressed()
        })
    }

    override fun setUpListeners() {
        super.setUpListeners()

        et_user_name.addTextChangedListener(userNameTextWatcher)
    }

    private fun onChangeUserName(userName: String) = settingsViewModel.onChangeUserName(userName)
}