package com.example.befit

import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.DialogFragment
import com.xw.repo.BubbleSeekBar


class TimerDialog : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val view = LayoutInflater.from(activity).inflate(R.layout.timer_dialog, null)


        val alert = AlertDialog.Builder(activity)

        val seekBarTime: BubbleSeekBar = view.findViewById(R.id.seekbar_timer)
        val seekBarRepetition: BubbleSeekBar = view.findViewById(R.id.seekbar_repetition)

        var time: Int = 1
        var repetition: Int = 3

        seekBarTime.setOnProgressChangedListener(
            object : BubbleSeekBar.OnProgressChangedListener {
                override fun onProgressChanged(progress: Int, progressFloat: Float) {

                    time = progress
                }

                override fun getProgressOnActionUp(progress: Int, progressFloat: Float) {

                }

                override fun getProgressOnFinally(progress: Int, progressFloat: Float) {

                }
            })

        seekBarRepetition.setOnProgressChangedListener(
            object : BubbleSeekBar.OnProgressChangedListener {
                override fun onProgressChanged(progress: Int, progressFloat: Float) {

                    repetition = progress
                }

                override fun getProgressOnActionUp(progress: Int, progressFloat: Float) {

                }

                override fun getProgressOnFinally(progress: Int, progressFloat: Float) {

                }
            })

        with(alert) {
            setView(view)
            setTitle(getString(R.string.title_time_dialog))
            setPositiveButton(getString(R.string.start_time_dialog)) { _, _ ->
                onPositiveClick(
                    time,
                    repetition
                )
            }
        }
        return alert.create()
    }


    private fun onPositiveClick(time: Int, repetition: Int) {
        val i = Intent(requireActivity().baseContext, GymActivities::class.java)
        i.putExtra("TIME", time)
        i.putExtra("REPETITION", repetition)
        startActivity(i)
    }
}