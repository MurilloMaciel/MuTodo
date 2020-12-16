package com.maciel.murillo.mutodo.core.extensions

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

fun FragmentActivity?.replaceFragment(viewId: Int, fragment: Fragment, stackName: String? = null) {
    if (this == null) return

    fragment.let {
        supportFragmentManager.beginTransaction()
                .replace(viewId, it, it.javaClass.simpleName)
                .addToBackStack(stackName)
                .commitAllowingStateLoss()
    }
}

fun FragmentActivity?.addFragment(viewId: Int, fragment: Fragment) {
    if (this == null) return

    fragment.let {
        supportFragmentManager.beginTransaction()
                .add(viewId, it, it.javaClass.simpleName)
                .commitAllowingStateLoss()
    }
}

fun FragmentActivity?.removeFragment(fragment: Fragment) {
    if (this == null) return

    fragment.let {
        supportFragmentManager.beginTransaction()
                .remove(fragment)
                .commitAllowingStateLoss()
    }
}