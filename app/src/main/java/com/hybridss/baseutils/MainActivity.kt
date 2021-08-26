package com.hybridss.baseutils

import android.os.Bundle
import com.hybridss.androidbaseutils.viewbase.BaseActivity

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}