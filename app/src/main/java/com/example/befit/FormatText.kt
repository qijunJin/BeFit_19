package com.example.befit

import android.app.Activity
import android.content.Context
import android.graphics.Typeface
import android.widget.TextView
import java.util.*

class FormatText(context: Context, text_time: Int) {

    var txt_time: TextView = (context as Activity).findViewById(text_time)
    val tp: Typeface = Typeface.createFromAsset(context.assets, "font/futura-pt-demibold.otf")

    fun updateCountDownText(timeLeftInMillis: Long) {
        val minutes: Int = (timeLeftInMillis.toInt() / 1000) / 60
        val seconds: Int = (timeLeftInMillis.toInt() / 1000) % 60
        val timeLeftFormatted: String =
            java.lang.String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds)
        txt_time.text = timeLeftFormatted
        txt_time.typeface = tp
    }
}