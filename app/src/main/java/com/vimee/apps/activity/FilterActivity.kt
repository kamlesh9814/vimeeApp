package com.vimee.apps.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.vimee.apps.R
import com.vimee.apps.databinding.ActivityFilterBinding

class FilterActivity : AppCompatActivity() {
    lateinit var binding:ActivityFilterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFilterBinding.inflate(layoutInflater)
        onClick()
        setContentView(binding.root)
    }

    private fun onClick() {
        binding.ApplyLayout.setOnClickListener {
            startActivity(Intent(this@FilterActivity,ItsMatchActivity::class.java))
        }
        binding.backIV.setOnClickListener {
            finish()
        }
    }
}