package com.eu.nasawiki.post.view.adapter

import com.eu.nasawiki.R
import com.eu.nasawiki.base.BaseAdapter
import com.eu.nasawiki.databinding.ItemEmptyBinding
import com.eu.nasawiki.databinding.ItemPostListBinding
import com.eu.nasawiki.post.repository.remote.PostModel

/**
 * @author Emre UYSAL
 * Adapter class for posts in main page
 */
class PostListAdapter(
    var onClickItem: (id: Long) -> Unit,
    var onClickRetry: () -> Unit
) :
    BaseAdapter<PostModel>(
        R.layout.item_post_list,
        R.layout.item_empty,
        { item, contentHolder ->
            with(contentHolder.binding as ItemPostListBinding) {
                imageUrl = item.url
                title = item.title
                summary = item.summary
                isFav = item.isFav
                root.setOnClickListener {
                    onClickItem(item.id)
                }
            }
        },
        { emptyHolder ->
            with(emptyHolder.binding as ItemEmptyBinding) {
                pageName = "post"
                btTryAgainEmpty.setOnClickListener {
                    onClickRetry()
                }
            }
        }
    )