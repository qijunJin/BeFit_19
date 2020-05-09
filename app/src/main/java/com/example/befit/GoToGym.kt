package com.example.befit

import android.graphics.Typeface
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class GoToGym : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_go_to_gym)

        var btn_gym_start: Button = findViewById(R.id.gym_start)

        btn_gym_start.typeface = Typeface.createFromAsset(assets, "font/futura-pt-heavy.otf")

        var exercise = intent.getSerializableExtra("EXERCISE") as Exercise

        btn_gym_start.setOnClickListener {
            val timerDialog = TimerDialog(exercise)
            timerDialog.show(supportFragmentManager, "")
        }

        var imgId: LinearLayout = findViewById(R.id.imgId)
        var exerciseName: TextView = findViewById(R.id.exerciseName)

        imgId.setBackgroundResource(exercise.imgId)
        exerciseName.text = exercise.exerciseName
        exerciseName.typeface = Typeface.createFromAsset(assets, "font/futura-pt-heavy.otf")
    }
}

