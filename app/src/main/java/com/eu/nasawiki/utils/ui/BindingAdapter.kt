package com.eu.nasawiki.utils.ui

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS
import androidx.recyclerview.widget.StaggeredGridLayoutManager.VERTICAL
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.eu.nasawiki.R
import com.eu.nasawiki.base.BaseViewModel
import com.eu.nasawiki.post.repository.remote.CommentModel
import com.eu.nasawiki.post.repository.remote.PhotoModel
import com.eu.nasawiki.post.repository.remote.PostModel
import com.eu.nasawiki.post.view.adapter.CommentListAdapter
import com.eu.nasawiki.post.view.adapter.PostListAdapter
import com.eu.nasawiki.post.viewmodel.PostDetailFragmentViewModel
import com.eu.nasawiki.post.viewmodel.PostListFragmentViewModel

/**
 * @author Emre UYSAL
 * File that contains binding adapters for DataBinding views
 */
@BindingAdapter("loadImage")
fun loadImage(view: ImageView, photo: Any?) {
    Glide.with(view.context)
        .load(
            when (photo) {
                is PhotoModel -> photo.url
                else -> photo
            }
        )
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .error(R.drawable.ic_image_error)
        .placeholder(R.drawable.ic_image_background)
        .into(view)
}

@BindingAdapter("setSpanCount")
fun setSpanCount(
    view: RecyclerView,
    spanCount: Int,
) {
    view.layoutManager = StaggeredGridLayoutManager(spanCount, VERTICAL).apply {
        gapStrategy = GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS
        invalidateSpanAssignments()
    }
}

@BindingAdapter(value = ["adapterVm", "data", "postId"], requireAll = false)
fun setAdapter(view: RecyclerView, viewModel: Any, data: List<Any>?, postId: Long?) {
    if (view.adapter == null) {
        view.adapter = when (viewModel) {
            is PostListFragmentViewModel -> {
                PostListAdapter(
                    { id -> viewModel.onListItemClick(id) },
                    { viewModel.swipeAndRefresh() }
                )
            }
            is PostDetailFragmentViewModel -> {
                CommentListAdapter { viewModel.fetchComments(postId!!) }
            }
            else -> throw IllegalArgumentException("Invalid Adapter")
        }
    }
    data?.let {
        when (val adapter = view.adapter) {
            is PostListAdapter -> adapter.submitList(data as List<PostModel>)
            is CommentListAdapter -> {
                adapter.submitList(data as List<CommentModel>)
            }
            else -> throw IllegalArgumentException("Invalid Adapter")
        }
    }
}

@BindingAdapter("setRefreshStatus")
fun setRefreshStatus(
    view: SwipeRefreshLayout,
    status: Boolean
) {
    view.isRefreshing = status
}

@BindingAdapter("onRefresh")
fun onRefresh(
    view: SwipeRefreshLayout,
    viewModel: BaseViewModel
) {
    when (viewModel) {
        is PostListFragmentViewModel -> view.setOnRefreshListener {
            viewModel.swipeAndRefresh()
            viewModel.fetchPosts()
        }
        else -> throw IllegalArgumentException("Invalid ViewModel")
    }
}