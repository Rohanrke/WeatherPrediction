package com.rohan.weatherprediction.domain.arch

data class Failure(
    val code: Int?,
    val message: String,
    val exception: Exception? = null
)
