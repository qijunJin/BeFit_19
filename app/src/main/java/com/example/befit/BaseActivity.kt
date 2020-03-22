package com.example.befit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.Toolbar

/**
 * Simulates the activity where the fragments are going to be placed at.
 */

class BaseActivity : AppCompatActivity() {

    val fragmentManager = supportFragmentManager
    val fragmentTransaction = fragmentManager.beginTransaction()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
        val fragment = AccountConf()
        fragmentTransaction.add(R.id.baseFragment, fragment)
            .commit()
    }

    fun to_settings(view: View) {
        // Change Actiivity
        val intent = Intent(this, SettingsActivity::class.java)
        val toast = Toast.makeText(this,"Opening Settings ...",Toast.LENGTH_SHORT)
        toast.show()
        startActivity(intent)
    }

}
