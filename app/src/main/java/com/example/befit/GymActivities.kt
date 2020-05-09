package com.example.befit

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class GymActivities : AppCompatActivity() {

    var txt_repetition: TextView? = null

    var play: ImageButton? = null

    var progress: ProgressBar? = null
    var timerLength: Float? = null

    var repetition: Int = 1
    var totalRepetition: Int? = null

    var formatText: FormatText? = null

    private var timerStatus = TimerStatus.STARTED

    var timeLeftInMillis: Long = 0

    var exercise: Exercise? = null

    private var imgId: ImageView? = null

    lateinit var countDownTimer: CountDownTimer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gym_activities)

        init()
        getData()
        exercise = intent.getSerializableExtra("EXERCISESELECTED") as Exercise
        imgId?.setBackgroundResource(exercise!!.imgId)
        initTimer()

    }

    private fun initTimer() {
        play!!.setOnClickListener {
            if (timerStatus == TimerStatus.STARTED) {
                pauseTimer()
            } else {
                startTimer()
            }
        }

        if (totalRepetition == 0) {
            txt_repetition!!.visibility = View.GONE

        } else {
            txt_repetition!!.setText(repetition.toString() + "/" + totalRepetition.toString())
        }

        timeLeftInMillis = (timerLength!! * 60000).toLong()

        formatText = FormatText(this, R.id.txt_time)
        formatText!!.updateCountDownText(timeLeftInMillis)
        setProgressBarValues()

        startTimer()

    }


    private fun init() {
        txt_repetition = findViewById(R.id.txt_repetition)
        progress = findViewById(R.id.chronoProgressBar)
        play = findViewById(R.id.play)
        imgId = findViewById(R.id.imgId)
    }

    private fun getData() {
        timerLength = intent.getFloatExtra("TIME", 0.1F)
        totalRepetition = intent.getIntExtra("REPETITION", 0)


    }

    private fun setProgressBarValues() {
        progress!!.max = timeLeftInMillis.toInt() / 1000
        progress!!.progress = timeLeftInMillis.toInt() / 1000
    }

    fun statusBreak() {
        if (repetition == totalRepetition) {
            val builder = AlertDialog.Builder(this)
            builder.setTitle(getString(R.string.title_finish_time))
            builder.setMessage(getString(R.string.message_finish_time))
            //builder.setPositiveButton(getString(R.string.action_ok)) { _, _ -> finish() }
            builder.setPositiveButton("OK") { _, _ ->
                exercise?.let {
                    onPositiveClick(
                        it
                    )
                }
            }
            val dialog = builder.create()
            dialog.show()
        } else {
            val intent = Intent(this, Break::class.java)
            startActivityForResult(intent, 0)
        }
    }

    private fun onPositiveClick(e: Exercise) {
        val i = Intent(this.baseContext, Main_Interface::class.java)
        i.putExtra("EXERCISEFINISH", e)
        startActivity(i)
    }

    private fun startTimer() {
        countDownTimer = object : CountDownTimer(timeLeftInMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {

                timeLeftInMillis = millisUntilFinished
                play!!.setImageResource(R.drawable.pause)
                formatText!!.updateCountDownText(timeLeftInMillis)
                progress!!.progress = (millisUntilFinished / 1000).toInt()
            }

            override fun onFinish() {

                timerStatus = TimerStatus.STOPPED
                resetTimer()
                statusBreak()
            }

        }.start()
        timerStatus = TimerStatus.STARTED
    }

    private fun pauseTimer() {
        countDownTimer.cancel()
        timerStatus = TimerStatus.STOPPED
        play!!.setImageResource(R.drawable.play)
    }

    private fun resetTimer() {
        pauseTimer()
        timeLeftInMillis = (timerLength!! * 60000).toLong()
        setProgressBarValues()
        formatText!!.updateCountDownText(timeLeftInMillis)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onBackPressed() {
        pauseTimer()

        val builder = AlertDialog.Builder(this)
        builder.setTitle(getString(R.string.title_attention))
        builder.setMessage(getString(R.string.message_finish_timer))
        builder.setPositiveButton(getString(R.string.action_yes)) { _, _ -> finish() }
        builder.setNegativeButton(getString(R.string.action_no)) { _, _ -> startTimer() }

        val dialog = builder.create()
        dialog.show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 0) {
            repetition++
            txt_repetition!!.text = repetition.toString() + "/" + totalRepetition.toString()
            startTimer()
        }
    }


}
