package com.rohan.weatherprediction.data.di

import com.google.gson.GsonBuilder
import com.rohan.weatherprediction.data.BuildConfig
import com.rohan.weatherprediction.data.remote.service.WeatherService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun retrofitModule() = module {
    single { provideLoggingInterceptor() }
    single { provideOkHttpClient(get()) }
    single {
        provideRetrofit(
            BuildConfig.BASE_URL,
            get(),
            GsonConverterFactory.create(GsonBuilder().create())
        )
    }
    single { provideWeatherService(get()) }
}

/*
 * The method returns the Retrofit object
 * */
fun provideRetrofit(
    baseUrl: String,
    okHttpClient: OkHttpClient.Builder,
    factory: GsonConverterFactory
): Retrofit {
    return Retrofit.Builder().client(okHttpClient.build())
        .addConverterFactory(factory)
        .baseUrl(baseUrl)
        .build()
}


fun provideLoggingInterceptor(): HttpLoggingInterceptor =
    HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient.Builder {
    val httpClient = OkHttpClient.Builder()
    httpClient.addInterceptor(httpLoggingInterceptor)
    return httpClient
}

fun provideWeatherService(retrofit: Retrofit): WeatherService =
    retrofit.create(WeatherService::class.java)

