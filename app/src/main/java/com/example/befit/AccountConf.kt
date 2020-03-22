package com.example.befit

import android.os.Bundle
import android.preference.PreferenceFragment
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil.setContentView
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat

/**
 * A simple [Fragment] subclass.
 */
class AccountConf : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.simple_hierarchy2,rootKey)
    }

}
