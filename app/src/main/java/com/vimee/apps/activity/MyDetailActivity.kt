package com.vimee.apps.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.vimee.apps.R
import com.vimee.apps.databinding.ActivityMyDetailBinding

class MyDetailActivity : AppCompatActivity() {
    lateinit var binding: ActivityMyDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyDetailBinding.inflate(layoutInflater)
        onClick()
        setContentView(binding.root)
    }

    private fun onClick() {
        binding.cardView.setOnClickListener {
            startActivity(Intent(this@MyDetailActivity,FilterActivity::class.java))
        }
        binding.backIV.setOnClickListener {
            finish()
        }
    }
}