package com.rohan.weatherprediction.utils

import java.text.SimpleDateFormat
import java.time.DayOfWeek
import java.time.LocalDate
import java.util.*
import com.rohan.weatherprediction.R
import java.text.DecimalFormat

object AppUtils {

    fun getDateTime(s: Long?): DayOfWeek? {
        if (s != null) {
            return try {
                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.US)
                val netDate = Date(s * 1000)
                val formattedDate = sdf.format(netDate)

                LocalDate.of(
                    formattedDate.substringAfterLast("/").toInt(),
                    formattedDate.substringAfter("/").take(2).toInt(),
                    formattedDate.substringBefore("/").toInt()
                )
                    .dayOfWeek
            } catch (e: Exception) {
                e.printStackTrace()
                DayOfWeek.MONDAY
            }
        }

        return DayOfWeek.MONDAY
    }

    fun getColor(dt: Long?): Int {
        return when (dt?.let { getDateTime(it) }) {
            DayOfWeek.MONDAY -> R.color.tint_color_monday
            DayOfWeek.TUESDAY -> R.color.tint_color_tuesday
            DayOfWeek.WEDNESDAY -> R.color.tint_color_wednesday
            DayOfWeek.THURSDAY -> R.color.tint_color_thursday
            DayOfWeek.FRIDAY -> R.color.tint_color_friday
            DayOfWeek.SATURDAY -> R.color.tint_color_saturday
            DayOfWeek.SUNDAY -> R.color.tint_color_sunday
            else -> R.color.tint_color_monday
        }
    }

    fun formatTempValue(temp: Double?): String {
        val df: DecimalFormat = DecimalFormat("##.##");
        temp?.let {
            return df.format(it)
        }
        return ""
    }
}