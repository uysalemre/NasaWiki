package com.eu.citizenmecase.utils.ui

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS
import androidx.recyclerview.widget.StaggeredGridLayoutManager.VERTICAL
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.eu.citizenmecase.R
import com.eu.citizenmecase.post.view.adapter.CommentListAdapter
import com.eu.citizenmecase.post.view.adapter.PostListAdapter

/**
 * @author Emre UYSAL
 * File that contains binding adapters for DataBinding views
 */
@BindingAdapter("loadImage")
fun loadImage(view: ImageView, url: String?) {
    Glide.with(view.context)
        .load(url)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .error(R.drawable.ic_image_error)
        .placeholder(R.drawable.ic_image_background)
        .into(view)
}

@BindingAdapter(value = ["spanCount", "adapter"], requireAll = true)
fun setRecyclerViewStaggered(
    view: RecyclerView,
    spanCount: Int,
    adapter: PostListAdapter
) {
    view.layoutManager = StaggeredGridLayoutManager(spanCount, VERTICAL).apply {
        gapStrategy = GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS
        invalidateSpanAssignments()
    }
    view.adapter = adapter
    view.addItemDecoration(GridSpacingItemDecoration(spanCount, 20, true, 0, false))
}

@BindingAdapter("setLinearRvAdapter")
fun setLinearRvAdapter(view: RecyclerView, adapter: CommentListAdapter) {
    view.layoutManager = LinearLayoutManager(view.context)
    view.adapter = adapter
}

@BindingAdapter("setRefreshStatus")
fun setRefreshStatus(
    view: SwipeRefreshLayout,
    status: Boolean
) {
    view.isRefreshing = status
}

@BindingAdapter("onRefresh")
fun setRefreshStatus(
    view: SwipeRefreshLayout,
    onRefresh: SwipeRefreshLayout.OnRefreshListener
) {
    view.setOnRefreshListener(onRefresh)
}