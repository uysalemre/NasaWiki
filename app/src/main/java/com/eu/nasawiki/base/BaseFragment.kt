package com.eu.nasawiki.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.eu.nasawiki.post.view.PostActivity
import com.google.android.material.snackbar.Snackbar

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
    private var snackbar: Snackbar? = null

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

    override fun onStop() {
        super.onStop()
        snackbar?.dismiss()
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

    protected fun showSnackBar(
        @StringRes messageResId: Int,
        @StringRes actionResId: Int,
        duration: Int,
        action: () -> Unit
    ) {
        snackbar = Snackbar
            .make(binding.root, getString(messageResId), duration)
            .setAction(actionResId) {
                action()
            }
        snackbar?.show()
    }
}