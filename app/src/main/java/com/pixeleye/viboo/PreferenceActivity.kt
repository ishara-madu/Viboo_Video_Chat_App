package com.pixeleye.viboo

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.MaterialToolbar

class PreferenceActivity : AppCompatActivity() {
    private lateinit var titles: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_preference)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val toolbar = findViewById<MaterialToolbar>(R.id.toolbarPreferences)

        val id = intent.getIntExtra("id", -1)
        titles = intent.getStringExtra("title").toString()
        when (id) {
            1 -> {
                toolbar.title = "Looking For"
                initGender()
            }

            2 -> {
                toolbar.title = "Location"
                initFlags()
            }

            else -> toolbar.title = "Unknown"
        }

        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }


    }

    private fun initFlags() {
        val items = listOf(
            Preferences(1, "https://flagcdn.com/32x24/lk.png", "Sri Lanka"),
            Preferences(2, "https://flagcdn.com/16x12/ind.png", "India"),
        )

        val recyclerView = findViewById<RecyclerView>(R.id.preferencesRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = PreferencesAdapter(items,titles,2)
    }

    private fun initGender() {
        val items = listOf(
            Preferences(1, "https://flagcdn.com/32x24/ua.png", "Male"),
            Preferences(2, "https://flagcdn.com/16x12/ua.png", "Female"),
        )

        val recyclerView = findViewById<RecyclerView>(R.id.preferencesRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = PreferencesAdapter(items,titles,1)
    }

}