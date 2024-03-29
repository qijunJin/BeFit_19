package com.example.befit

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import androidx.fragment.app.Fragment

/**
 * A simple [Fragment] subclass.
 */
class FragmentOne : Fragment() {  //Primer fragment que apareix per logejarse

    lateinit var btn_male: ImageButton
    lateinit var btn_female: ImageButton
    lateinit var txt_name: EditText
    lateinit var txt_age : EditText
    lateinit var txt_height : EditText
    lateinit var txt_weight : EditText

    companion object{
        //var genere : Int = 0   //Variable per controlar quin genere a escollit 1->home 2-> dona
    }

    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var mLinearLayout = inflater.inflate(R.layout.fragment_one, container, false)

        //Inicialització botons
        btn_male = mLinearLayout.findViewById(R.id.btn_male)
        btn_female = mLinearLayout.findViewById(R.id.btn_female)
        txt_name = mLinearLayout.findViewById(R.id.edit_name)
        txt_age = mLinearLayout.findViewById(R.id.edit_age)
        txt_height = mLinearLayout.findViewById(R.id.edit_height)
        txt_weight = mLinearLayout.findViewById(R.id.edit_weight)


        txt_age.addTextChangedListener(textWatcher1) //Listener per si canvia el text a age
        txt_height.addTextChangedListener(textWatcher2)  //Listener per height
        txt_weight.addTextChangedListener(textWatcher3)
        txt_name.addTextChangedListener(textWatcher4)




        btn_male.setOnClickListener {
            btn_female.alpha = 0.5F
            btn_male.alpha = 1F
            MainActivity.user_actual.genere="home"
           // MainActivity.reference.child(MainActivity.user_actual.complete_name).child("genere").setValue("home") //Guardem a firebase el genere home de lusuari

        }

        btn_female.setOnClickListener {
            btn_male.alpha = 0.5F
            btn_female.alpha = 1F
            MainActivity.user_actual.genere="dona"
            //MainActivity.reference.child(MainActivity.user_actual.complete_name).child("genere").setValue("dona") //Guardem a firebase el genere dona de lusuari
        }

        // Inflate the layout for this fragment
        return mLinearLayout
        //return inflater.inflate(R.layout.fragment_one, container, false)
    }

    //Watcher que observa si hi han canvis al edit text dels anys i actualitza el valor a firebase
    private val textWatcher1 = object : TextWatcher {
        override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}

        override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}

        override fun afterTextChanged(editable: Editable) {
            if (txt_age.text.toString()
                    .isNotEmpty()
            )
                MainActivity.user_actual.age = txt_age.text.toString().toInt()
               /* MainActivity.reference.child(MainActivity.user_actual.complete_name).child("age")
                .setValue(txt_age.text.toString())*/
        }
    }

    //Watcher que observa si hi han canvis al edit text dels height i actualitza el valor a firebase
    private val textWatcher2 = object : TextWatcher {
        override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}

        override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}

        override fun afterTextChanged(editable: Editable) {
            if (txt_height.text.toString()
                    .isNotEmpty()
            ) /*MainActivity.reference.child(MainActivity.user_actual.complete_name).child("height")
                .setValue(txt_height.text.toString())*/
                MainActivity.user_actual.height = txt_height.text.toString().toDouble()
        }
    }


    private val textWatcher3 = object : TextWatcher {
        override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}

        override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}

        override fun afterTextChanged(editable: Editable) {
            if (txt_weight.text.toString()
                    .isNotEmpty()
            ) /*MainActivity.reference.child(MainActivity.user_actual.complete_name).child("weight")
                .setValue(txt_weight.text.toString())*/
                MainActivity.user_actual.weight = txt_weight.text.toString().toDouble()
        }
    }

    private val textWatcher4 = object : TextWatcher {
        override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}

        override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}

        override fun afterTextChanged(editable: Editable) {
            if (txt_name.text.toString()
                    .isNotEmpty()
            ) /*MainActivity.reference.child(MainActivity.user_actual.complete_name).child("name")
                .setValue(txt_name.text.toString())*/
                MainActivity.user_actual.name = txt_name.text.toString()
        }
    }

}
