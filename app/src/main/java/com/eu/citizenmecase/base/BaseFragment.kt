package com.eu.citizenmecase.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.eu.citizenmecase.post.view.PostActivity

/**
 * @author Emre UYSAL
 * Base Fragment for fragments
 * Manages binding lifecycle and creates general objects for children
 */
abstract class BaseFragment<db : ViewDataBinding>(@LayoutRes val layoutId: Int) : Fragment() {
    private var _binding: db? = null
    protected val binding get() = _binding!!
    protected val navController by lazy { findNavController() }
    private val currentActivity by lazy { requireActivity() as PostActivity }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onViewCreationCompleted()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    abstract fun onViewCreationCompleted()

    protected fun hideLoading() {
        currentActivity.updateLoadingVisibility(false)
    }

    protected fun showLoading() {
        currentActivity.updateLoadingVisibility(true)
    }
}