package com.maciel.murillo.mutodo.core.extensions

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.TypedValue
import android.view.View
import android.view.inputmethod.InputMethodManager

fun Context?.hideKeyboard(view: View) {
    if (this == null) return

    val imm = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(view.windowToken, 0)
}

fun Context?.getSettingsUri(): Uri? {
    if (this == null) return null

    return Uri.fromParts("package", packageName, null)
}

fun Context.toDP(valueInPx: Int): Int {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, valueInPx.toFloat(), resources.displayMetrics).toInt()
}

fun Context.toPx(valueInDP: Int): Int {
    return resources?.displayMetrics?.let {
        (it.density * valueInDP).toInt()
    } ?: 0
}

fun Context?.openURL(strUri: String) {
    if (this == null) return

    startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(strUri)))
}
