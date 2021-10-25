package com.eu.nasawiki.utils.ui

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil
import com.eu.nasawiki.post.repository.remote.Model

/**
 * @author Emre UYSAL
 * Difference calculator class for list adapter
 */
class GenericDiffCallback<T : Model> : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T, newItem: T) = oldItem.id == newItem.id

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: T, newItem: T) = oldItem == newItem
}