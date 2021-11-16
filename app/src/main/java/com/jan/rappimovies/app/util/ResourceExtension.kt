package com.jan.rappimovies.app.util

import android.content.Context

import android.util.TypedValue
import com.jan.rappimovies.app.R

fun Context.getPrimaryColor(): Int {
    val typedValue = TypedValue()
    theme.resolveAttribute(R.attr.colorPrimary, typedValue, true)
    return typedValue.data
}