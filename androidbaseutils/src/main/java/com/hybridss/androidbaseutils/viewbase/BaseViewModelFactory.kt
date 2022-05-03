package com.hybridss.androidbaseutils.viewbase

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

open class BaseViewModelFactory(private val inneriClass: Any) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = inneriClass as T
}