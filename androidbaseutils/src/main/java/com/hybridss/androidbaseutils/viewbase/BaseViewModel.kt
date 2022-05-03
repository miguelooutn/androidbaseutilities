package com.hybridss.androidbaseutils.viewbase

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

open class BaseViewModel : ViewModel() {
    var compositeDisposable: CompositeDisposable = CompositeDisposable()
    var alert: MutableLiveData<String> = MutableLiveData()
    var loader: MutableLiveData<Boolean> = MutableLiveData()

    fun clearViewModel() {
        onCleared()
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}