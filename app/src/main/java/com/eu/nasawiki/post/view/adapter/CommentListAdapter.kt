package com.eu.nasawiki.post.view.adapter

import com.eu.nasawiki.R
import com.eu.nasawiki.base.BaseAdapter
import com.eu.nasawiki.databinding.ItemCommentListBinding
import com.eu.nasawiki.databinding.ItemEmptyBinding
import com.eu.nasawiki.post.repository.remote.CommentModel

/**
 * @author Emre UYSAL
 * Adapter class for comments in detail page
 */
class CommentListAdapter(val onClickRetry: () -> Unit) :
    BaseAdapter<CommentModel>(
        R.layout.item_comment_list,
        R.layout.item_empty,
        { item, holder ->
            with(holder.binding as ItemCommentListBinding) {
                name = item.name
                body = item.body
            }
        },
        { emptyHolder ->
            with(emptyHolder.binding as ItemEmptyBinding) {
                pageName = "comments"
                btTryAgainEmpty.setOnClickListener {
                    onClickRetry()
                }
            }
        })