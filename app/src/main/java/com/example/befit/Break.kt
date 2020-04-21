package com.example.befit

import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class Break : AppCompatActivity(), View.OnClickListener {

    var btn_leave_break: Button? = null
    var formatText: FormatText? = null
    var timerStatus = TimerStatus.STARTED

    lateinit var countDownTimer: CountDownTimer

    var timeLeftInMillis: Long = (1 * 60000).toLong()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_break)

        initBreak()

        startBreak()
    }

    private fun initBreak() {
        btn_leave_break = findViewById(R.id.leave_break)
        btn_leave_break!!.setOnClickListener(this)
        formatText = FormatText(this, R.id.txt_break)
        formatText!!.updateCountDownText(timeLeftInMillis)
    }

    override fun onClick(view: View?) {
        when (view!!.id) {
            R.id.leave_break -> {
                if (timerStatus == TimerStatus.STARTED) {
                    pauseBreak()
                    val builder = AlertDialog.Builder(this)
                    builder.setTitle(getString(R.string.title_attention))
                    builder.setMessage(getString(R.string.message_finish_break))
                    builder.setPositiveButton(getText(R.string.action_yes)) { _, _ -> finish() }
                    builder.setNegativeButton(getText(R.string.action_no)) { _, _ -> finish() }

                    val dialog = builder.create()
                    dialog.show()
                } else {
                    finish()
                }
            }
        }
    }


    private fun startBreak() {
        countDownTimer = object : CountDownTimer(timeLeftInMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timeLeftInMillis = millisUntilFinished
                formatText!!.updateCountDownText(timeLeftInMillis)
            }

            override fun onFinish() {
                finish()
            }
        }.start()
        timerStatus = TimerStatus.STARTED
    }

    fun pauseBreak() {
        countDownTimer.cancel()
        timerStatus = TimerStatus.STOPPED
    }
}
