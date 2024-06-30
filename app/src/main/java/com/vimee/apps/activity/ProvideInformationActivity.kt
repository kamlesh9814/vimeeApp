package com.vimee.apps.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.vimee.apps.R
import com.vimee.apps.databinding.ActivityProvideInformationBinding

class ProvideInformationActivity : AppCompatActivity() {
    lateinit var binding:ActivityProvideInformationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProvideInformationBinding.inflate(layoutInflater)
        onClick()
        colorChange()
        setContentView(binding.root)
    }

    private fun colorChange() {
        binding.apply {
            etNameUser.setOnClickListener {
                etNameUser.setBackgroundResource(R.drawable.item_cornors_drawable)
                etLastName.setBackgroundResource(R.drawable.cornours_drawable)
            }
            etLastName.setOnClickListener {
                etLastName.setBackgroundResource(R.drawable.item_cornors_drawable)
                etNameUser.setBackgroundResource(R.drawable.cornours_drawable)
            }
        }
    }

    private fun onClick() {
        binding.ContinueLayout.setOnClickListener {
            startActivity(Intent(this@ProvideInformationActivity,AddYourPhotoActivity::class.java))
        }
        binding.backIV.setOnClickListener {
            finish()
        }
    }
}