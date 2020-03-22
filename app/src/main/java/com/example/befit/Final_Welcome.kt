package com.example.befit

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Final_Welcome : AppCompatActivity() {

    lateinit var welcome_name: TextView

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_final_welcome)
        Toast.makeText(this, "User created", Toast.LENGTH_SHORT).show()

        var btn_welcome_start: Button = findViewById(R.id.btn_welcome_start)
        var final_name: TextView = findViewById(R.id.final_name)

        val message =
            intent.getStringExtra(EXTRA_MESSAGE) //Rebem el nom de l'usuari indicat a l'activity anterior

        final_name.text =
            if (message == "") "unknown" else message //Assignem al textView del nom, el nom rebut

        btn_welcome_start.setOnClickListener {
            startActivity(Intent(this, Main_Interface::class.java))

        }
    }
}
