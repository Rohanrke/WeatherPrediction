package com.rohan.weatherprediction.data.mapper

interface RemoteToEntityMapper<R,E> {

    fun map(remote: R): E
}