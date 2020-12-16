package com.maciel.murillo.mutodo.core.extensions

import androidx.fragment.app.Fragment
import com.maciel.murillo.mutodo.core.extensions.hideKeyboard

fun Fragment?.addFragment(viewId: Int, fragment: Fragment) {
    if (this == null) return

    childFragmentManager.beginTransaction()
            .add(viewId, fragment)
            .commit()
}

fun Fragment?.hideKeyboard() {
    this?.activity?.hideKeyboard()
}