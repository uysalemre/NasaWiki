package com.eu.citizenmecase

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * @author Emre UYSAL
 * Main activity view model for handling states in it
 */
@HiltViewModel
class MainActivityViewModel @Inject constructor() : ViewModel() {
    private val _isLoadingVisible: MutableLiveData<Boolean> = MutableLiveData()
    val isLoadingVisible: LiveData<Boolean> get() = _isLoadingVisible

    fun updateLoadingVisibility(isVisible: Boolean) {
        _isLoadingVisible.value = isVisible
    }
}