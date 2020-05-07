package com.example.befit

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageButton
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main_interface.*


class Main_Interface : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener,
    RegisterWeightDialog.DialogListener {

    lateinit var toolbar :Toolbar;
    lateinit var viewPager : ViewPager
    lateinit var drawerLayout : DrawerLayout
    lateinit var navigationView : NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_interface)
        /*
        var listExercise : ListView = findViewById(R.id.listExercise)

        val arrayExercise = arrayOf<String>("Push up", "Lateral raise")
        var arrayAdapterExercise = ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayExercise)

        listExercise.adapter = arrayAdapterExercise

        val imgId = arrayOf <Int>(R.drawable.pushup, R.drawable.lateralraise)

*/      viewPager = findViewById(R.id.viewPager_Main)
        val adapter = MyViewPagerAdapter(supportFragmentManager) //Adapter que ens permetrà afegir els fragment al viewPager

        adapter.addFragment(fragmentEjercicio())
        adapter.addFragment(fragment_comida())
        adapter.addFragment(fragmentAmbiente())
        viewPager.adapter = adapter

        var tabLayout = findViewById<TabLayout>(R.id.tab_layout_Main)
        tabLayout.setupWithViewPager(viewPager_Main,true) //Connectem els dots amb el viewpager
/*
        exerciseListView.setOnItemClickListener(AdapterView.OnItemClickListener(){
                adapterView: AdapterView<*>, view1: View, i: Int , l: Long ->
            var intent = Intent(this, GoToGym::class.java)
            intent.putExtra("EXERCISE", exerciseList.get(i))

            startActivity(intent)
        })
*/


        var txtkcal: TextView
        txtkcal = findViewById(R.id.kcal)
        txtkcal.typeface = Typeface.createFromAsset(assets, "font/Futura Heavy font.ttf")


        var btnRegisterWeight: Button = findViewById(R.id.btnRegisterWeight)
        btnRegisterWeight.typeface = Typeface.createFromAsset(assets, "font/Futura Heavy font.ttf")

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
        toolbar.title=null

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
        when(item.itemId){
            R.id.storage_item-> null
            R.id.app_item-> null
            else-> {
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

    override fun applyTexts(weight: String?) {
        if (!weight.isNullOrEmpty()) btnRegisterWeight.setText(weight + "kg")
        else btnRegisterWeight.setText("REGISTER WEIGHT")
    }

    class MyViewPagerAdapter(manager: FragmentManager) :
        FragmentPagerAdapter(manager, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

        private val fragmentList : MutableList<Fragment> = ArrayList()   //Creem una llista amb tots els fragments a utilitzar en el ViewPager

        override fun getItem(position: Int): Fragment {
            return fragmentList[position]
        }

        override fun getCount(): Int {
            return fragmentList.size  //Retorna el nombre de fragments que hi han
        }

        fun addFragment(fragment : Fragment){  //Mètode per afegir fragments al viewPager
            fragmentList.add(fragment)
        }
    }

}