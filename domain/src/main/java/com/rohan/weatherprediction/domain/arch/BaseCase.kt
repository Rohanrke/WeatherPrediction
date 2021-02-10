package com.rohan.weatherprediction.domain.arch

import kotlinx.coroutines.*

abstract class BaseCase<out Type> {
    abstract suspend fun run(): Either<Failure, Type>

    open operator fun invoke(
        scope: CoroutineScope,
        dispatcher: CoroutineDispatcher = Dispatchers.IO,
        onResult: (Either<Failure, Type>) -> Unit = {}
    ) {
        val backgroundJob = scope.async(dispatcher) { run() }
        scope.launch { onResult(backgroundJob.await()) }
    }
}