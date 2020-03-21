package com.example.befit

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_final__welcome.*
import kotlinx.android.synthetic.main.fragment_one.*

/**
 * A simple [Fragment] subclass.
 */
class FragmentOne : Fragment() {  //Primer fragment que apareix per logejarse

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var mLinearLayout = inflater.inflate(R.layout.fragment_one,container,false)


        // Inflate the layout for this fragment
        //return mLinearLayout
        return inflater.inflate(R.layout.fragment_one, container, false)
    }


}
