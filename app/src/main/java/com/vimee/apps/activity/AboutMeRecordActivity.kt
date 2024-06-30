package com.vimee.apps.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.vimee.apps.R
import com.vimee.apps.databinding.ActivityAboutMeRecordBinding

class AboutMeRecordActivity : AppCompatActivity() {
    lateinit var binding:ActivityAboutMeRecordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAboutMeRecordBinding.inflate(layoutInflater)
        onClick()
        setContentView(binding.root)
    }

    private fun onClick() {
        binding.RecordVoiceLayout.setOnClickListener {
            startActivity(Intent(this@AboutMeRecordActivity,LetsMatchActivity::class.java))
        }
        binding.backIV.setOnClickListener {
            finish()
        }
    }
}