package com.example.befit

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import androidx.viewpager.widget.PagerAdapter

class SliderAdapter : PagerAdapter() {

    lateinit var context: Context
    lateinit var layoutInflater: LayoutInflater

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        TODO("Not yet implemented")
    }

    override fun getCount(): Int {
        TODO("Not yet implemented")
    }
}