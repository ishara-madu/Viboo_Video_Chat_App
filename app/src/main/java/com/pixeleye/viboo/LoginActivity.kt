package com.pixeleye.viboo

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatCheckBox
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.google.android.material.button.MaterialButton
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {
    private lateinit var googleSignInClient: GoogleSignInClient
    private val activityContext: Context = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val facebookButton = findViewById<MaterialButton>(R.id.facebookButton)
        val googleButton = findViewById<MaterialButton>(R.id.googleButton)
        val userAgreement = findViewById<AppCompatCheckBox>(R.id.userAgreementCheckBox)

        userAgreement.setOnCheckedChangeListener { _, isChecked ->
            facebookButton.isEnabled = isChecked
            googleButton.isEnabled = isChecked
        }
        googleSignInClient = GoogleSignInClient(this)

        googleButton.setOnClickListener {
            // Launch sign-in process
            signInWithGoogle()
        }

    }

    private fun signInWithGoogle() {
        lifecycleScope.launch {
            val isSignedIn = googleSignInClient.signIn()
            if (isSignedIn) {
                println("Sign-in successful!")
                Toast.makeText(activityContext, "Sign-in successful!", Toast.LENGTH_SHORT).show()
                val intent = Intent(activityContext, MainActivity::class.java)
                startActivity(intent)
            } else {
                println("Sign-in failed.")
                Toast.makeText(activityContext, "Sign-in failed.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun signOutFromGoogle() {
        lifecycleScope.launch {
            googleSignInClient.signOut()
            println("Signed out successfully.")
        }
    }

}