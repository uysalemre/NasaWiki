package com.eu.citizenmecase.post.view

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.eu.citizenmecase.R
import com.eu.citizenmecase.base.BaseFragment
import com.eu.citizenmecase.databinding.FragmentPostDetailBinding
import com.eu.citizenmecase.post.view.adapter.CommentListAdapter
import com.eu.citizenmecase.post.viewmodel.PostDetailFragmentViewModel
import com.eu.citizenmecase.utils.network.NetworkResult
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PostDetailFragment :
    BaseFragment<FragmentPostDetailBinding>(R.layout.fragment_post_detail) {
    private val viewModel: PostDetailFragmentViewModel by viewModels()
    private lateinit var adapter: CommentListAdapter
    private val args: PostDetailFragmentArgs by navArgs()

    override fun onViewCreationCompleted() {
        initializeView()
        initializeObserver()
        fetchDetails()
    }

    private fun initializeView() {
        adapter = CommentListAdapter()
        with(binding) {
            rvAdapter = adapter
            viewmodel = viewModel
        }
    }

    private fun initializeObserver() {
        viewModel.photo.observe(viewLifecycleOwner, {
            when (it) {
                is NetworkResult.OnLoading -> {
                }
                is NetworkResult.OnFailure -> {
                }
                is NetworkResult.OnUnexpected -> {
                }
                is NetworkResult.OnSuccess -> {
                    viewModel.setImageUrl(it.data[0].url)
                    viewModel.setTitle(it.data[0].title)
                }
            }
        })

        viewModel.comment.observe(viewLifecycleOwner, {
            when (it) {
                is NetworkResult.OnLoading -> {
                }
                is NetworkResult.OnFailure -> {
                }
                is NetworkResult.OnUnexpected -> {
                }
                is NetworkResult.OnSuccess -> {
                    if (it.data.isNullOrEmpty()) {
                        viewModel.setIsCommentExists(false)
                    } else {
                        viewModel.setIsCommentExists(true)
                        adapter.submitList(it.data)
                    }
                }
            }
        })
    }

    private fun fetchDetails() {
        viewModel.fetchComments(args.postId)
        viewModel.fetchPhoto(args.postId)
        viewModel.setBody(args.body)
    }
}