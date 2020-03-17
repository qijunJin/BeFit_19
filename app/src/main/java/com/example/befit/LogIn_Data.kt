package com.example.befit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import androidx.viewpager.widget.ViewPager

class LogIn_Data : AppCompatActivity() {
    lateinit var mSlideViewPager : ViewPager
    lateinit var mDotLayout : LinearLayout
    lateinit var btn_next : Button
    lateinit var btn_back :Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mSlideViewPager = findViewById(R.id.slidePager)
        mDotLayout = findViewById(R.id.dotsLayout)

        btn_next=findViewById(R.id.btn_next)  //De moment utilitzem el botó next per canviar a la ultima activity del logIn. Després nomes servirà per fer canviar de slide
        btn_back=findViewById(R.id.btn_back)

        btn_next.setOnClickListener {
            val intent : Intent = Intent(this,Final_Welcome::class.java)
            startActivity(intent)
        }

        btn_back.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
    }
}
