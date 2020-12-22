package com.maciel.murillo.mutodo.modules

import com.maciel.murillo.mutodo.R
import com.maciel.murillo.mutodo.core.presentation.base.BaseBindingActivity
import com.maciel.murillo.mutodo.databinding.ActivityRootBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class RootActivity : BaseBindingActivity<ActivityRootBinding>() {

    override val layoutId = R.layout.activity_root

    override val loadVm: (ActivityRootBinding) -> Unit = { binding ->
        binding.vm = rootViewModel
    }

    private val rootViewModel by lazy { RootViewModel() }
}