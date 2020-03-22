package com.example.befit

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import kotlinx.android.synthetic.main.activity_login.*

/**
 * A simple [Fragment] subclass.
 */
class fragmentThree : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var mLinearLayout = inflater.inflate(R.layout.fragment_three,container,false)
        var btn_med : Button = mLinearLayout.findViewById(R.id.btn_mediterranean)
        var btn_oriental : Button = mLinearLayout.findViewById(R.id.btn_oriental)
        var btn_american = mLinearLayout.findViewById<Button>(R.id.btn_american)
        var btn_halal = mLinearLayout.findViewById<Button>(R.id.btn_halal)
        var btn_mex = mLinearLayout.findViewById<Button>(R.id.btn_mexican)
        var btn_veg = mLinearLayout.findViewById<Button>(R.id.btn_vegetarian)
        //Indiquem el color per defecte dels botons
        btn_med.setBackgroundColor(Color.parseColor("#FFFFFF"))
        btn_oriental.setBackgroundColor(Color.parseColor("#FFFFFF"))
        btn_american.setBackgroundColor(Color.parseColor("#FFFFFF"))
        btn_halal.setBackgroundColor(Color.parseColor("#FFFFFF"))
        btn_mex.setBackgroundColor(Color.parseColor("#FFFFFF"))
        btn_veg.setBackgroundColor(Color.parseColor("#FFFFFF"))

        btn_med.setOnClickListener {
            if(btn_med.alpha==1F){
                btn_med.setBackgroundColor(Color.parseColor("#F6DE05"))
                btn_med.alpha=0.5F
            }else if(btn_med.alpha==0.5F){
                btn_med.setBackgroundColor(Color.parseColor("#FFFFFF"))
                btn_med.alpha=1F
            }
        }

        btn_oriental.setOnClickListener {
            if(btn_oriental.alpha==1F){
                btn_oriental.setBackgroundColor(Color.parseColor("#F6DE05"))
                btn_oriental.alpha=0.5F
            }else if(btn_oriental.alpha==0.5F){
                btn_oriental.setBackgroundColor(Color.parseColor("#FFFFFF"))
                btn_oriental.alpha=1F
            }
        }

        btn_american.setOnClickListener {
            if(btn_american.alpha==1F){
                btn_american.setBackgroundColor(Color.parseColor("#F6DE05"))
                btn_american.alpha=0.5F
            }else if(btn_american.alpha==0.5F){
                btn_american.setBackgroundColor(Color.parseColor("#FFFFFF"))
                btn_american.alpha=1F
            }
        }

        btn_halal.setOnClickListener {
            if(btn_halal.alpha==1F){
                btn_halal.setBackgroundColor(Color.parseColor("#F6DE05"))
                btn_halal.alpha=0.5F
            }else if(btn_halal.alpha==0.5F){
                btn_halal.setBackgroundColor(Color.parseColor("#FFFFFF"))
                btn_halal.alpha=1F
            }
        }

        btn_mex.setOnClickListener {
            if(btn_mex.alpha==1F){
                btn_mex.setBackgroundColor(Color.parseColor("#F6DE05"))
                btn_mex.alpha=0.5F
            }else if(btn_mex.alpha==0.5F){
                btn_mex.setBackgroundColor(Color.parseColor("#FFFFFF"))
                btn_mex.alpha=1F
            }
        }

        btn_veg.setOnClickListener {
            if(btn_veg.alpha==1F){
                btn_veg.setBackgroundColor(Color.parseColor("#F6DE05"))
                btn_veg.alpha=0.5F
            }else if(btn_veg.alpha==0.5F){
                btn_veg.setBackgroundColor(Color.parseColor("#FFFFFF"))
                btn_veg.alpha=1F
            }
        }



        // Inflate the layout for this fragment
        return mLinearLayout
        //return inflater.inflate(R.layout.fragment_three, container, false)
    }

}
