package com.example.befit

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class GoToGym : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_go_to_gym)

        var btn_gym_start: Button = findViewById(R.id.gym_start)

        btn_gym_start.setOnClickListener {
            startActivity(Intent(this, GymActivities::class.java))

            val timerDialog = TimerDialog()
            timerDialog.show(supportFragmentManager, "")
        }
    }
}
