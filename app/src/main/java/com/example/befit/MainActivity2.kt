package com.example.befit

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

private lateinit var auth: FirebaseAuth

class MainActivity2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val btn_LogOut: Button = findViewById(R.id.btn_LogOut)
        val btn_next: Button = findViewById(R.id.btn_next)

        val acct = GoogleSignIn.getLastSignedInAccount(this)

        var registrat = false

        val reff = FirebaseDatabase.getInstance().reference.child(acct?.displayName.toString())

        reff.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {}
            override fun onDataChange(@NonNull p0: DataSnapshot) {  //Mirem si el nom d'usuari ja existeix a firebase. En cas que existeixi no ha de registrar i passem a Main_Interface directament
                registrat = p0.child("age").exists()
            }
        })

        btn_next.setOnClickListener {

            if (registrat) {
                val reff =
                    FirebaseDatabase.getInstance().reference.child(acct?.displayName.toString())

                reff.addValueEventListener(object : ValueEventListener {
                    override fun onCancelled(p0: DatabaseError) {}
                    override fun onDataChange(@NonNull p0: DataSnapshot) {
                        if (p0.child("age").exists()) {//Mirem si el nom d'usuari ja existeix a firebase. En cas que existeixi no ha de registrar i passem a Main_Interface directament
                            MainActivity.user_actual.complete_name = acct?.displayName.toString()
                            MainActivity.user_actual.name = p0.child("name").value.toString()
                            MainActivity.user_actual.age = p0.child("age").value.toString().toInt()
                            MainActivity.user_actual.height =
                                p0.child("height").value.toString().toDouble()
                            MainActivity.user_actual.weight =
                                p0.child("weight").value.toString().toDouble()
                            MainActivity.user_actual.cal = p0.child("cal").value.toString().toInt()
                        }
                    }
                })
                startActivity(Intent(this, Main_Interface::class.java))
            } else {
                startActivity(Intent(this, LogIn_Data::class.java))
            }
        }

        btn_LogOut.setOnClickListener {
            mGoogleSignInClient.signOut().addOnCompleteListener(this) {
                Toast.makeText(this, "Sessió tancada", Toast.LENGTH_SHORT).show()
            }
            startActivity(Intent(this, MainActivity::class.java))

        }
    }
}

