package com.gmail.eamosse.imdb.glide

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions


object BidingAdapters {
    @BindingAdapter("app:bindImage")
    @JvmStatic
    fun changeImage(view: AppCompatImageView, url: String?) {
        if(url == null) return
        Glide.with(view).load("https://image.tmdb.org/t/p/w500/$url")
            .centerCrop()
            .into(view)
    }
    @BindingAdapter("app:bindImageRounded")
    @JvmStatic
    fun changeImageRounded(view: AppCompatImageView, url: String?) {
        if(url == null) return
        var requestOptions = RequestOptions()
        requestOptions = requestOptions.transforms(CenterCrop(), RoundedCorners(300));

        Glide.with(view).load("https://image.tmdb.org/t/p/w500/$url")
            .apply(requestOptions)
            .into(view)
    }
}