package com.example.befit

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_main.*

private val RC_SIGN_IN = 123
lateinit var mGoogleSignInClient : GoogleSignInClient
private lateinit var auth: FirebaseAuth
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var btn_LogIn: SignInButton = findViewById(R.id.btn_LogIn)
        var btn_LogOut : Button = findViewById(R.id.btn_LogOut)
        var btn_next : Button = findViewById(R.id.btn_next)

        //Si hem deixat la sessio inciada, entrem directament al menu d'exercicis
        val acct = GoogleSignIn.getLastSignedInAccount(this)
        if(acct != null){
            startActivity(Intent(this, Main_Interface::class.java))
            //UpdateUI
            btn_LogIn.visibility = View.GONE
            btn_LogOut.visibility = View.VISIBLE
            btn_next.visibility = View.VISIBLE

        }


        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)
        auth = FirebaseAuth.getInstance()

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
            val user = FirebaseAuth.getInstance().currentUser
            var database = FirebaseDatabase.getInstance()
            var reference = database.reference.child(auth.currentUser?.displayName.toString()) //Afegim nou usuari, o actualitzem les dades de un ja registrat
            //Actualitzem caracteristiques principals que ens dona el correu
            reference.child("Nom").setValue(user?.displayName)
            reference.child("ID").setValue(user?.uid)

            startActivity(Intent(this,LogIn_Data::class.java))
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

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        //updateUI(currentUser)
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
            firebaseAuthWithGoogle(account!!)
            //Signed in succesfully
            btn_LogIn.visibility = View.GONE
            Toast.makeText(this,"Sessió Iniciada", Toast.LENGTH_SHORT).show()

        } catch (e: ApiException) {
            // Sign in was unsuccessful
            btn_LogIn.visibility = View.VISIBLE
            Toast.makeText(this,"Error al iniciar", Toast.LENGTH_SHORT).show()
        }
    }

    private fun firebaseAuthWithGoogle(acct: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(acct.idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                   // Log.d(TAG, "signInWithCredential:success")
                    val user = auth.currentUser


                    //updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    //Log.w(TAG, "signInWithCredential:failure", task.exception)
                    Toast.makeText(this, "Authentication with firebase failed.", Toast.LENGTH_SHORT).show()
                    //updateUI(null)
                }

                // ...
            }
    }


}



