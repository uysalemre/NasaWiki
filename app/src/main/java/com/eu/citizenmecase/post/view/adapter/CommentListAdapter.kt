package com.eu.citizenmecase.post.view.adapter

import com.eu.citizenmecase.R
import com.eu.citizenmecase.base.BaseAdapter
import com.eu.citizenmecase.databinding.ItemCommentListBinding
import com.eu.citizenmecase.post.repository.remote.CommentModel
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @author Emre UYSAL
 * Adapter class for comments in detail page
 */
@Singleton
class CommentListAdapter @Inject constructor() :
    BaseAdapter<CommentModel, ItemCommentListBinding>(
        R.layout.item_comment_list,
        { item, holder ->
            with(holder.binding) {
                name = item.name
                body = item.body
            }
        })