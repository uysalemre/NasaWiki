package com.eu.citizenmecase.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.eu.citizenmecase.R

@BindingAdapter("loadImage")
fun loadImage(view: ImageView, url: String) {
    // todo change placeholder later
    Glide.with(view.context)
        .load(url)
        .placeholder(R.drawable.ic_launcher_background)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .into(view)
}