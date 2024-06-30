package com.vimee.apps.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.vimee.apps.R
import com.vimee.apps.databinding.ActivityItsMatchBinding

class ItsMatchActivity : AppCompatActivity() {
    lateinit var binding:ActivityItsMatchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityItsMatchBinding.inflate(layoutInflater)
        onClick()
        setContentView(binding.root)
    }

    private fun onClick() {
        binding.SayHelloLayoutTextView.setOnClickListener {
            startActivity(Intent(this@ItsMatchActivity,SubscriptionActivity::class.java))
        }
    }
}