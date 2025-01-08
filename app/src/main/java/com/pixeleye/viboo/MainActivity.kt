package com.pixeleye.viboo

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.pixeleye.viboo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var lookingTitle:String? = null
    private var locationTitle:String? = null
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        lookingTitle = getFromMap("lookingTitle")
        locationTitle = getFromMap("locationTitle")

        if (lookingTitle != null){
        binding.lookingButton.text = lookingTitle
        }
        if (locationTitle != null){
        binding.locationButton.text = locationTitle
        }

        binding.settingsButton.setOnClickListener {
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        }

        binding.lookingButton.setOnClickListener {
            val intent = Intent(this, PreferenceActivity::class.java)
            intent.putExtra("id",1)
            intent.putExtra("title",binding.lookingButton.text.toString())
            startActivity(intent)
        }
        binding.locationButton.setOnClickListener {
            val intent = Intent(this, PreferenceActivity::class.java)
            intent.putExtra("id",2)
            intent.putExtra("title",binding.locationButton.text.toString())
            startActivity(intent)
        }
    }
}