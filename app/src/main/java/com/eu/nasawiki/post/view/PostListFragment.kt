package com.eu.nasawiki.post.view

import androidx.fragment.app.viewModels
import com.eu.nasawiki.R
import com.eu.nasawiki.base.BaseFragment
import com.eu.nasawiki.databinding.FragmentPostBinding
import com.eu.nasawiki.post.viewmodel.PostListFragmentViewModel
import com.eu.nasawiki.utils.ui.EventObserver
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

/**
 * @author Emre UYSAL
 * Main page fragment that shows posts to user and navigates to detail
 */
@AndroidEntryPoint
class PostListFragment : BaseFragment<FragmentPostBinding>(R.layout.fragment_post) {
    private val postListViewModel: PostListFragmentViewModel by viewModels()

    override fun onViewCreationCompleted() {
        initializeView()
        initializeObserver()
    }

    private fun initializeView() {
        with(binding) {
            viewModel = postListViewModel
        }
    }

    private fun initializeObserver() {
        with(postListViewModel) {
            listItemClick.observe(viewLifecycleOwner, EventObserver {
                navController.navigate(
                    PostListFragmentDirections.actionPhotoListFragmentToPhotoDetailFragment(it)
                )
            })

            isLoading.observe(viewLifecycleOwner, EventObserver {
                when (it) {
                    true -> showLoading()
                    false -> hideLoading()
                }
            })

            errorUnexpected.observe(viewLifecycleOwner, EventObserver {
                showSnackBar(it, R.string.err_retry, Snackbar.LENGTH_LONG) {
                    fetchPosts()
                }
            })
        }
    }
}