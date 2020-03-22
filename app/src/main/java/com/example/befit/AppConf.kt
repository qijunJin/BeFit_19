package com.example.befit

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.preference.PreferenceFragmentCompat

/**
 * A simple [Fragment] subclass.
 * Use the [AppConf.newInstance] factory method to
 * create an instance of this fragment.
 */
class AppConf : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.simple_hierarchy,rootKey)
    }


}
