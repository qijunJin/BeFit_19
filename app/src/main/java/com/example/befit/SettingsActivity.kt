package com.example.befit

import android.content.Context
import android.graphics.drawable.DrawableContainer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * This Activity is mento to support fragments related with configuration
 *          Account Configrations (Logout, Login, etc.)
 *          App Configurations (Notifications, etc.)
 */

class SettingsActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        //For fragments transactions (add(),remove(),replace(), addToBackStack(), commit())
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        //Also fragmentManager.findFragmentByID()

        //PREFERENCES FRAGMENT
        if(savedInstanceState== null){
            fragmentTransaction
                .add(R.id.settings_fragment_container,SettingsFragment())
                .addToBackStack(null)
                .commit()
        }



    }

}
