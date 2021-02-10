package com.rohan.weatherprediction.domain.arch

import kotlinx.coroutines.*

abstract class BaseParamCase<out Type, in Params> {
    abstract suspend fun run(params: Params): Either<Failure, Type>

    open operator fun invoke(
        scope: CoroutineScope,
        params: Params,
        dispatcher: CoroutineDispatcher = Dispatchers.IO,
        onResult: (Either<Failure, Type>) -> Unit = {}
    ) {
        val backgroundJob = scope.async(dispatcher) { run(params) }
        scope.launch { onResult(backgroundJob.await()) }
    }
}
