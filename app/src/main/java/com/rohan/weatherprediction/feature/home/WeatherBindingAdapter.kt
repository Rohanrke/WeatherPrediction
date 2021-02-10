package com.rohan.weatherprediction.feature.home

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.loopeer.shadow.ShadowView
import com.rohan.weatherprediction.utils.AppUtils
import com.rohan.weatherprediction.R
import java.time.format.TextStyle
import java.util.*


@BindingAdapter("bgColorTint")
fun setBackgroundColor(view: View, dt: Long){
    view.apply {
        backgroundTintList = ContextCompat.getColorStateList(view.context, AppUtils.getColor(dt))
    }
}

@BindingAdapter("weatherIcon")
fun setWeatherIcon(view: ImageView, imageUri: String?) {
    imageUri?.let {
        val newPath = imageUri.replace(imageUri, "a$imageUri")
        val imageid = view.context.resources.getIdentifier(newPath + "_svg", "drawable", view.context.packageName)
        val imageDrawable = view.context.resources.getDrawable(imageid, null)
        view.setImageDrawable(imageDrawable)
    }
}

@BindingAdapter("temp")
fun setTemperature(view: TextView, temp: Double?){
    temp?.let {
        view.text = "${it.toString().substringBefore(".")} °"
    }
}


@BindingAdapter("humidity")
fun setHumidity(view: TextView, humidity: Int?){
    humidity?.let {
        view.text = view.context.getString(R.string.text_humidity, it)
    }
}

@BindingAdapter("dt")
fun shadowColor(view: ShadowView, dt: Long?){
    view.shadowColor = AppUtils.getColor(dt)
}

@BindingAdapter("dayDt")
fun setDay(view: TextView, dt: Long?) {
    dt?.let {
        view.text = AppUtils.getDateTime(it)?.getDisplayName(TextStyle.FULL, Locale.getDefault())
    }

}


