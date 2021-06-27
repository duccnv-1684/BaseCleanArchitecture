package com.ducnguyen2102.baseclean.util.extension

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.ducnguyen2102.baseclean.R

fun ImageView.loadCircleImage(url: String?) = url?.let {
    Glide.with(context)
        .load(it)
        .apply(RequestOptions.circleCropTransform())
        .into(this)
}

fun ImageView.loadImage(url: String?) = url?.let {
    Glide.with(context)
        .load(it)
        .apply(RequestOptions.centerCropTransform())
        .placeholder(R.drawable.ic_loading)
        .error(R.drawable.ic_load_error)
        .into(this)
}

fun View.setSingleClickListener(hiddenKeyboard: Boolean? = false, singleClick: (() -> Unit)) {
    setOnClickListener {
        singleClick.invoke()
        isClickable = false
        when (hiddenKeyboard) {
            true -> context.showSoftKeyboard(false)
        }

        postDelayed({
            isClickable = true
        }, 300L)
    }
}

fun View.hideKeyboard() {
    val inputMethod: InputMethodManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethod.hideSoftInputFromWindow(windowToken, 0)
}

fun View.showKeyboard() {
    val inputMethod: InputMethodManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethod.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
}
