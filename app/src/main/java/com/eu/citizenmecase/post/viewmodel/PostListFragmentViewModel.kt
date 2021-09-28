package com.eu.citizenmecase.post.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eu.citizenmecase.post.repository.PostRepository
import com.eu.citizenmecase.post.repository.remote.PostModel
import com.eu.citizenmecase.utils.network.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author Emre UYSAL
 * View model class for managing main page content and states
 */
@HiltViewModel
class PostListFragmentViewModel @Inject constructor(private val repository: PostRepository) :
    ViewModel() {

    private val _posts: MutableLiveData<NetworkResult<List<PostModel>>> = MutableLiveData()
    val posts: LiveData<NetworkResult<List<PostModel>>> get() = _posts

    private val _isItemExits: MutableLiveData<Boolean> = MutableLiveData()
    val isItemExists: LiveData<Boolean> get() = _isItemExits

    private val _isRefreshing: MutableLiveData<Boolean> = MutableLiveData()
    val isRefreshing: LiveData<Boolean> get() = _isRefreshing

    init {
        fetchPosts()
    }

    private fun fetchPosts() {
        viewModelScope.launch {
            repository.getPosts().collect {
                _posts.postValue(it)
            }
        }
    }

    fun updateItemExists(isExists: Boolean) {
        viewModelScope.launch {
            _isItemExits.value = isExists
        }
    }

    fun swipeAndRefresh() {
        viewModelScope.launch {
            _isRefreshing.value = true
            fetchPosts()
        }
    }

    fun stopRefresh() {
        viewModelScope.launch {
            _isRefreshing.value = false
        }
    }
}