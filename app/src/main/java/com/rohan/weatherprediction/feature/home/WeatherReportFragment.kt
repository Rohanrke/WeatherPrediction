package com.rohan.weatherprediction.feature.home

import android.app.AlertDialog
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.rohan.weatherprediction.base.presentation.BaseFragment
import com.rohan.weatherprediction.R
import com.rohan.weatherprediction.databinding.FragmentWeatherReportBinding
import com.rohan.weatherprediction.feature.home.forecast.ForecastAdapter
import org.koin.android.viewmodel.ext.android.sharedViewModel

class WeatherReportFragment :
    BaseFragment<FragmentWeatherReportBinding>(R.layout.fragment_weather_report) {
    private val homeViewModel: HomeViewModel by sharedViewModel()

    private lateinit var binding: FragmentWeatherReportBinding

    private var foreCastAdapter: ForecastAdapter? = null

    override fun initComponents(
        savedInstanceState: Bundle?,
        binding: FragmentWeatherReportBinding
    ) {
        this.binding = binding
        binding.apply {
            viewModel = homeViewModel
            lifecycleOwner = this@WeatherReportFragment
        }
        initForecastView()
    }

    private fun initForecastView() {
        foreCastAdapter = ForecastAdapter()
        binding.recyclerForecast.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = foreCastAdapter
        }
    }

    override fun observeLiveEvents() {
        super.observeLiveEvents()

        homeViewModel.forecastEntityLiveData.observe(this, {
            it.list?.let { list ->
                foreCastAdapter?.submitList(list)
            }
        })
        homeViewModel.savedCityLiveData.observe(this, { city ->
            homeViewModel.fetchCurrentWeatherData(city)
            homeViewModel.fetchForecastWeatherData(city)
        })

        homeViewModel.messageRes.observe(this, {
            showToast(it)
        })

        homeViewModel.alertItem.observe(this, Observer {
            showAlertForWeather(it)
        })
    }

    private fun showAlertForWeather(alertItem: AlertItem) {
        activity?.let {
            val builder = AlertDialog.Builder(it).setTitle(R.string.dialog_title_alert)
                .setMessage(getString(alertItem.messageId,alertItem.tempString))
                .setNeutralButton(R.string.dialog_ok) { dialog, _ ->
                    dialog.dismiss()
                }

            builder.create().show()
        }
    }


}