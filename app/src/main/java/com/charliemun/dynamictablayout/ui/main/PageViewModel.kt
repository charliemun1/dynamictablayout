package com.charliemun.dynamictablayout.ui.main

import androidx.lifecycle.*

class PageViewModel : ViewModel() {

    private val _index = MutableLiveData<Int>()
    val index: LiveData<Int> = _index


    fun setIndex(index: Int) {
        _index.value = index
    }
}