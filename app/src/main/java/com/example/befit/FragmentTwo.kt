package com.example.befit

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import androidx.fragment.app.Fragment

/**
 * A simple [Fragment] subclass.
 */
class FragmentTwo : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        //Interacció amb els botons del fragment
        var mLinearLayout = inflater.inflate(R.layout.fragment_two, container, false)
        var btn_ubication: Button = mLinearLayout.findViewById(R.id.btn_ubication)
        var btn_notif: Button = mLinearLayout.findViewById(R.id.btn_notification)
        var rbtn_mon: RadioButton = mLinearLayout.findViewById(R.id.btn_Monday)
        var rbtn_tue: RadioButton = mLinearLayout.findViewById(R.id.btn_Tuesday)
        var rbtn_wed: RadioButton = mLinearLayout.findViewById(R.id.btn_Wednesday)
        var rbtn_thu: RadioButton = mLinearLayout.findViewById(R.id.btn_Thursday)
        var rbtn_fri: RadioButton = mLinearLayout.findViewById(R.id.btn_Friday)
        var rbtn_sat: RadioButton = mLinearLayout.findViewById(R.id.btn_Saturday)
        var rbtn_sun: RadioButton = mLinearLayout.findViewById(R.id.btn_Sunday)

        //Indiquem el color per defecte dels botons
        btn_ubication.setBackgroundColor(Color.parseColor("#FFFFFF"))
        btn_notif.setBackgroundColor(Color.parseColor("#FFFFFF"))

        //variables per controlar si el radiobutton està polsat o no i poder variar el seu estat
        var act_mon = false
        var act_tue = false
        var act_wed = false
        var act_thu = false
        var act_fri = false
        var act_sat = false
        var act_sun = false

        //Interaccions amb els radio buton
        rbtn_mon.setOnClickListener {
            rbtn_mon.isChecked = !act_mon
            rbtn_mon.setTextColor(Color.parseColor(if (!act_mon) "#F6DE05" else "#FFFFFF"))
            act_mon = !act_mon
        }
        rbtn_tue.setOnClickListener {
            rbtn_tue.isChecked = !act_tue
            rbtn_tue.setTextColor(Color.parseColor(if (!act_tue) "#F6DE05" else "#FFFFFF"))
            act_tue = !act_tue
        }
        rbtn_wed.setOnClickListener {
            rbtn_wed.isChecked = !act_wed
            rbtn_wed.setTextColor(Color.parseColor(if (!act_wed) "#F6DE05" else "#FFFFFF"))
            act_wed = !act_wed
        }
        rbtn_thu.setOnClickListener {
            rbtn_thu.isChecked = !act_thu
            rbtn_thu.setTextColor(Color.parseColor(if (!act_thu) "#F6DE05" else "#FFFFFF"))
            act_thu = !act_thu
        }
        rbtn_fri.setOnClickListener {
            rbtn_fri.isChecked = !act_fri
            rbtn_fri.setTextColor(Color.parseColor(if (!act_fri) "#F6DE05" else "#FFFFFF"))
            act_fri = !act_fri
        }
        rbtn_sat.setOnClickListener {
            rbtn_sat.isChecked = !act_sat
            rbtn_sat.setTextColor(Color.parseColor(if (!act_sat) "#F6DE05" else "#FFFFFF"))
            act_sat = !act_sat
        }
        rbtn_sun.setOnClickListener {
            rbtn_sun.isChecked = !act_sun
            rbtn_sun.setTextColor(Color.parseColor(if (!act_sun) "#F6DE05" else "#FFFFFF"))
            act_sun = !act_sun
        }

        btn_ubication.setOnClickListener {//Meter en un mètodo a parte
            //btn_ubication.text = if (btn_ubication.isPressed) "Allowed" else "Allow"
            btn_ubication.text = if (btn_ubication.text == "Allow") "Allowed" else "Allow"
            btn_ubication.setBackgroundColor(Color.parseColor(if (btn_ubication.text == "Allowed") "#F6DE05" else "#FFFFFF"))
        }

        btn_notif.setOnClickListener {
            btn_notif.text = if (btn_notif.text == "Allow") "Allowed" else "Allow"
            btn_notif.setBackgroundColor(Color.parseColor(if (btn_notif.text == "Allowed") "#F6DE05" else "#FFFFFF"))
        }

        return mLinearLayout  //Retornem el layout despres dels canvis
        //return inflater.inflate(R.layout.fragment_two, container, false)
    }


}
