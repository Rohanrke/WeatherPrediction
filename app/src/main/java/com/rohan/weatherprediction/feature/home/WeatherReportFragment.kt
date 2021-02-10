package com.rohan.weatherprediction.feature.home

import android.os.Bundle
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
        homeViewModel.updateTitle(getString(R.string.title_weather))
        homeViewModel.getSavedCity()
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
    }

}