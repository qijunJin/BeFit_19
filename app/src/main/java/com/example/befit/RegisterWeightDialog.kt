package com.example.befit

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.widget.EditText
import androidx.appcompat.app.AppCompatDialogFragment
import com.google.android.gms.auth.api.signin.GoogleSignIn
import kotlinx.android.synthetic.main.register_weight_dialog.*

class RegisterWeightDialog : AppCompatDialogFragment() {
    private var listener: DialogListener? = null
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val view = LayoutInflater.from(activity).inflate(R.layout.register_weight_dialog, null)
        val alert = AlertDialog.Builder(activity)
        var etxtWeight : EditText = view.findViewById(R.id.etxtWeight)

        //etxtWeight.addTextChangedListener(textWatcher1)
        val acct = GoogleSignIn.getLastSignedInAccount(requireActivity().applicationContext)
/*
        etxtWeight.addTextChangedListener(object:TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                //weight = etxtWeight.text.toString()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int){
            }
        })
*/
        with(alert) {
            setView(view)
            setTitle(getString(R.string.title_register_weight_dialog))
            setPositiveButton(getString(R.string.register_weight_dialog)) { _, _ ->
                onPositiveClick(
                    etxtWeight.text.toString()
                )
            }
        }

        return alert.create()

/*
        @Override
        public void onAttach(Context context){

        }
        */

    }

  /* private val textWatcher1 = object : TextWatcher {

        override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}

        override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}

        override fun afterTextChanged(editable: Editable) {
            MainActivity.reference.child(MainActivity.user_actual.name).child("weight").setValue(etxtWeight.text.toString().toDouble())
        }
    }*/

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = try {
            context as DialogListener
        } catch (e: ClassCastException) {
            throw ClassCastException(
                context.toString() +
                        "must implement ExampleDialogListener"
            )
        }
    }

    private fun onPositiveClick(weight: String) {
        //listener?.applyTexts(weight)

        //etxtWeight.getText().toString()
        //requireActivity().findViewById<EditText>(R.id.btnRegisterWeight).setText(weight);

        val i = Intent(requireActivity().baseContext, Main_Interface::class.java)
        MainActivity.reference.child(MainActivity.user_actual.complete_name).child("weight").setValue(weight)
        i.putExtra("WEIGHT", weight)
        startActivity(i)
    }

    interface DialogListener {

        fun applyTexts(weight: String?)
    }


}
