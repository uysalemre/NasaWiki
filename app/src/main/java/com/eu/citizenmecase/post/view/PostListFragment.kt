package com.eu.citizenmecase.post.view

import androidx.fragment.app.viewModels
import com.eu.citizenmecase.R
import com.eu.citizenmecase.base.BaseFragment
import com.eu.citizenmecase.databinding.FragmentPostBinding
import com.eu.citizenmecase.post.view.adapter.PostListAdapter
import com.eu.citizenmecase.post.viewmodel.PostListFragmentViewModel
import com.eu.citizenmecase.utils.RecyclerViewItemClickListener
import com.eu.citizenmecase.utils.network.NetworkResult
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PostListFragment : BaseFragment<FragmentPostBinding>(R.layout.fragment_post),
    RecyclerViewItemClickListener {

    private lateinit var adapter: PostListAdapter
    private val viewModel: PostListFragmentViewModel by viewModels()

    override fun onViewCreationCompleted() {
        initializeView()
        initializeObserver()
    }

    private fun initializeView() {
        adapter = PostListAdapter(this)
        with(binding) {
            itemClick = this@PostListFragment
            rvAdapter = adapter
        }
    }

    private fun initializeObserver() {
        viewModel.posts.observe(viewLifecycleOwner, {
            when (it) {
                is NetworkResult.OnLoading -> {
                }
                is NetworkResult.OnFailure -> {
                }
                is NetworkResult.OnUnexpected -> {
                }
                is NetworkResult.OnSuccess -> {
                    adapter.submitList(it.data)
                }
            }
        })
    }

    override fun onItemClick(id: Long) {
        val action =
            PostListFragmentDirections.actionPhotoListFragmentToPhotoDetailFragment(id)
        navController.navigate(action)
    }

}