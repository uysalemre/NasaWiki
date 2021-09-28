package com.eu.citizenmecase.post.view

import androidx.fragment.app.viewModels
import com.eu.citizenmecase.R
import com.eu.citizenmecase.base.BaseFragment
import com.eu.citizenmecase.databinding.FragmentPostBinding
import com.eu.citizenmecase.post.view.adapter.PostListAdapter
import com.eu.citizenmecase.post.viewmodel.PostListFragmentViewModel
import com.eu.citizenmecase.utils.network.NetworkResult
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * @author Emre UYSAL
 * Main page fragment that shows posts to user and navigates to detail
 */
@AndroidEntryPoint
class PostListFragment : BaseFragment<FragmentPostBinding>(R.layout.fragment_post) {

    @Inject
    lateinit var adapter: PostListAdapter
    private val viewModel: PostListFragmentViewModel by viewModels()

    override fun onViewCreationCompleted() {
        initializeView()
        initializeObserver()
    }

    private fun initializeView() {
        adapter.itemClick = { id, body ->
            val action =
                PostListFragmentDirections.actionPhotoListFragmentToPhotoDetailFragment(id, body)
            navController.navigate(action)
        }
        with(binding) {
            rvAdapter = adapter
            viewmodel = viewModel
        }
    }

    private fun initializeObserver() {
        viewModel.posts.observe(viewLifecycleOwner, {
            viewModel.stopRefresh()
            when (it) {
                is NetworkResult.OnLoading -> showLoading()
                is NetworkResult.OnUnexpected -> {
                    hideLoading()
                }
                is NetworkResult.OnSuccess -> {
                    hideLoading()
                    when {
                        it.data.isNotEmpty() -> {
                            viewModel.updateItemExists(true)
                            adapter.submitList(it.data)
                        }
                        else -> {
                            viewModel.updateItemExists(false)
                        }
                    }
                }
            }
        })
    }
}