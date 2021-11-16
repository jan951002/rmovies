package com.jan.rappimovies.adaptermanager

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.viewbinding.ViewBinding

typealias Inflate<T> = (LayoutInflater, ViewGroup?, Boolean) -> T

inline fun <T> basicDiffUtil(
    crossinline areContentsTheSame: (T, T) -> Boolean,
    crossinline areItemsTheSame: (T, T) -> Boolean
): DiffUtil.ItemCallback<T> = object : DiffUtil.ItemCallback<T>() {

    override fun areItemsTheSame(oldItem: T, newItem: T) = areItemsTheSame(oldItem, newItem)

    override fun areContentsTheSame(oldItem: T, newItem: T) =
        areContentsTheSame(oldItem, newItem)
}

fun <Binding : ViewBinding> ViewGroup.inflate(inflate: Inflate<Binding>): Binding {
    val layoutInflater = LayoutInflater.from(context)
    return inflate(layoutInflater, this, false)
}