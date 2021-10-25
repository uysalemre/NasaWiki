package com.eu.nasawiki.post.view

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.eu.nasawiki.R
import com.eu.nasawiki.base.BaseFragment
import com.eu.nasawiki.databinding.FragmentPostDetailBinding
import com.eu.nasawiki.post.viewmodel.PostDetailFragmentViewModel
import com.eu.nasawiki.utils.ui.EventObserver
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

/**
 * @author Emre UYSAL
 * Detail page fragment that shows user detailed information about post and comments
 */
@AndroidEntryPoint
class PostDetailFragment : BaseFragment<FragmentPostDetailBinding>(R.layout.fragment_post_detail) {
    private val postDetailFragmentViewModel: PostDetailFragmentViewModel by viewModels()
    private val args: PostDetailFragmentArgs by navArgs()

    override fun onViewCreationCompleted() {
        initializeView()
        initializeObserver()
        fetchDetails()
    }

    private fun initializeView() {
        with(binding) {
            viewmodel = postDetailFragmentViewModel
        }
    }

    private fun initializeObserver() {
        with(postDetailFragmentViewModel) {
            isLoading.observe(viewLifecycleOwner, EventObserver {
                when (it) {
                    true -> showLoading()
                    false -> hideLoading()
                }
            })

            errorUnexpected.observe(viewLifecycleOwner, EventObserver {
                showSnackBar(it, R.string.err_retry, Snackbar.LENGTH_LONG) {
                    fetchComments(args.postId)
                    fetchComments(args.postId)
                }
            })
        }
    }

    private fun fetchDetails() = with(postDetailFragmentViewModel) {
        fetchComments(args.postId)
        fetchPhoto(args.postId)
    }
}