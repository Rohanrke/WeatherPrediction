package com.rohan.weatherprediction.base.navigation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import timber.log.Timber

object Navigator {

    fun <T> navigateToActivity(context: Context, activityClass: Class<T>, bundle: Bundle? = null) {
        context.startActivity(Intent(context, activityClass).apply {
            bundle?.let { putExtras(it) }
        })
    }

    /**
     * @param fragmentManager
     * @param fragment
     * @param frameId
     * @param startAnimation
     * @param endAnimation
     * @param backstackId     Add Product Fragment with Animation
     */
    fun addFragmentEntireScreenWithAnim(
        fragmentManager: FragmentManager,
        fragment: Fragment, frameId: Int, startAnimation: Int,
        endAnimation: Int, backstackId: String?
    ) {
        val transaction = fragmentManager.beginTransaction()
        transaction.setCustomAnimations(startAnimation, 0, 0, endAnimation)
        transaction.add(frameId, fragment)
        if (backstackId != null) {
            transaction.addToBackStack(backstackId)
        }
        try {
            transaction.commitAllowingStateLoss()
        } catch (e: IllegalStateException) {
            Timber.e(e)
        }
    }

}