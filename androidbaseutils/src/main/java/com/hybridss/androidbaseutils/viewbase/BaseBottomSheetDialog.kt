package com.hybridss.androidbaseutils.viewbase

import android.content.Intent
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

open class BaseBottomSheetDialog : BottomSheetDialogFragment() {
    protected fun lanzarActivity(intent: Intent?, flags: Int) {
        (activity as BaseActivity).lanzarActivity(intent!!, flags)
    }
}