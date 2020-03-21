package com.example.befit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import kotlinx.android.synthetic.main.activity_login.*

class LogIn_Data : AppCompatActivity() {
    lateinit var viewPager : ViewPager
    lateinit var btn_next : Button
    lateinit var btn_back :Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        viewPager = findViewById(R.id.viewPager)

        val adapter = MyViewPagerAdapter(supportFragmentManager) //Adapter que ens permetrà afegir els fragment al viewPager
        //Afegim tots els fragments que creem
        adapter.addFragment(FragmentOne())
        adapter.addFragment(FragmentTwo())
        viewPager.adapter = adapter

        //Inicialització botons
        btn_next=findViewById(R.id.btn_finish)  //De moment utilitzem el botó next per canviar a la ultima activity del logIn. Després nomes servirà per fer canviar de slide
        btn_back=findViewById(R.id.btn_back)

        btn_next.setOnClickListener {
            val intent : Intent = Intent(this,Final_Welcome::class.java)
            startActivity(intent)
        }

        btn_back.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
    }

    class MyViewPagerAdapter(manager : FragmentManager) : FragmentPagerAdapter(manager){

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
