package com.hybridss.androidbaseutils.viewbase

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.hybridss.androidbaseutils.R
import java.util.*

open class BaseActivity : AppCompatActivity() {


    private lateinit var progressDialog: Dialog

    fun mostrarFragmentFade(
        fragmentIn: BaseFragment,
        containerViewId: Int,
        bundle: Bundle?,
        addToBackStack: Boolean
    ) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction =
            fragmentManager.beginTransaction()
        val fragment =
            fragmentManager.findFragmentByTag(fragmentIn.javaClass.simpleName)
        if (fragment == null) {
            try {
                fragmentIn.arguments = bundle
            } catch (e: Exception) {
                throw RuntimeException("New Fragment should have been created", e)
            }
        }
        fragmentTransaction.setCustomAnimations(R.animator.fade_in, R.animator.fade_out)
        fragmentTransaction.replace(
            containerViewId,
            fragmentIn,
            fragmentIn.javaClass.simpleName
        )
        if (addToBackStack) {
            fragmentTransaction.addToBackStack( fragmentIn.javaClass.simpleName)
        }
        fragmentTransaction.commit()
    }

    open fun <T : Fragment?> mostrarFragment(
        fragmentClass: Class<T>,
        containerViewId: Int,
        bundle: Bundle?,
        addToBackStack: Boolean
    ) {
        mostrarFragment(fragmentClass, containerViewId, bundle, addToBackStack, false)
    }

    open fun <T : Fragment?> mostrarFragment(
        fragmentClass: Class<T>,
        containerViewId: Int,
        bundle: Bundle?,
        addToBackStack: Boolean,
        clearStack: Boolean
    ) {
        mostrarFragment(fragmentClass, containerViewId, bundle, addToBackStack, clearStack, false)
    }

    open fun <T : Fragment?> mostrarFragment(
        fragmentClass: Class<T>,
        containerViewId: Int,
        bundle: Bundle?,
        addToBackStack: Boolean,
        clearStack: Boolean,
        commitAllowingStateLoss: Boolean
    ) {
        val fragmentManager = supportFragmentManager
        if (clearStack) {
            for (i in 0 until fragmentManager.backStackEntryCount) {
                fragmentManager.popBackStack()
            }
        }
        val fragmentTransaction = fragmentManager.beginTransaction()
        var fragment = fragmentManager.findFragmentByTag(fragmentClass.simpleName)
        if (fragment == null) {
            try {
                fragment = fragmentClass.newInstance()
                fragment!!.arguments = bundle
            } catch (e: java.lang.Exception) {
                throw java.lang.RuntimeException("New Fragment should have been created", e)
            }
        }
        fragmentTransaction.replace(containerViewId, fragment!!, fragmentClass.simpleName)
        if (addToBackStack) {
            fragmentTransaction.addToBackStack(fragmentClass.name)
        }
        if (commitAllowingStateLoss) {
            fragmentTransaction.commitAllowingStateLoss()
        } else {
            fragmentTransaction.commit()
        }
    }

    fun lanzarActivity(intent: Intent, flags: Int) {
        if (flags == NO_FLAGS) {
            startActivity(intent)
        } else {
            startActivity(intent.addFlags(flags))
        }
    }

    fun lanzarActivityForResult(intent: Intent, requestCode: Int, flags: Int) {
        if (flags == NO_FLAGS) {
            startActivityForResult(intent, requestCode)
        } else {
            startActivityForResult(intent.addFlags(flags), requestCode)
        }
    }

    fun mostrarAlerta(
        titulo: String,
        mensaje: String,
        activity: Activity,
        textoPositivo: String,
        listenerPositivo: DialogInterface.OnClickListener
    ){
        mostrarAlerta(titulo, mensaje, activity, textoPositivo, listenerPositivo,"",DialogInterface.OnClickListener{_,_->})
    }

    fun mostrarAlerta(
        titulo: String,
        mensaje: String,
        activity: Activity,
        textoPositivo: String,
        listenerPositivo: DialogInterface.OnClickListener,
        textoNegativo : String,
        listenerNegativo: DialogInterface.OnClickListener
    ) {
        val builder = AlertDialog.Builder(activity)

        if (titulo.isNotEmpty()) {
            builder.setTitle(titulo)
        }

        if (textoPositivo.isNotEmpty()) {
            builder.setPositiveButton(textoPositivo, listenerPositivo)
        }

        if (textoNegativo.isNotEmpty()) {
            builder.setNegativeButton(textoNegativo, listenerNegativo)
        }

        builder.setMessage(mensaje)
        val alertDialog = builder.create()
        alertDialog.show()
    }

    fun mostrarAlerta(
        titulo: String,
        mensaje: String,
        activity: Activity
    ) {
        mostrarAlerta(titulo, mensaje, activity, "", DialogInterface.OnClickListener { _, _ -> },"",DialogInterface.OnClickListener{_,_->})
    }

    fun progress(it: Boolean) {
        progressDialog = Dialog(this)

        if (it) {
            progressDialog.setContentView(R.layout.dialog_progress)
            progressDialog.setCancelable(false)
            progressDialog.show()
            progressDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        } else {
            progressDialog.dismiss()
        }
    }

    fun mostrarErrorSnackBar(it: String) {
        val snack = Snackbar.make(window.decorView, it, Snackbar.LENGTH_LONG)
        snack.animationMode = Snackbar.ANIMATION_MODE_SLIDE
        snack.setAction(getString(R.string.msg_aceptar)) { snack.dismiss() }
        snack.setActionTextColor(resources.getColor(R.color.md_red_500))
        snack.show()
    }


    fun closeKeyboard(context: Context, view: View) {
        val inputMethodManager =
            context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    fun openKeyBoard(context: Context, view: View) {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        Objects.requireNonNull(imm).showSoftInput(view, 0)
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        var accion = false
        Log.d(javaClass.simpleName, "empezando on key down")
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            for (i in supportFragmentManager.fragments.indices) {
                if (supportFragmentManager.fragments[i] is BaseFragment) {
                    val fragment =
                        supportFragmentManager.fragments[i] as BaseFragment
                    accion = accion || fragment.onBackPressed()
                }
            }
        }
        Log.d(javaClass.simpleName, " accion = $accion")
        return accion || super.onKeyDown(keyCode, event)
    }

    companion object {
        const val NO_FLAGS = -1
    }
}