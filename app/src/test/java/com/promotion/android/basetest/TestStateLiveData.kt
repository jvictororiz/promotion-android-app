package com.promotion.android.basetest

import androidx.lifecycle.MutableLiveData

open class TestStateLiveData<T> : MutableLiveData<T>() {
    private val listStates = mutableListOf<T>()

    override fun postValue(value: T) {
        listStates.add(value)
        super.postValue(value)
    }

    override fun setValue(value: T) {
        listStates.add(value)
        super.setValue(value)
    }

    fun getListStates() = listStates

    fun clear() {
        listStates.clear()
    }
}