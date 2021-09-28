package com.eu.citizenmecase.utils.ui

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

/**
 * @author Emre UYSAL
 * Generic view holder class for DataBinding and RecyclerView
 */
class GenericViewHolder<VB : ViewDataBinding>(val binding: VB) :
    RecyclerView.ViewHolder(binding.root)