package com.rohan.weatherprediction.base.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {

    protected val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String>
        get() = _errorMessage

    protected val _state = MutableLiveData<ProgressState>()
    val state: LiveData<ProgressState>
        get() = _state


    protected val _messageRes = MutableLiveData<Int>()
    val messageRes: LiveData<Int>
        get() = _messageRes

    protected val _errorMessageRes = MutableLiveData<Int>()
    val errorMessageRes: LiveData<Int>
        get() = _errorMessageRes

}