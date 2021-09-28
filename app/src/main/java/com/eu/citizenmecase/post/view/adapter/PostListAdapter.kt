package com.eu.citizenmecase.post.view.adapter

import com.eu.citizenmecase.R
import com.eu.citizenmecase.base.BaseAdapter
import com.eu.citizenmecase.databinding.ItemPostListBinding
import com.eu.citizenmecase.post.repository.remote.PostModel
import com.eu.citizenmecase.utils.ui.GenericViewHolder
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @author Emre UYSAL
 * Adapter class for posts in main page
 */
@Singleton
class PostListAdapter @Inject constructor() :
    BaseAdapter<PostModel, ItemPostListBinding>(
        R.layout.item_post_list,
        { item, holder ->
            with(holder.binding) {
                imageUrl = item.thumbnailUrl
                title = item.title
                body = item.body
            }
        }) {
    lateinit var itemClick: (id: Long, body: String) -> Unit

    override fun onBindViewHolder(holder: GenericViewHolder<ItemPostListBinding>, position: Int) {
        super.onBindViewHolder(holder, position)
        val item = getItem(position)
        holder.binding.root.setOnClickListener {
            itemClick(item.id, item.body)
        }
    }
}