package com.example.befit

import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.DialogFragment
import com.xw.repo.BubbleSeekBar
import java.util.*


class TimerDialog : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val view = LayoutInflater.from(activity).inflate(R.layout.timer_dialog, null)
        val alert = AlertDialog.Builder(activity)

        val seekBarTime: BubbleSeekBar = view.findViewById(R.id.seekbar_timer)
        val seekBarRepetition: BubbleSeekBar = view.findViewById(R.id.seekbar_repetition)

        var time: Int = 0
        var repetition: Int = 0

        seekBarTime.setOnProgressChangedListener(
            object : BubbleSeekBar.OnProgressChangedListener {
                override fun onProgressChanged(progress: Int, progressFloat: Float) {
                    TODO("Not yet implemented")
                    time = progress
                }

                override fun getProgressOnActionUp(progress: Int, progressFloat: Float) {
                    TODO("Not yet implemented")
                }

                override fun getProgressOnFinally(progress: Int, progressFloat: Float) {
                    TODO("Not yet implemented")
                }
            })

        seekBarRepetition.setOnProgressChangedListener(
            object : BubbleSeekBar.OnProgressChangedListener {
                override fun onProgressChanged(progress: Int, progressFloat: Float) {
                    TODO("Not yet implemented")
                    repetition = progress
                }

                override fun getProgressOnActionUp(progress: Int, progressFloat: Float) {
                    TODO("Not yet implemented")
                }

                override fun getProgressOnFinally(progress: Int, progressFloat: Float) {
                    TODO("Not yet implemented")
                }
            })

        with(alert) {
            setView(view)
            setTitle("Configure")
            setPositiveButton("Iniciar") { _, _ ->
                onPositiveClick(time, repetition)
            }
        }
        return alert.create()
    }

    private fun onPositiveClick(time: Int, repetition: Int) {
        val i = Intent(activity!!.baseContext, Timer::class.java)
        i.putExtra("TIME", time)
        i.putExtra("REPETITION", repetition)
        startActivity(i)
    }
}