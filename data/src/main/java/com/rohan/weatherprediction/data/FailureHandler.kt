package com.rohan.weatherprediction.data

import com.rohan.weatherprediction.domain.arch.Failure
import java.net.UnknownHostException

const val CODE_NO_INTERNET = 1
const val UNKNOWN_ERROR = 2

class FailureHandler {
    fun handleException(exception: Exception): Failure {
        return when (exception) {
            is UnknownHostException -> Failure(
                CODE_NO_INTERNET,
                "Please check your internet connection and try again",
                exception
            )
            else -> Failure(UNKNOWN_ERROR, "Something went wrong, Please try again !", exception)
        }
    }
}