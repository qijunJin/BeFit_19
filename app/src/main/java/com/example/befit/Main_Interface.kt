package com.example.befit

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import android.app.TaskStackBuilder
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class Main_Interface : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    lateinit var toolbar :Toolbar;

    lateinit var drawerLayout : DrawerLayout
    lateinit var navigationView : NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_interface)

        // Trobem la toolbar u la apliquem a la activity
        toolbar = findViewById(R.id.main_toolbar)
        setSupportActionBar(toolbar)

        drawerLayout = findViewById(R.id.drawer_layout)
        navigationView = findViewById(R.id.nav_view)

        var actionBarDrawerToggle : ActionBarDrawerToggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            toolbar,
            R.string.openNavDrawer,
            R.string.closeNavDrawer
        )
        drawerLayout.addDrawerListener(actionBarDrawerToggle)

        actionBarDrawerToggle.syncState()
        //Quan seleccionem un item del menú
        navigationView.setNavigationItemSelectedListener(this)


    // BUTTON LISTENERS

        var btn_Gym: Button = findViewById(R.id.btn_Gym)

        btn_Gym.setOnClickListener {
            startActivity(Intent(this, GoToGym::class.java))
        }

        var btn_Outdoor: Button = findViewById(R.id.btn_Outdoor)

        btn_Outdoor.setOnClickListener {
            startActivity(Intent(this, GoToOutdoorRunning::class.java))
        }

        var btn_statistics: ImageButton = findViewById(R.id.btn_Statistics)

        btn_statistics.setOnClickListener {
            startActivity(Intent(this, Statistics::class.java))
        }
    }


    //  Toolbar and prefereneces needed methods
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.storage_item-> null
            R.id.app_item-> null
            else-> {
                Toast.makeText(this,"Click Worked",Toast.LENGTH_LONG).show()
                goToPreferenceActivity()
            }
        }
        return true
    }

    override fun onPointerCaptureChanged(hasCapture: Boolean) {
        super.onPointerCaptureChanged(hasCapture)
    }


    //!!! If we wanted an options icon (threepoints in top-left corner) to acces the configuration

/*    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.preference_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }*/

    //Quan es selecciona una opcio del manu
/*    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.groupId

        when(id){
            //Not finished
            else-> goToPreferenceActivity()
        }

        return super.onOptionsItemSelected(item)
    }*/

    fun goToPreferenceActivity(){
        startActivity(Intent(this,PreferenceActivity::class.java))
    }

}

