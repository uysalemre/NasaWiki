package com.eu.citizenmecase.post.view.adapter

import com.eu.citizenmecase.R
import com.eu.citizenmecase.base.BaseAdapter
import com.eu.citizenmecase.databinding.ItemCommentListBinding
import com.eu.citizenmecase.post.repository.remote.CommentModel
import dagger.hilt.android.scopes.FragmentScoped
import javax.inject.Inject

@FragmentScoped
class CommentListAdapter @Inject constructor() :
    BaseAdapter<CommentModel, ItemCommentListBinding>(
        R.layout.item_comment_list,
        { item, holder ->
            with(holder.binding) {
                name = item.name
                body = item.body
            }
        })