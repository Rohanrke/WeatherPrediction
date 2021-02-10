package com.rohan.weatherprediction.base.presentation

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide


@BindingAdapter("visible")
fun setVisibility(view: View, isVisible: Boolean?) {
    view.visibility = if (isVisible == true) View.VISIBLE else View.GONE
}

@BindingAdapter("imageUri")
fun loadImage(imageView: ImageView, imageUri: String?) {
    imageUri?.let {
        Glide.with(imageView.context).load(it).into(imageView)
    }
}
