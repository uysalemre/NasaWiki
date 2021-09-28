package com.eu.citizenmecase.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.ListAdapter
import com.eu.citizenmecase.post.repository.remote.Model
import com.eu.citizenmecase.utils.ui.GenericDiffCallback
import com.eu.citizenmecase.utils.ui.GenericViewHolder

/**
 * @author Emre UYSAL
 * Base adapter class that creates and manages ListAdapters
 * Creates DataBinding for view and bind using function passed from constructor
 */
abstract class BaseAdapter<T : Model, VB : ViewDataBinding>(
    @LayoutRes private val layoutId: Int,
    inline val bind: (item: T, holder: GenericViewHolder<VB>) -> Unit
) : ListAdapter<T, GenericViewHolder<VB>>(GenericDiffCallback<T>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenericViewHolder<VB> {
        val binding: VB =
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                layoutId,
                parent,
                false
            )
        return GenericViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GenericViewHolder<VB>, position: Int) =
        bind(getItem(position), holder)
}




