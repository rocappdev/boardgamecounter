package com.jmyp.boardgamecounter.utils

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import java.text.SimpleDateFormat
import java.util.*

fun ImageView.loadUrl(url: String) {
    Glide.with(context).load(url).into(this)
}

fun View.setSafeOnClickListener(onSafeClick: (View) -> Unit) {
    val safeClickListener = SafeClickListener {
        onSafeClick(it)
    }
    setOnClickListener(safeClickListener)
}

fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide() {
    visibility = View.GONE
}

fun convertLongToTime(time: Long): String {
    val date = Date(time)
//    val format = SimpleDateFormat("dd/MM/yyyy")
    val format = SimpleDateFormat("d MMMM")
    return format.format(date)
}
