package com.example.befit

import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.Toast
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_two.*

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
        var mLinearLayout = inflater.inflate(R.layout.fragment_two,container,false)
        var btn_ubication : Button = mLinearLayout.findViewById(R.id.btn_ubication)
        var btn_notif : Button = mLinearLayout.findViewById(R.id.btn_notification)
        var btn_monday : RadioButton = mLinearLayout.findViewById(R.id.btn_Monday)

       //Indiquem el color per defecte dels botons
        btn_ubication.setBackgroundColor(Color.parseColor("#FFFFFF"))
        btn_notif.setBackgroundColor(Color.parseColor("#FFFFFF"))
        
        btn_ubication.setOnClickListener {
            if(btn_ubication.text=="Allow"){
                btn_ubication.text = "Allowed"
                btn_ubication.setBackgroundColor(Color.parseColor("#F6DE05"))
            }else if(btn_ubication.text=="Allowed"){
                btn_ubication.text="Allow"
                btn_ubication.setBackgroundColor(Color.parseColor("#FFFFFF"))
            }
        }

        btn_notif.setOnClickListener {
            if(btn_notif.text=="Allow"){
                btn_notif.text = "Allowed"
                btn_notif.setBackgroundColor(Color.parseColor("#F6DE05"))
            }else if(btn_notif.text=="Allowed"){
                btn_notif.text="Allow"
                btn_notif.setBackgroundColor(Color.parseColor("#FFFFFF"))
            }
        }

        return mLinearLayout  //Retornem el layout despres dels canvis
        //return inflater.inflate(R.layout.fragment_two, container, false)
    }





}
