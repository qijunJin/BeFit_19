package com.example.befit

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.fragment_one.*

const val EXTRA_MESSAGE = "com.example.BEFIT_F.MESSAGE"
const val EXTRA_ENRERE = "com.exameple.BEFIT_F.ENRERE"

class LogIn_Data : AppCompatActivity() {
    lateinit var viewPager : ViewPager
    lateinit var btn_finish : Button
    lateinit var btn_back :Button
    lateinit var nameUser : String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        viewPager = findViewById(R.id.viewPager)
        val adapter = MyViewPagerAdapter(supportFragmentManager) //Adapter que ens permetrà afegir els fragment al viewPager

        //Afegim tots els fragments que creem
        adapter.addFragment(FragmentOne())
        adapter.addFragment(FragmentTwo())
        adapter.addFragment(fragmentThree())
        viewPager.adapter = adapter
        var tabLayout = findViewById<TabLayout>(R.id.tab_layout)
        tabLayout.setupWithViewPager(viewPager,true) //Connectem els dots amb el viewpager

        btn_finish=findViewById(R.id.btn_finish)  //De moment utilitzem el botó next per canviar a la ultima activity del logIn. Després nomes servirà per fer canviar de slide
        //btn_finish.isEnabled = false
        //btn_finish.alpha = 0F

        btn_back=findViewById(R.id.btn_back)


        btn_finish.setOnClickListener {
            /*nameUser = edit_name.hint.toString() //Agafem el valor del editText amb el nom
            if(nameUser.isNullOrEmpty()){
                Toast.makeText(this,"Introduce tu nombre",Toast.LENGTH_SHORT).show()
            }else if(nameUser.isNotBlank()) {
                val intent : Intent = Intent(this,Final_Welcome::class.java).apply {
                    putExtra(EXTRA_MESSAGE,nameUser) //Enviem el nom del usuari a la seguent activity on el saludem

                }*/
            val intent : Intent = Intent(this,Final_Welcome::class.java)
            startActivity(intent)
           // }
        }

        btn_back.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java).apply {
                putExtra(EXTRA_ENRERE,"si")
            }
            startActivity(intent)
        }
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
