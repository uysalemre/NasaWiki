package com.eu.citizenmecase.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.ListAdapter
import com.eu.citizenmecase.post.repository.remote.Model

abstract class BaseAdapter<T : Model, VB : ViewDataBinding>(
    @LayoutRes private val layoutId: Int,
    inline val bind: (item: T, holder: BaseViewHolder<VB>) -> Unit
) : ListAdapter<T, BaseViewHolder<VB>>(BaseDiffCallback<T>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<VB> {
        val binding: VB =
            DataBindingUtil.inflate(LayoutInflater.from(parent.context), layoutId, parent, false)
        return BaseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<VB>, position: Int) =
        bind(getItem(position), holder)
}




