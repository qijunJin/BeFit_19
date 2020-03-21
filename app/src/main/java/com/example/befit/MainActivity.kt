package com.example.befit

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var btn_signUp : Button = findViewById(R.id.btn_signUp)
        var btn_signIn : Button = findViewById(R.id.btn_SignIn)

        btn_signUp.setOnClickListener {
            val intent = Intent(this, LogIn_Data::class.java)
            startActivity(intent)
        }

        btn_signIn.setOnClickListener {
            val intent = Intent(this, LogIn_Data::class.java)
            startActivity(intent)
        }

    }


}
