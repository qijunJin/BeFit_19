package com.example.befit

import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.provider.MediaStore
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
        var rbtn_monday : RadioButton = mLinearLayout.findViewById(R.id.btn_Monday)
        var rbtn_tuesday : RadioButton = mLinearLayout.findViewById(R.id.btn_Tuesday)
        var rbtn_wed : RadioButton = mLinearLayout.findViewById(R.id.btn_Wednesday)
        var rbtn_Thu : RadioButton = mLinearLayout.findViewById(R.id.btn_Thursday)
        var rbtn_fri : RadioButton = mLinearLayout.findViewById(R.id.btn_Friday)
        var rbtn_sat : RadioButton = mLinearLayout.findViewById(R.id.btn_Saturday)
        var rbtn_sun : RadioButton = mLinearLayout.findViewById(R.id.btn_Sunday)

       //Indiquem el color per defecte dels botons
        btn_ubication.setBackgroundColor(Color.parseColor("#FFFFFF"))
        btn_notif.setBackgroundColor(Color.parseColor("#FFFFFF"))

        //variables per controlar si el radiobutton està polsat o no i poder variar el seu estat
        var activate_m :Boolean = false
        var activate_tues = false
        var act_wed = false
        var act_thu = false
        var act_fri = false
        var act_sat = false
        var act_sun = false
        //Interaccions amb els radio buton
        rbtn_monday.setOnClickListener {
            if(activate_m==false){
                rbtn_monday.isChecked=true
                activate_m=true
            }else if(activate_m==true){
                rbtn_monday.isChecked=false
                activate_m=false
            }
        }
        rbtn_tuesday.setOnClickListener {
            if(activate_tues==false){
                rbtn_tuesday.isChecked=true
                activate_tues=true
            }else if(activate_tues==true){
                rbtn_tuesday.isChecked=false
                activate_tues=false
            }
        }
        rbtn_wed.setOnClickListener {
            if(act_wed==false){
                rbtn_wed.isChecked=true
                act_wed=true
            }else if(act_wed==true){
                rbtn_wed.isChecked=false
                act_wed=false
            }
        }
        rbtn_Thu.setOnClickListener {
            if(act_thu==false){
                rbtn_Thu.isChecked=true
                act_thu=true
            }else if(act_thu==true){
                rbtn_Thu.isChecked=false
                act_thu=false
            }
        }
        rbtn_fri.setOnClickListener {
            if(act_fri==false){
                rbtn_fri.isChecked=true
                act_fri=true
            }else if(act_fri==true){
                rbtn_fri.isChecked=false
                act_fri=false
            }
        }
        rbtn_sat.setOnClickListener {
            if(act_sat==false){
                rbtn_sat.isChecked=true
                act_sat=true
            }else if(act_sat==true){
                rbtn_sat.isChecked=false
                act_sat=false
            }
        }
        rbtn_sun.setOnClickListener {
            if(act_sun==false){
                rbtn_sun.isChecked=true
                act_sun=true
            }else if(act_sun==true){
                rbtn_sun.isChecked=false
                act_sun=false
            }
        }




        btn_ubication.setOnClickListener {//Meter en un mètodo a parte
            if(btn_ubication.text=="Allow"){
                btn_ubication.text = "Allowed"
                btn_ubication.alpha=0.5F
                btn_ubication.setBackgroundColor(Color.parseColor("#F6DE05"))
            }else if(btn_ubication.text=="Allowed"){
                btn_ubication.text="Allow"
                btn_ubication.alpha=1F
                btn_ubication.setBackgroundColor(Color.parseColor("#FFFFFF"))
            }
        }

        btn_notif.setOnClickListener {
            if(btn_notif.text=="Allow"){
                btn_notif.text = "Allowed"
                btn_notif.alpha=0.5F
                btn_notif.setBackgroundColor(Color.parseColor("#F6DE05"))
            }else if(btn_notif.text=="Allowed"){
                btn_notif.text="Allow"
                btn_notif.alpha=1F
                btn_notif.setBackgroundColor(Color.parseColor("#FFFFFF"))
            }
        }

        return mLinearLayout  //Retornem el layout despres dels canvis
        //return inflater.inflate(R.layout.fragment_two, container, false)
    }





}
