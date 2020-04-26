package com.example.befit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.fragment_one.*

/**
 * A simple [Fragment] subclass.
 */
class FragmentOne : Fragment() {  //Primer fragment que apareix per logejarse

    lateinit var btn_male: ImageButton
    lateinit var btn_female: ImageButton
    lateinit var txt_name: TextView
    lateinit var txt_age : EditText

    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var mLinearLayout = inflater.inflate(R.layout.fragment_one, container, false)

        //Inicialització botons
        btn_male = mLinearLayout.findViewById(R.id.btn_male)
        btn_female = mLinearLayout.findViewById(R.id.btn_female)
        txt_name = mLinearLayout.findViewById(R.id.edit_name)

        txt_name.text = MainActivity.user?.displayName

        btn_male.setOnClickListener {
            btn_female.alpha = 0.5F
            btn_male.alpha = 1F
        }

        btn_female.setOnClickListener {
            btn_male.alpha = 0.5F
            btn_female.alpha = 1F
        }

        // Inflate the layout for this fragment
        return mLinearLayout
        //return inflater.inflate(R.layout.fragment_one, container, false)
    }


}
