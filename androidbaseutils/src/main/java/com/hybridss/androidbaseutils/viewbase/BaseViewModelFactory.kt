package com.hybridss.androidbaseutils.viewbase

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

open class BaseViewModelFactory(private val inneriClass: Any) : ViewModelProvider.Factory {
    override fun <Any : ViewModel> create(modelClass: Class<Any>): Any = inneriClass as Any
}