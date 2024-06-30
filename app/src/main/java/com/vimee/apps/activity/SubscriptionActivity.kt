package com.vimee.apps.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.vimee.apps.R
import com.vimee.apps.databinding.ActivitySubscriptionBinding

class SubscriptionActivity : AppCompatActivity() {
    lateinit var binding:ActivitySubscriptionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySubscriptionBinding.inflate(layoutInflater)
        onClick()
        setContentView(binding.root)
    }

    private fun onClick() {
        binding.TryItFreeLayout.setOnClickListener {
            startActivity(Intent(this@SubscriptionActivity,MatchDetailActivity::class.java))
        }
    }
}