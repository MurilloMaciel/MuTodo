package com.maciel.murillo.mutodo.modules.about.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.maciel.murillo.mutodo.BuildConfig
import com.maciel.murillo.mutodo.R
import kotlinx.android.synthetic.main.fragment_about.view.*

class AboutFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_about, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.iv_back.setOnClickListener { activity?.onBackPressed() }

        view.tv_version_recovered.text = BuildConfig.VERSION_NAME
    }
}