package com.aliduman.countries.util

import android.content.Context
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.aliduman.countries.R
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

//Extension

fun ImageView.getImageWithGlide(url: String?, progressDrawable: CircularProgressDrawable) {

    val options = RequestOptions()
        .placeholder(progressDrawable) //inene kadar ne göstereceğiz..
        .error(R.mipmap.ic_launcher_round)//hata cikarsa bunu göster..

    Glide.with(context)
        .setDefaultRequestOptions(options)
        .load(url)
        .into(this)

}

fun placeholderProgressBar(context: Context) : CircularProgressDrawable{
    return CircularProgressDrawable(context).apply {
        strokeWidth = 8f
        centerRadius = 40f
        start()
    }
}

@BindingAdapter("android:downloadUrl")
fun downloadImage(view: ImageView, url: String?) {
    url?.let {
        view.getImageWithGlide(it, placeholderProgressBar(view.context))
    }
}


/*
fun String.myExtension(myParameter : String) {
    println(myParameter)
    }
 */

