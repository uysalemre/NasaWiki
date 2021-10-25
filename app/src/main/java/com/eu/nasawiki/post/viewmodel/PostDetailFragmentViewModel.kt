package com.eu.nasawiki.post.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.eu.nasawiki.base.BaseViewModel
import com.eu.nasawiki.post.repository.PostRepository
import com.eu.nasawiki.post.repository.remote.CommentModel
import com.eu.nasawiki.post.repository.remote.PhotoModel
import com.eu.nasawiki.utils.network.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author Emre UYSAL
 * View model class for managing detail page content and states
 */
@HiltViewModel
class PostDetailFragmentViewModel @Inject constructor(private val repository: PostRepository) :
    BaseViewModel() {

    private val _photo: MutableLiveData<PhotoModel> = MutableLiveData()
    val photo: LiveData<PhotoModel> get() = _photo

    private val _comment: MutableLiveData<List<CommentModel>> = MutableLiveData()
    val comment: LiveData<List<CommentModel>> get() = _comment

    fun fetchPhoto(albumId: Long) = viewModelScope.launch {
        repository.getPhoto(albumId).collect {
            when (it) {
                is NetworkResult.OnLoading -> setIsLoading(true)
                is NetworkResult.OnUnexpected -> {
                    setIsLoading(false)
                    setError(it.messageId)
                }
                is NetworkResult.OnSuccess -> {
                    setIsLoading(false)
                    _photo.value = it.data
                }
            }
        }
    }

    fun fetchComments(postId: Long) = viewModelScope.launch {
        repository.getComments(postId).collect {
            when (it) {
                is NetworkResult.OnLoading -> setIsLoading(true)
                is NetworkResult.OnUnexpected -> {
                    setIsLoading(false)
                    setError(it.messageId)
                }
                is NetworkResult.OnSuccess -> {
                    setIsLoading(false)
                    _comment.value = it.data
                }
            }
        }
    }
}