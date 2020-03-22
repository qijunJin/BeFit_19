package com.example.befit

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class GoToOutdoorRunning : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_go_to_outdoor_running)

        var start_outdoor: Button = findViewById(R.id.start_outdoor)

        start_outdoor.setOnClickListener {
            startActivity(Intent(this, OutdoorRunning::class.java))
        }
    }
}
