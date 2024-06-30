package com.vimee.apps.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.vimee.apps.R
import com.vimee.apps.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {
    lateinit var binding:ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        onClick()
        colorChange()
        setContentView(binding.root)
    }

    private fun colorChange() {
        binding.apply {
            etNameUser.setOnClickListener {
                etNameUser.setBackgroundResource(R.drawable.item_cornors_drawable)
                etEmail.setBackgroundResource(R.drawable.cornours_drawable)
                etPassword.setBackgroundResource(R.drawable.cornours_drawable)
            }
            etEmail.setOnClickListener {
                etEmail.setBackgroundResource(R.drawable.item_cornors_drawable)
                etNameUser.setBackgroundResource(R.drawable.cornours_drawable)
                etPassword.setBackgroundResource(R.drawable.cornours_drawable)
            }
            etPassword.setOnClickListener {
                etPassword.setBackgroundResource(R.drawable.item_cornors_drawable)
                etEmail.setBackgroundResource(R.drawable.cornours_drawable)
                etNameUser.setBackgroundResource(R.drawable.cornours_drawable)
            }
        }

    }

    private fun onClick() {
        binding.SignUpLayout.setOnClickListener {
            startActivity(Intent(this@SignUpActivity,LoginActivity::class.java))
        }
        binding.SignUpTV.setOnClickListener {
            startActivity(Intent(this@SignUpActivity,LoginActivity::class.java))
        }
    }
}