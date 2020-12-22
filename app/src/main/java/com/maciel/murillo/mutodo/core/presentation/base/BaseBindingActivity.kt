package com.maciel.murillo.mutodo.core.presentation.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseBindingActivity<B : ViewDataBinding> : AppCompatActivity() {

    abstract val layoutId: Int

    abstract val loadVm: (B) -> Unit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (DataBindingUtil.setContentView(this, layoutId) as? B)?.apply {
            lifecycleOwner = this@BaseBindingActivity
            loadVm(this)
        }

        setUpView()
        setUpViewModels()
        setUpObservers()
    }

    open fun setUpView() {}

    open fun setUpViewModels() {}

    open fun setUpObservers() {}
}
