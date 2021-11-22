package com.charliemun.dynamictablayout.ui.main

import androidx.lifecycle.*

class PageViewModel : ViewModel() {

    private val _index = MutableLiveData<Int>()
    private val _message = MutableLiveData<String>()
    val text: LiveData<String> = Transformations.switchMap<String, String>(_message
        ) { message: String ->
        return@switchMap liveData {
            emit("Hello world from section: ${_index.value} on tab: $message")
        }

    }
    fun setIndex(index: Int) {
        _index.value = index
    }
    fun setMessage(message: String) {
        _message.value = message
    }
}