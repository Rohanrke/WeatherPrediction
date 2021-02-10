package com.rohan.weatherprediction.base.presentation

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.ColorRes
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.rohan.weatherprediction.base.navigation.NavigationContract

abstract class BaseActivity<VDB : ViewDataBinding> constructor(
        @LayoutRes private val layoutResId: Int
) : AppCompatActivity(), NavigationContract {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        // set content view using data-binding
        DataBindingUtil.setContentView<VDB>(this, layoutResId).run {
            // continue initialization
            this.lifecycleOwner = this@BaseActivity
            initComponents(savedInstanceState, this)
        }
        observeLiveEvents()
    }


    // initialize other components required after onCreate
    abstract fun initComponents(savedInstanceState: Bundle?, binding: VDB)

    fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    fun showToast(@StringRes msg: Int) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    fun color(@ColorRes id: Int): Int {
        return resources.getColor(id)
    }

}