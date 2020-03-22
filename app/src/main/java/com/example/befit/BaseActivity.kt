package com.example.befit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentTransaction

class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.base_activity)


        //findNavController(R.id.my_nav_host_fragment).navigate(R.id.action_placeholder_to_settingsMainFragmen)


        val transaction =fragmentManager!!.beginTransaction()
        transaction.replace(R.id.my_nav_host_fragment,SettingsMainFragment())
            .addToBackStack(null)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            .commit()

    }
}
