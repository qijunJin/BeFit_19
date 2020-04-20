package com.example.befit

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.*
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import kotlinx.android.synthetic.main.activity_main.*

private val RC_SIGN_IN = 123
lateinit var mGoogleSignInClient : GoogleSignInClient
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var btn_LogIn: SignInButton = findViewById(R.id.btn_LogIn)
        var btn_LogOut : Button = findViewById(R.id.btn_LogOut)
        var btn_next : Button = findViewById(R.id.btn_next)


        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)

        btn_LogIn.visibility = View.VISIBLE
        //Aquests botons estan ocults fins iniciar sessió
        btn_LogOut.visibility = View.GONE
        btn_next.visibility = View.GONE

        btn_LogIn.setOnClickListener {
            val signInIntent = mGoogleSignInClient.signInIntent
            startActivityForResult(signInIntent, RC_SIGN_IN)
            //Actualitzem UI, ficar-ho dins un mètode updateUI()
            btn_LogIn.visibility= View.INVISIBLE
            btn_LogOut.visibility=View.VISIBLE
            btn_next.visibility = View.VISIBLE

        }

        btn_next.setOnClickListener {
            startActivity(Intent(this,LogIn_Data::class.java))
        }

        val acct = GoogleSignIn.getLastSignedInAccount(this)
        if(acct != null){
            //UpdateUI
            btn_LogIn.visibility = View.GONE
            btn_LogOut.visibility = View.VISIBLE
            btn_next.visibility = View.VISIBLE

        }

        btn_LogOut.setOnClickListener {
            signOut()
        }

    }

    private fun signOut() {  //Tanquem sessio
        mGoogleSignInClient.signOut()
            .addOnCompleteListener(this) {
                // Update your UI here

                Toast.makeText(this,"Sessió tancada", Toast.LENGTH_SHORT).show()
            }
        //UpdateUI
        btn_LogIn.visibility = View.VISIBLE
        btn_LogOut.visibility = View.GONE
        btn_next.visibility = View.GONE
    }

    private fun updateUI(){
        //Actualitzar UI segons pertoqui
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)  //Obtenim el signedIn
                handleSignInResult(task)  //Mètode per obtenir les dades del usuari de google
        }
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount> ) {
        try{
            val account = completedTask.getResult(ApiException::class.java)
            //Signed in succesfully
            btn_LogIn.visibility = View.GONE
            Toast.makeText(this,"Sessió Iniciada", Toast.LENGTH_SHORT).show()

        } catch (e: ApiException) {
            // Sign in was unsuccessful
            btn_LogIn.visibility = View.VISIBLE
            Toast.makeText(this,"Error al iniciar", Toast.LENGTH_SHORT).show()
        }
    }


}



