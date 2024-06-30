package com.vimee.apps.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.vimee.apps.databinding.ActivityMatchDetailBinding

class MatchDetailActivity : AppCompatActivity() {
    lateinit var binding: ActivityMatchDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMatchDetailBinding.inflate(layoutInflater)
        onClick()
        setContentView(binding.root)
    }

    private fun onClick() {
        binding.backIV.setOnClickListener {
            finish()
        }
        binding.ivProfile.setOnClickListener {
            startActivity(Intent(this@MatchDetailActivity,MainActivity::class.java))
        }
    }
}