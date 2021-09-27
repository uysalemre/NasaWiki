package com.eu.citizenmecase.utils.ui

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS
import androidx.recyclerview.widget.StaggeredGridLayoutManager.VERTICAL
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.eu.citizenmecase.R
import com.eu.citizenmecase.post.view.adapter.CommentListAdapter
import com.eu.citizenmecase.post.view.adapter.PostListAdapter


@BindingAdapter("loadImage")
fun loadImage(view: ImageView, url: String?) {
    // todo change placeholder later
    Glide.with(view.context)
        .load(url)
        .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
        .placeholder(R.drawable.ic_launcher_background)
        .into(view)
}

@BindingAdapter(value = ["spanCount", "adapter", "itemClickListener"], requireAll = true)
fun setRecyclerViewStaggered(
    view: RecyclerView,
    spanCount: Int,
    adapter: PostListAdapter,
    itemClickListener: RecyclerViewItemClickListener
) {
    view.layoutManager = StaggeredGridLayoutManager(spanCount, VERTICAL).apply {
        gapStrategy = GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS
        invalidateSpanAssignments()
    }
    adapter.itemClickListener = itemClickListener
    view.adapter = adapter
    view.addItemDecoration(GridSpacingItemDecoration(spanCount, 20, true, 0, false))
}

@BindingAdapter("setLinearRvAdapter")
fun setLinearRvAdapter(view: RecyclerView, adapter: CommentListAdapter) {
    view.layoutManager = LinearLayoutManager(view.context)
    view.adapter = adapter
}