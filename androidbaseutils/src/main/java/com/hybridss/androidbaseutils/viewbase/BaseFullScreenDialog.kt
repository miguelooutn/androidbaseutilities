package com.hybridss.androidbaseutils.viewbase

import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.ViewGroup
import android.view.Window
import android.widget.RelativeLayout
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import com.hybridss.androidbaseutils.R

open class BaseFullScreenDialog : DialogFragment() {
    override fun getTheme(): Int {
        return R.style.FullScreenDialog
    }

    fun <T : Fragment?> mostrarFragmentFade(
        fragmentClass: Class<T>,
        containerViewId: Int,
        bundle: Bundle?,
        addToBackStack: Boolean,
        animation: Boolean
    ) {
        val fragmentManager = childFragmentManager
        val fragmentTransaction =
            fragmentManager.beginTransaction()
        var fragment =
            fragmentManager.findFragmentByTag(fragmentClass.simpleName)
        if (fragment == null) {
            try {
                fragment = fragmentClass.newInstance()
                fragment!!.arguments = bundle
            } catch (e: Exception) {
                throw RuntimeException("New Fragment should have been created", e)
            }
        }
        fragmentTransaction.setCustomAnimations(R.animator.fade_in, R.animator.fade_out)
        fragmentTransaction.replace(containerViewId, fragment, fragmentClass.simpleName)
        if (addToBackStack) {
            fragmentTransaction.addToBackStack(null)
        }
        fragmentTransaction.commit()
    }

    fun <T : Fragment?> mostrarFragment(
        fragmentClass: Class<T>,
        containerViewId: Int,
        bundle: Bundle?,
        addToBackStack: Boolean,
        animation: Boolean
    ) {
        val fragmentManager = childFragmentManager
        val fragmentTransaction =
            fragmentManager.beginTransaction()
        var fragment =
            fragmentManager.findFragmentByTag(fragmentClass.simpleName)
        if (fragment == null) {
            fragment = try {
                fragmentClass.newInstance()
            } catch (e: Exception) {
                throw RuntimeException("New Fragment should have been created", e)
            }
        }
        fragment!!.arguments = bundle
        if (animation) {
            fragmentTransaction.setCustomAnimations(
                R.animator.slide_in_left,
                R.animator.slide_out_right, android.R.anim.slide_in_left,
                android.R.anim.slide_out_right
            )
        }
        fragmentTransaction.replace(containerViewId, fragment, fragmentClass.simpleName)
        if (addToBackStack) {
            fragmentTransaction.addToBackStack(null)
        }
        fragmentTransaction.commit()
    }

    protected fun lanzarActivity(intent: Intent?, flags: Int) {
        (activity as BaseActivity).lanzarActivity(intent!!, flags)
    }

    protected fun lanzarActivityForResult(intent: Intent?, requestCode: Int, flags: Int) {
        (activity as BaseActivity).lanzarActivityForResult(intent!!, requestCode, flags)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val root = RelativeLayout(activity)
        root.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )

        // creating the fullscreen dialog
        val dialog = activity?.let { Dialog(it) }
        dialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(root)
        dialog.window!!.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        return dialog
    }

    fun setOnBackKeyPressed() {
        // Add back button listener
        dialog!!.setOnKeyListener { dialogInterface: DialogInterface?, keyCode: Int, keyEvent: KeyEvent ->
            if (keyCode == KeyEvent.KEYCODE_BACK && keyEvent.action == KeyEvent.ACTION_UP) {
                // Your code here
                return@setOnKeyListener true // Capture onKey
            }
            false // Don't capture
        }
    }

    fun progress(it: Boolean) {
        (activity as BaseActivity).progress(it)
    }

    fun mostrarAlerta(titulo: String, mensaje: String) {
        (activity as BaseActivity).mostrarAlerta(titulo, mensaje, requireActivity())
    }

    fun mostrarAlerta(
        titulo: String, mensaje: String, textoPositivo: String,
        listenerPositivo: DialogInterface.OnClickListener
    ) {
        (activity as BaseActivity).mostrarAlerta(
            titulo,
            mensaje,
            requireActivity(),
            textoPositivo,
            listenerPositivo
        )
    }

    fun mostrarAlerta(
        titulo: String, mensaje: String, textoPositivo: String,
        listenerPositivo: DialogInterface.OnClickListener,
        textoNegativo : String,
        listenerNegativo: DialogInterface.OnClickListener
    ) {
        (activity as BaseActivity).mostrarAlerta(
            titulo,
            mensaje,
            requireActivity(),
            textoPositivo,
            listenerPositivo,
            textoNegativo,
            listenerNegativo
        )
    }
}