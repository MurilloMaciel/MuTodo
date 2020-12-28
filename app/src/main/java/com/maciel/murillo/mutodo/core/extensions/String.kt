package com.maciel.murillo.mutodo.core.extensions

import android.annotation.SuppressLint
import android.util.Log
import java.math.BigInteger
import java.security.MessageDigest
import java.text.Normalizer
import java.text.SimpleDateFormat
import java.util.*

fun String?.isEmpty(): Boolean {
    if (this == null) return true
    return length == 0
}

@SuppressLint("SimpleDateFormat")
fun String?.toDate(format: String? = "dd/MM/yyyy"): Date? {
    if (this == null) return null
    return try {
        SimpleDateFormat(format).parse(this)
    } catch (exception: Exception) {
        null
    }
}

fun String.removeDots() = this.replace(".", "")

fun String.extractNumbers() = this.replace(Regex("[^0-9]"), "")

fun String?.applyMask(mask: String): String? {
    if (this == null) return null

    var index = 0
    val masked = StringBuilder()
    for (i in 0 until mask.length) {
        val c = mask[i]
        if (c == '#') {
            masked.append(this[index])
            index++
        } else {
            masked.append(c)
        }
    }
    return masked.toString()

}

fun String?.removeAccents(): String? {
    val regex = "\\p{InCombiningDiacriticalMarks}+".toRegex()
    val normalize = Normalizer.normalize(this, Normalizer.Form.NFD)
    return regex.replace(normalize, "")
}

fun String?.containsIgnoringAccentAndCase(src: String): Boolean? {
    return removeAccents()?.contains(src.removeAccents().safe(), true)
}

fun String.fiilWithDefaultText(digitsToFill: Int, fillChar: String = "X", rightToLeft: Boolean = true): String {
    var aux = digitsToFill
    var filledString = ""
    while ((aux - this.length) > 0) {
        filledString += fillChar
        aux--
    }
    return if (rightToLeft) (filledString + this) else (this + filledString)
}

fun String.log() {
    Log.d("Murillo", this)
}