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

@HiltViewModel
class PostListFragmentViewModel @Inject constructor(private val repository: PostRepository) :
    ViewModel() {
    private val _posts: MutableLiveData<NetworkResult<List<PostModel>>> = MutableLiveData()
    val posts: LiveData<NetworkResult<List<PostModel>>> get() = _posts

    private val _isItemExits: MutableLiveData<Boolean> = MutableLiveData(true)
    val isItemExists: LiveData<Boolean> get() = _isItemExits

    init {
        fetchPosts()
    }

    fun fetchPosts() {
        viewModelScope.launch {
            repository.getPosts().collect {
                _posts.postValue(it)
            }
        }
    }

    fun updateItemExists(isExists: Boolean) {
        _isItemExits.value = isExists
    }
}