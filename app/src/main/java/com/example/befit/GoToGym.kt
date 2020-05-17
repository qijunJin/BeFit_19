package com.example.befit

import android.content.Intent
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

        var intensity_text: TextView = findViewById(R.id.intensity_text)
        var intensity: TextView = findViewById(R.id.intensity)
        var exercisecal: TextView = findViewById(R.id.exercisecal)

        intensity_text.typeface =
            Typeface.createFromAsset(assets, "font/futura-pt-book-oblique.otf")

        intensity.text = exercise.intensity.toString()
        intensity.typeface = Typeface.createFromAsset(assets, "font/futura-pt-heavy.otf")

        exercisecal.text = exercise.calories.toString() + " kcal / minute"
        exercisecal.typeface = Typeface.createFromAsset(assets, "font/futura-pt-demibold.otf")
    }

    override fun onBackPressed() {
        startActivity(Intent(this,Main_Interface::class.java))
    }  //Fa que no puguem tirar enrere desde main INterface
}

