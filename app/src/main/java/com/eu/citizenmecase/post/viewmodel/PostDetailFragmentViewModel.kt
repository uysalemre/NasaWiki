package com.eu.citizenmecase.post.viewmodel

import androidx.lifecycle.ViewModel
import com.eu.citizenmecase.post.repository.PostRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PostDetailFragmentViewModel @Inject constructor(private val repository: PostRepository) :
    ViewModel() {
}