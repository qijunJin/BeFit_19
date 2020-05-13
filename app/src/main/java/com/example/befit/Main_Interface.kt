package com.example.befit

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main_interface.*


class Main_Interface : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener,
    RegisterWeightDialog.DialogListener {

    lateinit var toolbar: Toolbar;
    lateinit var viewPager: ViewPager
    lateinit var drawerLayout: DrawerLayout
    lateinit var navigationView: NavigationView

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_interface)
        /*
        var listExercise : ListView = findViewById(R.id.listExercise)

        val arrayExercise = arrayOf<String>("Push up", "Lateral raise")
        var arrayAdapterExercise = ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayExercise)

        listExercise.adapter = arrayAdapterExercise

        val imgId = arrayOf <Int>(R.drawable.pushup, R.drawable.lateralraise)

*/
        var txtkcal: TextView = findViewById(R.id.kcal)
        txtkcal.typeface = Typeface.createFromAsset(assets, "font/futura-pt-heavy.otf")

        var title: TextView = findViewById(R.id.title)
        title.typeface = Typeface.createFromAsset(assets, "font/futura-pt-heavy.otf")

        viewPager = findViewById(R.id.viewPager_Main)
        val adapter =
            MyViewPagerAdapter(supportFragmentManager) //Adapter que ens permetrà afegir els fragment al viewPager

        adapter.addFragment(fragmentEjercicio())
        adapter.addFragment(fragment_comida())
        adapter.addFragment(fragmentAmbiente())
        viewPager.adapter = adapter


        var tabLayout = findViewById<TabLayout>(R.id.tab_layout_Main)
        tabLayout.setupWithViewPager(viewPager_Main, true) //Connectem els dots amb el viewpager


        tabLayout!!.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                when (tab.position) {
                    0 -> title.text = "Exercise"
                    1 -> title.text = "Gastronomy"
                    2 -> title.text = "Environment"
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {

            }

            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })

        var btnRegisterWeight: ImageButton = findViewById(R.id.btnRegisterWeight)
        var txtScale: TextView = findViewById(R.id.scale)

        var w = intent.getStringExtra("WEIGHT")

        txtScale.text = if (w.isNullOrEmpty()) "Scale" else "$w kg"
        txtScale.typeface = Typeface.createFromAsset(assets, "font/futura-pt-heavy.otf")

        var exerciseFinish = Exercise(0, "", 0)


        var ex = intent.getSerializableExtra("EXERCISEFINISH")
        var exercise: Exercise? = null
        var calories = 0

        val acct = GoogleSignIn.getLastSignedInAccount(this)
        MainActivity.reference.child(acct?.displayName.toString()).child("name").setValue(acct?.displayName.toString())
        txtkcal.text =  MainActivity.user_actual.cal.toString() + " kcal"
        if (ex != null) {
            exercise = ex as Exercise
            var userCal = MainActivity.user_actual.cal + exercise.calories
            MainActivity.reference.child(MainActivity.user_actual.name).child("cal")
                .setValue(userCal)
            txtkcal.text = "$userCal kcal"
        }


        btnRegisterWeight.setOnClickListener {
            val registerWeightDialog = RegisterWeightDialog()
            registerWeightDialog.show(supportFragmentManager, "")
        }


        // Trobem la toolbar u la apliquem a la activity
        toolbar = findViewById(R.id.main_toolbar)
        setSupportActionBar(toolbar)
        //Remove title from Appbar
        supportActionBar!!.setTitle("")

        drawerLayout = findViewById(R.id.drawer_layout)
        navigationView = findViewById(R.id.nav_view)


        //No title
        toolbar.title = null

        var actionBarDrawerToggle: ActionBarDrawerToggle = ActionBarDrawerToggle(
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
/*
        var btn_Gym: Button = findViewById(R.id.btn_Gym)

        btn_Gym.setOnClickListener {
            startActivity(Intent(this, GoToGym::class.java))
        }

        var btn_Outdoor: Button = findViewById(R.id.btn_Outdoor)

        btn_Outdoor.setOnClickListener {
            startActivity(Intent(this, GoToOutdoorRunning::class.java))
        }
*/
        var btn_statistics: ImageButton = findViewById(R.id.btn_Statistics)

        btn_statistics.setOnClickListener {
            startActivity(Intent(this, Statistics::class.java))
        }
    }


    //  Toolbar and prefereneces needed methods
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.storage_item -> null
            R.id.app_item -> null
            else -> {
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

    fun goToPreferenceActivity() {
        startActivity(Intent(this, PreferenceActivity::class.java))
    }

    override fun applyTexts(weight: String?) {
        if (!weight.isNullOrEmpty()) {
            var w = weight.toString()
            //txtScale = w
        }
        //else txtScale.text = "Scale"
    }

    class MyViewPagerAdapter(manager: FragmentManager) :
        FragmentPagerAdapter(manager, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

        private val fragmentList: MutableList<Fragment> =
            ArrayList()   //Creem una llista amb tots els fragments a utilitzar en el ViewPager

        override fun getItem(position: Int): Fragment {
            return fragmentList[position]
        }

        override fun getCount(): Int {
            return fragmentList.size  //Retorna el nombre de fragments que hi han
        }

        fun addFragment(fragment: Fragment) {  //Mètode per afegir fragments al viewPager
            fragmentList.add(fragment)
        }
    }

}