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
import kotlinx.coroutines.launch
import java.time.DayOfWeek
import kotlin.coroutines.CoroutineContext

class HomeViewModel(
    private val context: CoroutineContext,
    private val currentWeatherUseCase: GetCurrentWeatherUseCase,
    private val forecastWeatherUseCase: GetForecastWeatherUseCase,
    private val baseSharedPreferences: BaseSharedPreferences,
    private val networkUtils: NetworkUtils
): BaseViewModel() {


    private val _title = MutableLiveData<String>()
    val title: LiveData<String>
        get() = _title



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
    private fun onCurrentWeatherSuccess(entity: CurrentWeatherEntity) {
        _state.postValue(ProgressState.SUCCESS)
        _currentEntityLiveData.postValue(entity)
    }

    @VisibleForTesting
    private fun onForecastWeatherSuccess(entity: ForecastEntity) {
        entity.list?.sortedBy {
            it.dt

        }?.map {
            it.dayOfWeek = AppUtils.getDateTime(it.dt) ?: DayOfWeek.MONDAY
        }

        val uniqueList = entity.list?.distinctBy {
            it.dayOfWeek
        }
        entity.list = uniqueList
        _state.postValue(ProgressState.SUCCESS)
        _forecastEntityLiveData.postValue(entity)
    }


    fun updateCity(city: String){
        baseSharedPreferences.saveCity(city)
        _hasCity.postValue(true)
        updateTitle(city)
        fetchCurrentWeatherData(city)
        fetchForecastWeatherData(city)
    }

    fun getSavedCity(){
        viewModelScope.launch (context){
            baseSharedPreferences.getSavedCity()?.let {
                _title.postValue(it)
                _savedCityLiveData.postValue(it)
                _hasCity.postValue(true)
            }?: kotlin.run {
                _hasCity.postValue(false)
            }

        }
    }

    fun updateTitle(titleString: String) {
        _title.postValue(titleString)
    }

}