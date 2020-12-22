package com.maciel.murillo.mutodo.core.databinding

import android.view.View
import androidx.databinding.BindingAdapter

class VisibilityBinding {

    @BindingAdapter("isVisible")
   fun setIsVisible(view: View, isVisible: Boolean) {
        if (isVisible) {
            view.visibility = View.VISIBLE
        } else {
            view.visibility = View.GONE
        }
    }
}