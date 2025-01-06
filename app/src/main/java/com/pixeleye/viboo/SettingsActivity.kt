package com.pixeleye.viboo

import android.R
import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.lifecycleScope
import com.pixeleye.viboo.databinding.ActivitySettingsBinding
import kotlinx.coroutines.launch
import kotlin.system.exitProcess


class SettingsActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySettingsBinding
    private lateinit var googleSignInClient: GoogleSignInClient
    private val activityContext: Context = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        googleSignInClient = GoogleSignInClient(this)

        binding.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        binding.genderButton.setOnClickListener {
            genderChange()
        }

        binding.guidelinesButton.setOnClickListener {
            navigateToDocuments(this, 1, binding.guidelinesButton.text.toString())
        }
        binding.termsButton.setOnClickListener {
            navigateToDocuments(this, 2, binding.termsButton.text.toString())
        }
        binding.privacyButton.setOnClickListener {
            navigateToDocuments(this, 3, binding.privacyButton.text.toString())
        }

        binding.logoutButton.setOnClickListener {
            signOutFromGoogle()
        }

        binding.deleteProfileButton.setOnClickListener {
            showAlert()
        }

    }

    private fun genderChange() {
        val textMale = "I'm a Man 👨"
        val textFemale = "I'm a Women 👩‍🦱"
        if (binding.genderButton.text == textMale) {
            binding.genderButton.text = textFemale
        } else {
            binding.genderButton.text = textMale
        }
    }

    private fun navigateToDocuments(context: Context, id: Int, title: String) {
        val intent = Intent(context, DocumentsActivity::class.java)
        intent.putExtra("id", id)
        intent.putExtra("title", title)
        startActivity(intent)
    }
    private fun signOutFromGoogle() {
        lifecycleScope.launch {
            googleSignInClient.signOut()
            println("Signed out successfully.")
            Toast.makeText(activityContext, "Signed out successfully.", Toast.LENGTH_SHORT).show()
            val intent = Intent(activityContext, LoginActivity::class.java)
            startActivity(intent)
            finishAffinity()
        }
    }

    private fun deleteProfile() {
        lifecycleScope.launch {
            lifecycleScope.launch {
                googleSignInClient.deleteProfile()
                println("Profile deleted successfully.")
                Toast.makeText(activityContext, "Profile deleted successfully.", Toast.LENGTH_SHORT).show()
                val intent = Intent(activityContext, LoginActivity::class.java)
                startActivity(intent)
                finishAffinity()
            }
        }

    }
    private fun showAlert() {
        val builder = android.app.AlertDialog.Builder(this)
        builder.setTitle("Delete Account")
        builder.setMessage("Are you sure you want to delete your account?")
        builder.setPositiveButton("Yes") { dialog, _ ->
            // Perform action on Yes
            deleteProfile()
            dialog.dismiss()
        }
        builder.setNegativeButton("No") { dialog, _ ->
            // Dismiss the dialog
            dialog.dismiss()
        }
        builder.show()
    }


}