package com.eu.nasawiki.utils.ui

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

/**
 * @author Emre UYSAL
 * Generic view holder class for DataBinding and RecyclerView
 */
class GenericViewHolder(val binding: ViewDataBinding) :
    RecyclerView.ViewHolder(binding.root)