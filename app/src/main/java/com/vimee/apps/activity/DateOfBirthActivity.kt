package com.vimee.apps.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.vimee.apps.R
import com.vimee.apps.databinding.ActivityDateOfBirthBinding
import com.vimee.apps.utils.ConstantVar

class DateOfBirthActivity : AppCompatActivity() {
    lateinit var binding:ActivityDateOfBirthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDateOfBirthBinding.inflate(layoutInflater)
        onClick()
        colorChange()
        setContentView(binding.root)
    }

    private fun colorChange() {
        binding.apply {
            etDateOfBirth.setOnClickListener {
                etDateOfBirth.setBackgroundResource(R.drawable.item_cornors_drawable)
                ConstantVar.showDate(this@DateOfBirthActivity,binding.etDateOfBirth)
            }
        }
    }

    private fun onClick() {
        binding.ContinueLayout.setOnClickListener {
            startActivity(Intent(this@DateOfBirthActivity,OnlineInterestActivity::class.java))
        }
        binding.backIV.setOnClickListener {
            finish()
        }
    }
}