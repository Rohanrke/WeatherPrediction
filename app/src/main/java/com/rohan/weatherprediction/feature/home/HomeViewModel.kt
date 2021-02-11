package com.rohan.weatherprediction.feature.home

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.rohan.weatherprediction.base.presentation.BaseViewModel
import com.rohan.weatherprediction.base.presentation.ProgressState
import com.rohan.weatherprediction.domain.arch.Failure
import com.rohan.weatherprediction.domain.entity.CurrentWeatherEntity
import com.rohan.weatherprediction.domain.entity.ForecastEntity
import com.rohan.weatherprediction.domain.usecase.GetCurrentWeatherUseCase
import com.rohan.weatherprediction.domain.usecase.GetForecastWeatherUseCase
import com.rohan.weatherprediction.preferences.BaseSharedPreferences
import com.rohan.weatherprediction.utils.AppUtils
import com.rohan.weatherprediction.utils.NetworkUtils
import com.rohan.weatherprediction.R
import com.rohan.weatherprediction.config.RemoteConfig
import kotlinx.coroutines.launch
import java.time.DayOfWeek
import kotlin.coroutines.CoroutineContext

private const val HOUR_OF_DAY = "09:00"

class HomeViewModel(
    private val context: CoroutineContext,
    private val currentWeatherUseCase: GetCurrentWeatherUseCase,
    private val forecastWeatherUseCase: GetForecastWeatherUseCase,
    private val baseSharedPreferences: BaseSharedPreferences,
    private val remoteConfig: RemoteConfig
) : BaseViewModel() {


    private val _title = MutableLiveData<String>()
    val title: LiveData<String>
        get() = _title

    var currentCity: String? = null


    private val _currentEntityLiveData = MutableLiveData<CurrentWeatherEntity>()
    val currentEntityLiveData: LiveData<CurrentWeatherEntity>
        get() = _currentEntityLiveData

    private val _forecastEntityLiveData = MutableLiveData<ForecastEntity>()
    val forecastEntityLiveData: LiveData<ForecastEntity>
        get() = _forecastEntityLiveData

    private val _savedCityLiveData = MutableLiveData<String>()
    val savedCityLiveData: LiveData<String>
        get() = _savedCityLiveData

    private val _hasCity = MutableLiveData<Boolean?>()
    val hasCity: LiveData<Boolean?>
        get() = _hasCity

    private val _alertItem = MutableLiveData<AlertItem>()
    val alertItem: LiveData<AlertItem>
        get() = _alertItem


    fun fetchCurrentWeatherData(city: String) {
        viewModelScope.launch(context) {
            _state.postValue(ProgressState.LOADING)
            currentWeatherUseCase.invoke(this, city) {
                it.either(
                    ::onFailure,
                    ::onCurrentWeatherSuccess
                )
            }
        }
    }

    fun fetchForecastWeatherData(city: String) {
        viewModelScope.launch(context) {
            _state.postValue(ProgressState.LOADING)
            forecastWeatherUseCase.invoke(this, city) {
                it.either(
                    ::onFailure,
                    ::onForecastWeatherSuccess
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
    fun onCurrentWeatherSuccess(entity: CurrentWeatherEntity) {
        val min = remoteConfig.getMinTempVariance()
        val max = remoteConfig.getMaxTempVariance()

        entity.let {
            when {
                (it.main?.tempMin) ?: 0.0 <= min -> {
                    AlertItem(messageId = R.string.alert_less_temp, tempString = AppUtils.formatTempValue(it.main?.temp))
                }
                (it.main?.tempMax) ?: 0.0 >= max -> {
                    AlertItem(messageId = R.string.alert_more_temp, tempString = AppUtils.formatTempValue(it.main?.temp))
                }
                else -> {
                    null
                }
            }?.let { item ->
                _alertItem.postValue(item)
            }

        }
        _state.postValue(ProgressState.SUCCESS)
        _currentEntityLiveData.postValue(entity)
    }

    @VisibleForTesting
    fun onForecastWeatherSuccess(entity: ForecastEntity) {

        val min = remoteConfig.getMinTempVariance()
        val max = remoteConfig.getMaxTempVariance()

        entity.list?.sortedBy {
            it.dt

        }?.map {
            it.dayOfWeek = AppUtils.getDateTime(it.dt) ?: DayOfWeek.MONDAY
            if ((it.main?.tempMin) ?: 0.0 <= min) {
                it.tempMinVariance = true
            } else if ((it.main?.tempMax) ?: 0.0 >= max) {
                it.tempMaxVariance = true
            }
        }
        val uniqueList = entity.list?.filter {
            it.getHourOfDay() == HOUR_OF_DAY
        }?.distinctBy {
            it.dayOfWeek
        }
        entity.list = uniqueList
        _state.postValue(ProgressState.SUCCESS)
        _forecastEntityLiveData.postValue(entity)
    }


    fun updateCity(city: String) {
        currentCity = city
        baseSharedPreferences.saveCity(city)
        _hasCity.postValue(true)
        updateTitle(city)
        fetchCurrentWeatherData(city)
        fetchForecastWeatherData(city)
    }

    fun getSavedCity() {
        viewModelScope.launch(context) {
            baseSharedPreferences.getSavedCity()?.let {
                currentCity = it
                _title.postValue(it)
                _savedCityLiveData.postValue(it)
                _hasCity.postValue(true)
            } ?: kotlin.run {
                _hasCity.postValue(false)
            }

        }
    }

    fun updateTitle(titleString: String) {
        _title.postValue(titleString)
    }

    fun refresh() {
        currentCity?.let {
            _messageRes.postValue(R.string.refreshing)
            fetchCurrentWeatherData(it)
            fetchForecastWeatherData(it)
        }
    }
}