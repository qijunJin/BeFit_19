package com.example.befit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var btn_signUp : Button = findViewById(R.id.btn_signUp)
        var btn_signIn : Button = findViewById(R.id.btn_SignIn)

    }
}
