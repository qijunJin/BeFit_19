package com.example.befit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_one.*

class Final_Welcome : AppCompatActivity() {
lateinit var welcome_name : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_final__welcome)
        Toast.makeText(this, "User created", Toast.LENGTH_SHORT).show()


    }
}
