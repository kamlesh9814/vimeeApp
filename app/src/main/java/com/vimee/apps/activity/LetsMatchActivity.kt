package com.vimee.apps.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.vimee.apps.R
import com.vimee.apps.databinding.ActivityLetsMatchBinding

class LetsMatchActivity : AppCompatActivity() {
    lateinit var binding:ActivityLetsMatchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding  = ActivityLetsMatchBinding.inflate(layoutInflater)
        onClick()
        setContentView(binding.root)
    }

    private fun onClick() {
        binding.LetsMatchLayout.setOnClickListener {
            startActivity(Intent(this@LetsMatchActivity,MyDetailActivity::class.java))
        }
    }
}