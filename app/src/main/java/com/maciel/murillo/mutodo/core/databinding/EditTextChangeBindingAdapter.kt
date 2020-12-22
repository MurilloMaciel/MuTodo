package com.maciel.murillo.mutodo.core.databinding

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.databinding.BindingAdapter

class EditTextChangeBindingAdapter {

    @BindingAdapter("onTextChange")
    fun onTextChange(editText: EditText, onTextChange: (String) -> Unit): Unit {

        editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) { }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                onTextChange.invoke(s.toString())
            }

            override fun afterTextChanged(s: Editable?) { }

        })
    }
}