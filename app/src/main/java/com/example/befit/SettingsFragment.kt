package com.example.befit

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.FragmentTransaction.TRANSIT_FRAGMENT_CLOSE

/**
 * A simple [Fragment] subclass.
 */
class SettingsFragment : Fragment() {

    lateinit var listView : ListView;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.activity_settings,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val view = getView()

        listView = view!!.findViewById<ListView>(R.id.settings_listview)

        var arrayList = arrayListOf<String>()
        arrayList.add("App Configuration")
        arrayList.add("Account Configuration")
        arrayList.add("My Data")
        arrayList.add("Terms and Conditions")

        var arrayAdapter  = ArrayAdapter(view.context,android.R.layout.simple_list_item_1,arrayList)
        listView.adapter = arrayAdapter

        listView.setOnItemClickListener { parent, view, position, id ->
            val element = arrayAdapter.getItem(position)
            when(element){
                "App Configuration" -> changeToAppConf()
                "Account Configuration" -> changeToAccountConf()
                "My Data"-> changeToMyData()
                "Terms and Conditions" -> changeToTerms()
            }

        }

    }

    private fun changeToAppConf() {

        val transaction =fragmentManager!!.beginTransaction()
            transaction.replace(R.id.settings_fragment_container,AppConf())
            .addToBackStack(null)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            .commit()
    }

    private fun changeToAccountConf() {
        fragmentManager!!.beginTransaction()
            .replace(R.id.settings_fragment_container,AccountConf())
            .addToBackStack(null)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            .commit()
    }

    private fun changeToMyData() {
        fragmentManager!!.beginTransaction()
            .replace(R.id.settings_fragment_container,MyData())
            .addToBackStack(null)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            .commit()
    }

    private fun changeToTerms() {
        fragmentManager!!.beginTransaction()
            .replace(R.id.settings_fragment_container,TermsAndConditions())
            .addToBackStack(null)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            .commit()
    }


}
