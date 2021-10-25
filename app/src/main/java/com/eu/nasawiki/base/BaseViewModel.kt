package com.eu.nasawiki.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.eu.nasawiki.utils.ui.Event

abstract class BaseViewModel : ViewModel() {
    private val _isLoading: MutableLiveData<Event<Boolean>> = MutableLiveData()
    val isLoading: LiveData<Event<Boolean>> get() = _isLoading

    private val _errorUnexpected: MutableLiveData<Event<Int>> = MutableLiveData()
    val errorUnexpected: LiveData<Event<Int>> get() = _errorUnexpected

    protected fun setError(messageId: Int) {
        _errorUnexpected.value = Event(messageId)
    }

    protected fun setIsLoading(isLoading: Boolean) {
        _isLoading.value = Event(isLoading)
    }
}