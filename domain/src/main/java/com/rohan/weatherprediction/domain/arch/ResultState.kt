package com.rohan.weatherprediction.domain.arch

sealed class ResultState<T> {
    data class Success<T>(val data: T) : ResultState<T>()
    data class Error<T>(val failure: Failure) : ResultState<T>()
}

fun <T> ResultState<T>.result(): Either<Failure, T> {
    return if (this is ResultState.Success)
        Either.Right(this.data)
    else
        Either.Left((this as ResultState.Error).failure)
}
