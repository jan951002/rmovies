package com.jan.rappimovies.textmanager

import android.text.Editable
import android.text.TextWatcher

fun createTextWatcher(onAfterText: (text: String) -> Unit) = object : TextWatcher {
    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
    }

    override fun afterTextChanged(s: Editable?) {
        onAfterText.invoke(s.toString())
    }
}