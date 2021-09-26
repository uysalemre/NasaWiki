package com.eu.citizenmecase.post.view.adapter

import com.eu.citizenmecase.R
import com.eu.citizenmecase.base.BaseAdapter
import com.eu.citizenmecase.databinding.ItemPostListBinding
import com.eu.citizenmecase.post.repository.remote.PostModel

class PostListAdapter :
    BaseAdapter<PostModel, ItemPostListBinding>(
        R.layout.item_post_list,
        { item, holder ->
            with(holder.binding) {
                imageUrl = item.thumbnailUrl
                title = item.title
                body = item.body
            }
        })