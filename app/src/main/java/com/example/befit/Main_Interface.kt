package com.example.befit

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class Main_Interface : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_interface)

        var btn_Gym: Button = findViewById(R.id.btn_Gym)

        btn_Gym.setOnClickListener {
            startActivity(Intent(this, GoToGym::class.java))
        }

        var btn_Outdoor: Button = findViewById(R.id.btn_Outdoor)

        btn_Outdoor.setOnClickListener {
            startActivity(Intent(this, GoToOutdoorRunning::class.java))
        }

        var btn_statistics: ImageButton = findViewById(R.id.btn_Statistics)

        btn_statistics.setOnClickListener {
            startActivity(Intent(this, Statistics::class.java))
        }
    }
}

