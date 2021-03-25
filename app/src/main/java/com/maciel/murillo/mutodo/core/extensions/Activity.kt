package com.maciel.murillo.mutodo.core.extensions

import android.app.Activity
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.View
import android.view.WindowManager

fun Activity?.setAsFullScreen() {
    if (this == null) return
    window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
}

fun Activity.startActivityAndFinish(intent: Intent) {
    startActivity(intent)
    finish()
}

fun Activity?.setResultAndFinish(result: Int, intent: Intent? = null) {
    if (this == null) return
    setResult(result, intent)
    finish()
}

fun Activity.hideKeyboard() {
    if (currentFocus == null) View(this) else currentFocus?.let { hideKeyboard(it) }
}