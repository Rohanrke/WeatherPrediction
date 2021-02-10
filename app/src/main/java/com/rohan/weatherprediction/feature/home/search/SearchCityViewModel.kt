package com.rohan.weatherprediction.feature.home.search

import android.text.TextUtils
import androidx.annotation.VisibleForTesting
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.rohan.weatherprediction.base.presentation.BaseViewModel
import com.rohan.weatherprediction.base.presentation.ProgressState
import com.rohan.weatherprediction.domain.arch.Failure
import com.rohan.weatherprediction.domain.usecase.SearchCityUseCase
import kotlinx.coroutines.launch
import com.rohan.weatherprediction.R

import kotlin.coroutines.CoroutineContext

class SearchCityViewModel(
    private val context: CoroutineContext,
    private val cityUseCase: SearchCityUseCase
) : BaseViewModel() {

    private val _savedCity = MutableLiveData<String>()
    val savedCity: LiveData<String>
        get() = _savedCity

    private var cityCountryData: Map<String, List<String>>? = null

    init {
        fetchCityMap()
    }

    @VisibleForTesting
    fun fetchCityMap() {
        viewModelScope.launch(context) {
            _state.postValue(ProgressState.LOADING)
            cityUseCase.invoke(this) {
                it.either(
                    ::onFailure,
                    ::onGetCityMapSuccess
                )
            }
        }
    }

    @VisibleForTesting
    private fun onFailure(failure: Failure) {
        _state.postValue(ProgressState.ERROR)
        _errorMessage.postValue(failure.message)
    }

    @VisibleForTesting
    private fun onGetCityMapSuccess(entity: Map<String, List<String>>) {
        _state.postValue(ProgressState.SUCCESS)
        cityCountryData = entity

    }

    fun validate(city: String?, country: String?) {
        viewModelScope.launch(context) {
            when {
                TextUtils.isEmpty(country) -> {
                    _errorMessageRes.postValue(R.string.error_enter_country)
                }
                TextUtils.isEmpty(city) -> {
                    _errorMessageRes.postValue(R.string.error_enter_city)
                }
                else -> {
                    if (validateCityCountryPair(city = city!!, country = country!!)) {
                        _savedCity.postValue(city)
                    } else {
                        _errorMessageRes.postValue(R.string.error_valid_pair)
                    }
                }
            }
        }
    }


    @VisibleForTesting
    @WorkerThread
    fun validateCityCountryPair(city: String, country: String): Boolean {
        cityCountryData?.let { map ->
            if (map.containsKey(country)) {
                map.get(key = country)?.let {
                    if (it.contains(city)) {
                        return true
                    }
                }
            }
        }
        return false
    }
}