package com.maciel.murillo.mutodo.core.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView?.loadImageFromGlide(stringUrl: String?) {
    if (this == null) return
    Glide.with(context)
        .load(stringUrl)
        .dontAnimate()
        .into(this)
}
