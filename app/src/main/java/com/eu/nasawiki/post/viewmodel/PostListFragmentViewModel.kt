package com.eu.nasawiki.post.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.eu.nasawiki.base.BaseViewModel
import com.eu.nasawiki.post.repository.PostRepository
import com.eu.nasawiki.post.repository.remote.PostModel
import com.eu.nasawiki.utils.network.NetworkResult
import com.eu.nasawiki.utils.ui.Event
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
    BaseViewModel() {

    private val _isRefreshing: MutableLiveData<Boolean> = MutableLiveData()
    val isRefreshing: LiveData<Boolean> get() = _isRefreshing

    private val _listItemClick: MutableLiveData<Event<Long>> = MutableLiveData()
    val listItemClick: LiveData<Event<Long>> = _listItemClick

    private val _postsData: MutableLiveData<List<PostModel>> = MutableLiveData()
    val postsData: LiveData<List<PostModel>> get() = _postsData

    private val _spanCount: MutableLiveData<Int> = MutableLiveData(2)
    val spanCount: LiveData<Int> get() = _spanCount

    init {
        fetchPosts()
    }

    fun fetchPosts() {
        viewModelScope.launch {
            repository.getPosts().collect {
                when (it) {
                    is NetworkResult.OnLoading -> setIsLoading(true)
                    is NetworkResult.OnUnexpected -> {
                        setIsLoading(false)
                        setError(it.messageId)
                        _isRefreshing.value = false
                    }
                    is NetworkResult.OnSuccess -> {
                        setIsLoading(false)
                        _isRefreshing.value = false
                        when {
                            it.data.isNullOrEmpty() -> _spanCount.value = 1
                            else -> _spanCount.value = 2
                        }
                        _postsData.value = it.data
                    }
                }
            }
        }
    }

    fun swipeAndRefresh() {
        viewModelScope.launch {
            _isRefreshing.value = true
        }
    }

    fun onListItemClick(id: Long) {
        _listItemClick.value = Event(id)
    }
}