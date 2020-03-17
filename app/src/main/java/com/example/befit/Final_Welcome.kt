package com.example.befit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class Final_Welcome : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_final__welcome)
        Toast.makeText(this, "User created", Toast.LENGTH_SHORT).show()

    }
}
