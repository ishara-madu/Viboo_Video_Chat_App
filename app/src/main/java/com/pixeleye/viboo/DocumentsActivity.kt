package com.pixeleye.viboo

import android.os.Build
import android.os.Bundle
import android.text.Html
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DocumentsActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_documents)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val textView: TextView = findViewById(R.id.textView)
        val emptyTextView: TextView = findViewById(R.id.emptyTextView)
        val guidelines = getString(R.string.community_guidelines)
        val termsOfService = getString(R.string.terms_of_service)
        val privacyPolicy = getString(R.string.privacy_policy)
        val toolbar: androidx.appcompat.widget.Toolbar = findViewById(R.id.toolbar)
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        val id = intent.getIntExtra("id", -1)
        val title = intent.getStringExtra("title")



        if (id == 1){
        textView.text = Html.fromHtml(guidelines, Html.FROM_HTML_MODE_LEGACY)
            toolbar.title = title
        }else if (id == 2){
            textView.text = Html.fromHtml(termsOfService, Html.FROM_HTML_MODE_LEGACY)
            toolbar.title = title
        }else if (id == 3){
            textView.text = Html.fromHtml(privacyPolicy, Html.FROM_HTML_MODE_LEGACY)
            toolbar.title = title
        }else{
            emptyTextView.visibility = View.VISIBLE
            toolbar.title = getString(R.string.app_name)
        }
    }
}