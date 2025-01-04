package com.pixeleye.viboo

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatCheckBox
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.button.MaterialButton

class LoginActivity : AppCompatActivity() {
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
    }
}