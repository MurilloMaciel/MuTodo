package com.maciel.murillo.mutodo.core.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.maciel.murillo.mutodo.core.extensions.hideKeyboard

abstract class BaseBindingFragment<B : ViewDataBinding> : Fragment() {

    abstract val layoutId: Int
    abstract val loadVm: (B) -> Unit

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
        (DataBindingUtil.inflate(inflater, layoutId, container, false) as? B)?.apply {
            lifecycleOwner = viewLifecycleOwner
            loadVm(this)
        }?.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpViewModels()
        setUpView(view, savedInstanceState)
        setUpObservers()
    }

    open fun setUpView(view: View, savedInstanceState: Bundle?) {}

    open fun setUpViewModels() {}

    open fun setUpObservers() {}

    fun hideKeyboard() = activity?.hideKeyboard()
}