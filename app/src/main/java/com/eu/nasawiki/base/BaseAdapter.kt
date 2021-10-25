package com.eu.nasawiki.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.eu.nasawiki.post.repository.remote.Model
import com.eu.nasawiki.utils.ui.GenericDiffCallback
import com.eu.nasawiki.utils.ui.GenericViewHolder

/**
 * @author Emre UYSAL
 * Base adapter class that creates and manages ListAdapters
 * Creates DataBinding for view and bind using function passed from constructor
 */
abstract class BaseAdapter<T : Model>(
    @LayoutRes private val layoutId: Int,
    @LayoutRes private val emptyLayoutId: Int,
    inline val bindContent: (
        item: T,
        contentHolder: GenericViewHolder
    ) -> Unit,
    inline val bindEmpty: (
        emptyHolder: GenericViewHolder
    ) -> Unit
) : ListAdapter<T, GenericViewHolder>(GenericDiffCallback<T>()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): GenericViewHolder =
        when (viewType) {
            0 -> getViewHolder(parent, emptyLayoutId)
            1 -> getViewHolder(parent, layoutId)
            else -> throw IllegalArgumentException("Invalid ViewType")
        }

    override fun onBindViewHolder(holder: GenericViewHolder, position: Int) {
        if (position != RecyclerView.NO_POSITION) {
            when (holder.itemViewType) {
                0 -> bindEmpty(holder)
                1 -> bindContent(getItem(position), holder)
                else -> throw IllegalArgumentException("Invalid ViewHolder")
            }
        }
    }

    override fun getItemViewType(position: Int) = when {
        currentList.isNullOrEmpty() -> 0
        else -> 1
    }

    override fun onCurrentListChanged(previousList: MutableList<T>, currentList: MutableList<T>) {
        super.onCurrentListChanged(previousList, currentList)
    }

    private fun getViewHolder(parent: ViewGroup, layoutId: Int) = GenericViewHolder(
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            layoutId,
            parent,
            false
        )
    )
}




