package com.example.befit

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var btn_LogIn: Button = findViewById(R.id.btn_LogIn)

        btn_LogIn.setOnClickListener {
            startActivity(Intent(this, LogIn_Data::class.java))
        }

    }


}
