package com.maciel.murillo.mutodo.core.extensions

import android.view.View
import android.view.animation.TranslateAnimation

/**
 * @author dionata.ferraz
 */

fun View?.visible() {
    this?.visibility = View.VISIBLE
}

fun View?.gone() {
    this?.visibility = View.GONE
}

fun View?.invisible() {
    this?.visibility = View.INVISIBLE
}

fun View?.visibility(isVisible: Boolean) {
    if (isVisible)
        visible()
    else
        gone()
}

fun View?.enable() {
    this?.isEnabled = true
}

fun View?.disable() {
    this?.isEnabled = false
}

fun View.slideToTop(defaultDuration: Long = 500) {
    val animate = TranslateAnimation(0f, 0f, height.toFloat(), 0f).apply {
        duration = defaultDuration
    }
    startAnimation(animate)
    visible()
}
