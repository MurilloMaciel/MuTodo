package com.maciel.murillo.mutodo.core.extensions

import android.os.Build
import android.text.Html
import android.text.Html.FROM_HTML_MODE_LEGACY
import android.text.Spanned

fun fromHtml(source: String): Spanned {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        Html.fromHtml(source, FROM_HTML_MODE_LEGACY)
    } else {
        Html.fromHtml(source)
    }
}
