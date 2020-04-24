package com.example.befit

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class Final_Welcome : AppCompatActivity() {

    lateinit var welcome_name: TextView
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_final_welcome)
        Toast.makeText(this, "User created", Toast.LENGTH_SHORT).show()

        var btn_welcome_start: Button = findViewById(R.id.btn_welcome_start)
        var final_name: TextView = findViewById(R.id.final_name)

        auth = FirebaseAuth.getInstance()
        var currentUser = auth.currentUser

        var user : User = User(currentUser?.displayName.toString(), currentUser!!.uid, 0.0)  //Aqui creem el usuari local de l'aplicació amb totes les dades. Al acabar el exerici aquets valors es tornaran apujar a firebase

        val message =
            intent.getStringExtra(EXTRA_MESSAGE) //Rebem el nom de l'usuari indicat a l'activity anterior

        final_name.text = MainActivity.user?.displayName

        btn_welcome_start.setOnClickListener {
            startActivity(Intent(this, Main_Interface::class.java))

        }
    }
}
