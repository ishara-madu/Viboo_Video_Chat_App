package com.pixeleye.viboo

import android.content.res.ColorStateList
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import com.pixeleye.viboo.databinding.ActivityVideoBinding

class VideoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityVideoBinding
    private var isReported: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVideoBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.camViewButton.setOnClickListener {
            if (binding.senderView.isVisible) {
                binding.senderView.isVisible = false
            } else {
                binding.senderView.isVisible = true
            }
        }

        binding.reportButton.setOnClickListener {
            isReported = true
            reportVideo()
        }


    }

    private fun reportVideo() {
        if (isReported) {
            binding.reportButton.imageTintList = ColorStateList.valueOf(
                ContextCompat.getColor(this, R.color.google_red)
            )
        }
    }
}