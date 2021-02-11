package com.rohan.weatherprediction.feature.home

import android.app.AlertDialog
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import com.rohan.weatherprediction.base.presentation.BaseActivity
import com.rohan.weatherprediction.databinding.ActivityHomeBinding
import com.rohan.weatherprediction.R
import com.rohan.weatherprediction.base.navigation.Navigator
import com.rohan.weatherprediction.feature.home.search.SearchCityFragment
import org.koin.android.viewmodel.ext.android.viewModel

class HomeActivity : BaseActivity<ActivityHomeBinding>(R.layout.activity_home) {

    private val viewModel: HomeViewModel by viewModel()

    private var weatherFragment: WeatherReportFragment? = null

    override fun initComponents(savedInstanceState: Bundle?, binding: ActivityHomeBinding) {
        viewModel.getSavedCity()
        goToReport()
    }

    override fun observeLiveEvents() {

        viewModel.title.observe(this, {
            title = it
        })
        viewModel.hasCity.observe(this, { hasCity ->
            hasCity?.let {
                if (it) showReport() else showAlertForCity()
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.option_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.change_city -> {
                goToSearch()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun goToSearch() {
        Navigator.addFragmentEntireScreenWithAnim(
            supportFragmentManager,
            SearchCityFragment(),
            R.id.container,
            R.anim.slide_from_right,
            R.anim.slide_out_right,
            SearchCityFragment::class.java.name
        )
    }

    private fun goToReport() {
        weatherFragment = WeatherReportFragment()
        weatherFragment?.let {
            supportFragmentManager.beginTransaction()
                .add(R.id.container, it)
                .hide(it)
                .commit()
        }
    }

    private fun showReport() {
        weatherFragment?.let {
            if (it.isHidden) {
                supportFragmentManager.beginTransaction().show(it).commit()
            }
        }
    }

    private fun showAlertForCity() {
        val builder = AlertDialog.Builder(this).setMessage(R.string.dialog_title)
            .setPositiveButton(R.string.dialog_select) { dialog, _ ->
                goToSearch()
                dialog.dismiss()
            }
            .setNegativeButton(R.string.dialog_cancel) { dialog, _ ->
                dialog.dismiss()
                finish()
            }
        builder.create().show()
    }
}