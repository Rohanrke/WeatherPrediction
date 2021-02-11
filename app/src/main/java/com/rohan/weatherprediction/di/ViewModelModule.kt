package com.rohan.weatherprediction.di

import com.rohan.weatherprediction.feature.home.HomeViewModel
import com.rohan.weatherprediction.feature.home.search.SearchCityViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module


fun viewModelModule() = module {
    viewModel { HomeViewModel(Dispatchers.IO, get(), get(), get(), get(), get()) }
    viewModel { SearchCityViewModel(Dispatchers.IO, get() ) }
}
