package com.rohan.weatherprediction.base.presentation

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.andrognito.flashbar.Flashbar
import com.rohan.weatherprediction.base.navigation.NavigationContract


const val FIVE_SEC_MILLIS = 5000L
abstract class BaseFragment<VDB : ViewDataBinding>(
    @LayoutRes private val layoutResId: Int
) : Fragment(), NavigationContract {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = DataBindingUtil.inflate<VDB>(inflater, layoutResId, container, false).apply {
        initComponents(savedInstanceState, this)
        observeLiveEvents()
    }.root

    abstract fun initComponents(savedInstanceState: Bundle?, binding: VDB)



    fun showToast(msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }

    fun showToast(@StringRes msg: Int) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }

    fun showErrorMessage(context: Activity,message: String){
        Flashbar.Builder(context)
            .gravity(Flashbar.Gravity.BOTTOM)
            .backgroundColor(
                ContextCompat.getColor(
                    context,
                    android.R.color.holo_red_dark
                )
            )
            .duration(FIVE_SEC_MILLIS)
            .message(message)
            .build().show()
    }
}

