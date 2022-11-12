package com.hybridss.androidbaseutils.viewbase

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

open class BaseViewModel : ViewModel() {
    var compositeDisposable: CompositeDisposable = CompositeDisposable()
    var alert: SingleLiveEvent<String> = SingleLiveEvent()
    var loader: SingleLiveEvent<Boolean> = SingleLiveEvent()

    fun clearViewModel() {
        onCleared()
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}