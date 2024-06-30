package com.vimee.apps.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.vimee.apps.R
import com.vimee.apps.databinding.ActivityAboutMeBinding

class AboutMeActivity : AppCompatActivity() {
    lateinit var binding:ActivityAboutMeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAboutMeBinding.inflate(layoutInflater)
        onClick()
        colorChange()
        setContentView(binding.root)
    }

    private fun colorChange() {
        binding.etAboutMe.setOnClickListener {
            binding.etAboutMe.setBackgroundResource(R.drawable.item_cornors_drawable)
        }
    }

    private fun onClick() {
        binding.ContinueLayout1.setOnClickListener {
            startActivity(Intent(this@AboutMeActivity,SpeakNowActivity::class.java))
        }
        binding.backIV.setOnClickListener {
            finish()
        }
    }
}