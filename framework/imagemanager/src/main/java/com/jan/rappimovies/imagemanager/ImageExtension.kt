package com.jan.rappimovies.imagemanager

import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.RoundedCorners

import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.FitCenter

import com.bumptech.glide.request.RequestOptions


fun ImageView.loadUrl(urlString: String, placeholder: Int) {
    Glide.with(context)
        .load(urlString)
        .diskCacheStrategy(DiskCacheStrategy.DATA)
        .centerCrop()
        .placeholder(placeholder)
        .into(this)
}

fun ImageView.loadUrlCircle(urlString: String, placeholder: Int) {
    Glide.with(context)
        .load(urlString)
        .diskCacheStrategy(DiskCacheStrategy.DATA)
        .apply(RequestOptions().transform(FitCenter(), RoundedCorners(40)))
        .placeholder(placeholder)
        .into(this)
}

fun ImageView.loadDrawable(@DrawableRes drawableRes: Int) {
    setImageDrawable(ContextCompat.getDrawable(context, drawableRes))
}