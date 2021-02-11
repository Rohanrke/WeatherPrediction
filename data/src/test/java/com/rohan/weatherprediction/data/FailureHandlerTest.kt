package com.rohan.weatherprediction.data

import com.google.gson.JsonParseException
import com.rohan.weatherprediction.domain.arch.Failure
import org.junit.Assert
import org.junit.Test
import java.net.UnknownHostException

class FailureHandlerTest {
    private val failureHandler = FailureHandler()

    @Test
    fun `handleException should return internet error when UnknownHostException`() {
        val exception = UnknownHostException("unable to connect")
        val expected = Failure(
            CODE_NO_INTERNET,
            "Please check your internet connection and try again",
            exception
        )
        val actual = failureHandler.handleException(exception)
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `handleException should return something went wrong for other`() {
        val exception = JsonParseException("Json format incorrect")
        val expected = Failure(UNKNOWN_ERROR, "Something went wrong, Please try again !", exception)
        val actual = failureHandler.handleException(exception)
        Assert.assertEquals(expected, actual)
    }
}