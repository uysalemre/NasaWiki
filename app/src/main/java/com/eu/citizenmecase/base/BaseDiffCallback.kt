package com.eu.citizenmecase.base

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil
import com.eu.citizenmecase.post.repository.remote.Model

class BaseDiffCallback<T : Model> : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T, newItem: T) = oldItem.id == newItem.id

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: T, newItem: T) = oldItem == newItem
}