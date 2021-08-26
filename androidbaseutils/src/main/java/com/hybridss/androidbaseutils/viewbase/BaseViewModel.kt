package com.hybridss.androidbaseutils.viewbase

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

open class BaseViewModel : ViewModel() {
    val compositeDisposable: CompositeDisposable = CompositeDisposable()
    val alert: MutableLiveData<String> = MutableLiveData()
    val loader: MutableLiveData<Boolean> = MutableLiveData()

    fun clearViewModel() {
        onCleared()
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}