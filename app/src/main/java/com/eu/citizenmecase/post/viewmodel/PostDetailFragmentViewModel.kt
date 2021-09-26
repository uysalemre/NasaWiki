package com.eu.citizenmecase.post.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eu.citizenmecase.post.repository.PostRepository
import com.eu.citizenmecase.post.repository.remote.CommentModel
import com.eu.citizenmecase.post.repository.remote.PhotoModel
import com.eu.citizenmecase.utils.network.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostDetailFragmentViewModel @Inject constructor(private val repository: PostRepository) :
    ViewModel() {
    private val _photo: MutableLiveData<NetworkResult<List<PhotoModel>>> = MutableLiveData()
    val photo: LiveData<NetworkResult<List<PhotoModel>>> get() = _photo

    private val _comment: MutableLiveData<NetworkResult<List<CommentModel>>> = MutableLiveData()
    val comment: LiveData<NetworkResult<List<CommentModel>>> get() = _comment

    private val _title: MutableLiveData<String> = MutableLiveData()
    val title: LiveData<String> get() = _title

    private val _imageUrl: MutableLiveData<String> = MutableLiveData()
    val imageUrl: LiveData<String> get() = _imageUrl

    private val _body: MutableLiveData<String> = MutableLiveData()
    val body: LiveData<String> get() = _body

    private val _isCommentExists: MutableLiveData<Boolean> = MutableLiveData()
    val isCommentExists: LiveData<Boolean> get() = _isCommentExists


    fun fetchPhoto(albumId: Long) {
        viewModelScope.launch {
            repository.getPhoto(albumId).collect {
                _photo.postValue(it)
            }
        }
    }

    fun fetchComments(postId: Long) {
        viewModelScope.launch {
            repository.getComments(postId).collect {
                _comment.postValue(it)
            }
        }
    }

    fun setTitle(title: String) {
        _title.value = title
    }

    fun setImageUrl(imageUrl: String) {
        _imageUrl.value = imageUrl
    }

    fun setBody(body: String) {
        _body.value = body
    }

    fun setIsCommentExists(isExists: Boolean) {
        _isCommentExists.value = isExists
    }
}