package com.example.befit

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class GoToGym : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_go_to_gym)

        var gym_start: Button = findViewById(R.id.gym_start)

        gym_start.setOnClickListener {
            val intent = Intent(this, gym_activities::class.java)
            startActivity(intent)
        }
    }
}
