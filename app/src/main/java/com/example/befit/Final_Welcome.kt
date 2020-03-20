package com.example.befit

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Final_Welcome : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_final_welcome)
        Toast.makeText(this, "User created", Toast.LENGTH_SHORT).show()

        var btn_welcome_start: Button = findViewById(R.id.btn_welcome_start)

        btn_welcome_start.setOnClickListener {
            val intent = Intent(this, Main_Interface::class.java)
            startActivity(intent)
        }
    }
}
