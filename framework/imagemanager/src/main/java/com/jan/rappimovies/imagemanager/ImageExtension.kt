package com.jan.rappimovies.imagemanager

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

fun ImageView.loadUrl(urlString: String, placeholder: Int) {
    Glide.with(context)
        .load(urlString)
        .diskCacheStrategy(DiskCacheStrategy.DATA)
        .centerCrop()
        .placeholder(placeholder)
        .into(this)
}