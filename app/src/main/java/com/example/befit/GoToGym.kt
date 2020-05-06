package com.example.befit

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class GoToGym : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_go_to_gym)

        var btn_gym_start: Button = findViewById(R.id.gym_start)

        btn_gym_start.setOnClickListener {
            val timerDialog = TimerDialog()
            timerDialog.show(supportFragmentManager, "")
        }

        var exercise = intent.getSerializableExtra("EXERCISE") as Exercise

        var imgId: ImageView = findViewById(R.id.imgId)
        var exerciseName: TextView = findViewById(R.id.exerciseName)

        imgId.setImageResource(exercise.imgId)
        exerciseName.text = exercise.exerciseName
    }
}
