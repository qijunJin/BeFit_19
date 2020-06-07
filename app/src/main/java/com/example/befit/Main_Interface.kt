package com.example.befit

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.MenuItem
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
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
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_final_welcome.*
import kotlinx.android.synthetic.main.activity_main_interface.*
import kotlinx.android.synthetic.main.fragment_one.*
import kotlinx.android.synthetic.main.header_layout_for_drawer.*
import kotlinx.android.synthetic.main.register_weight_dialog.*


class Main_Interface : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener,
    RegisterWeightDialog.DialogListener {

    lateinit var toolbar: Toolbar;
    lateinit var viewPager: ViewPager
    lateinit var drawerLayout: DrawerLayout
    lateinit var navigationView: NavigationView
    lateinit var txt_weight : EditText
    lateinit var btn_f : ImageButton
    lateinit var btn_m : ImageButton

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
        val acct = GoogleSignIn.getLastSignedInAccount(this)
        var FoodPreferences : ArrayList<FoodType> = ArrayList()

        var txtkcal: TextView = findViewById(R.id.kcal)
        txtkcal.typeface = Typeface.createFromAsset(assets, "font/futura-pt-heavy.otf")

        var title: TextView = findViewById(R.id.title)
        title.typeface = Typeface.createFromAsset(assets, "font/futura-pt-heavy.otf")

        viewPager = findViewById(R.id.viewPager_Main)
        val adapter =
            MyViewPagerAdapter(supportFragmentManager) //Adapter que ens permetrà afegir els fragment al viewPager

        //txt_weight = findViewById(R.id.etxtWeight)
        //etxtWeight.addTextChangedListener(textWatcher1)

        adapter.addFragment(fragmentEjercicio())
        adapter.addFragment(fragment_comida())
        adapter.addFragment(fragmentAmbiente())

        viewPager.adapter = adapter


        var tabLayout = findViewById<TabLayout>(R.id.tab_layout_Main)
        tabLayout.setupWithViewPager(viewPager_Main, true) //Connectem els dots amb el viewpager


        tabLayout!!.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                when (tab.position) {
                    0 -> title.text = "Exercises"
                    1 -> title.text = "Gastronomy"
                    2 -> title.text = "Outdoor Running"
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
        txtScale.text = "Scale"

        txtScale.typeface = Typeface.createFromAsset(assets, "font/futura-pt-heavy.otf")

        var exerciseFinish = Exercise(0, "", 0, 0)


        var ex = intent.getSerializableExtra("EXERCISEFINISH")
        var exercise: Exercise? = null
        var calories = 0

        MainActivity.reference.child(acct?.displayName.toString())
            .addValueEventListener(object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {}
                override fun onDataChange(p0: DataSnapshot) {  //Mirem si el nom d'usuari ja existeix a firebase. En cas que existeixi no ha de registrar i passem a Main_Interface directament
                    if (p0.child("age").exists()) {
                        MainActivity.user_actual.name = p0.child("name").value.toString()
                        MainActivity.user_actual.age = p0.child("age").value.toString().toInt()
                        MainActivity.user_actual.height =
                            p0.child("height").value.toString().toDouble()
                        MainActivity.user_actual.weight =
                            p0.child("weight").value.toString().toDouble()
                        MainActivity.user_actual.cal = p0.child("cal").value.toString().toInt()
                        MainActivity.user_actual.genere = p0.child("genere").value.toString()
                        p0.child("arr_comida").children.forEach {
                            MainActivity.user_actual.arr_comida.add(it.value.toString())
                            //Toast.makeText(requireContext(),it.value.toString(),Toast.LENGTH_SHORT).show()
                        }

                    }
                }
            })

        MainActivity.reference.child(acct?.displayName.toString()).child("cal")
            .setValue(MainActivity.user_actual.cal)

        MainActivity.reference.child(acct?.displayName.toString()).child("arr_comida")
            .setValue(MainActivity.user_actual.arr_comida)

        if(MainActivity.user_actual.genere == "home"){
            btn_Statistics.setImageResource(R.drawable.male_round)
        }else if(MainActivity.user_actual.genere == "dona"){
            btn_Statistics.setImageResource(R.drawable.female_round)
        }else{
            btn_Statistics.setImageResource(R.drawable.male_round) //Per defecte carregem la iamtge de home, en cas que hi hagi algun error, pero no deuria pasar
        }

        var u = MainActivity.user_actual.cal


        txtkcal.text = "$u kcal"
        //txtScale.text = "$w1 kg"


        var w1 = intent.getStringExtra("WEIGHT")

        txtScale.text = if (w1.isNullOrEmpty()) "Scale" else "$w1 kg"


        MainActivity.reference.child(acct?.displayName.toString()).child("weight")
            .setValue(MainActivity.user_actual.weight)

        if (ex != null) {
            exercise = ex as Exercise
            var userCal = MainActivity.user_actual.cal + exercise.calories
            MainActivity.reference.child(acct?.displayName.toString()).child("cal")
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
        toolbar.title = "hola"

        var actionBarDrawerToggle: ActionBarDrawerToggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            toolbar,
            R.string.openNavDrawer,
            R.string.closeNavDrawer
        )
        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        drawerLayout.setOnCreateContextMenuListener { menu, v, menuInfo -> title1.text ="hola" }


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

    private val textWatcher1 = object : TextWatcher {
        override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}

        override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}

        override fun afterTextChanged(editable: Editable) {
            val acct = GoogleSignIn.getLastSignedInAccount(applicationContext)
            MainActivity.reference.child(acct?.displayName.toString()).child("weight")
                .setValue(etxtWeight.text.toString())
        }
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)

    }

    //  Toolbar and prefereneces needed methods
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.app_item -> null
            R.id.drawer_signOut -> signOut()
            R.id.drawer_deleteAccount -> deleteAccount()

        }
        return true
    }

    private fun deleteAccount() {
        FirebaseDatabase.getInstance().reference.child(MainActivity.user_actual.complete_name)  //Borramos ususario de firebase
            .removeValue()

        mGoogleSignInClient.signOut()
        startActivity(Intent(this, MainActivity::class.java))
        Toast.makeText(this, "Compte borrat", Toast.LENGTH_LONG).show()
    }


    private fun signOut() {
        mGoogleSignInClient.signOut()
        startActivity(Intent(this, MainActivity::class.java))
        Toast.makeText(this, "Sessió tancada", Toast.LENGTH_LONG).show()
    }

    override fun onPointerCaptureChanged(hasCapture: Boolean) {
        super.onPointerCaptureChanged(hasCapture)
    }

    override fun onBackPressed() {}  //Fa que no puguem tirar enrere desde main INterface


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