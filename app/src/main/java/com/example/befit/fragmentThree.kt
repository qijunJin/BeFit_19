package com.example.befit

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment

/**
 * A simple [Fragment] subclass.
 */
class fragmentThree : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var mLinearLayout = inflater.inflate(R.layout.fragment_three, container, false)
        var btn_med = mLinearLayout.findViewById<Button>(R.id.btn_mediterranean)
        var btn_oriental = mLinearLayout.findViewById<Button>(R.id.btn_oriental)
        var btn_american = mLinearLayout.findViewById<Button>(R.id.btn_american)
        var btn_halal = mLinearLayout.findViewById<Button>(R.id.btn_halal)
        var btn_mex = mLinearLayout.findViewById<Button>(R.id.btn_mexican)
        var btn_veg = mLinearLayout.findViewById<Button>(R.id.btn_vegetarian)

        var act_med = false
        var act_ori = false
        var act_ame = false
        var act_hal = false
        var act_mex = false
        var act_veg = false

        btn_med.setOnClickListener {
            btn_med.setBackgroundColor(Color.parseColor(if (!act_med) "#F6DE05" else "#FFFFFF"))
            act_med = !act_med
        }

        btn_oriental.setOnClickListener {
            btn_oriental.setBackgroundColor(Color.parseColor(if (!act_ori) "#F6DE05" else "#FFFFFF"))
            act_ori = !act_ori
        }

        btn_american.setOnClickListener {
            btn_american.setBackgroundColor(Color.parseColor(if (!act_ame) "#F6DE05" else "#FFFFFF"))
            act_ame = !act_ame
        }

        btn_halal.setOnClickListener {
            btn_halal.setBackgroundColor(Color.parseColor(if (!act_hal) "#F6DE05" else "#FFFFFF"))
            act_hal = !act_hal
        }

        btn_mex.setOnClickListener {
            btn_mex.setBackgroundColor(Color.parseColor(if (!act_mex) "#F6DE05" else "#FFFFFF"))
            act_mex = !act_mex
        }

        btn_veg.setOnClickListener {
            btn_veg.setBackgroundColor(Color.parseColor(if (!act_veg) "#F6DE05" else "#FFFFFF"))
            act_veg = !act_veg
        }

        // Inflate the layout for this fragment
        return mLinearLayout
        //return inflater.inflate(R.layout.fragment_three, container, false)
    }

}
