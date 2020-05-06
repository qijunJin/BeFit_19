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
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_main.*

private val RC_SIGN_IN = 123
lateinit var mGoogleSignInClient : GoogleSignInClient
private lateinit var auth: FirebaseAuth
class MainActivity : AppCompatActivity() {

    companion object{
        val user = FirebaseAuth.getInstance().currentUser
        var user_actual = User("","",0.0, 0.0,0)  //Dades del usuari que esta a l¡aplicaio
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        var btn_LogIn: SignInButton = findViewById(R.id.btn_LogIn)
        var btn_LogOut : Button = findViewById(R.id.btn_LogOut)
        var btn_next : Button = findViewById(R.id.btn_next)

        //Si hem deixat la sessio inciada, entrem directament al menu d'exercicis
        val acct = GoogleSignIn.getLastSignedInAccount(this)
        val sesio_activa = intent.getStringExtra(EXTRA_ENRERE)
        if(acct != null && sesio_activa == "si"){

            //UpdateUI
            btn_LogIn.visibility = View.GONE
            btn_LogOut.visibility = View.VISIBLE
            btn_next.visibility = View.VISIBLE

        }else if(acct!=null && sesio_activa!="si"){
            startActivity(Intent(this, Main_Interface::class.java))

        }else if(acct!=null){
            btn_LogIn.visibility = View.GONE
            btn_LogOut.visibility = View.VISIBLE
            btn_next.visibility = View.VISIBLE
        }else{
            btn_LogIn.visibility = View.VISIBLE
            //Aquests botons estan ocults fins iniciar sessió
            btn_LogOut.visibility = View.GONE
            btn_next.visibility = View.GONE
        }


        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)
        auth = FirebaseAuth.getInstance()


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
            user_actual = User(
                account.displayName.toString(),
                account.id.toString(),
                0.0,
                0.0,
                0
            ) //Inicialitzem user, de moment nomes amb el que tenim
            //Guardem dades inicials a firebase si es el primer cop en entrar
            var database = FirebaseDatabase.getInstance()
            var reference = database.reference.child(account.displayName.toString())
            reference.setValue(user_actual)//Afegim nou usuari, o actualitzem les dades de un ja registrat si és necessari

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




