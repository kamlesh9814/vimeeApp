package com.vimee.apps.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.vimee.apps.R
import com.vimee.apps.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    lateinit var binding:ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        colorChange()
        onClick()
        setContentView(binding.root)
    }

    private fun colorChange() {
        binding.apply {
            etName.setOnClickListener {
                etName.setBackgroundResource(R.drawable.item_cornors_drawable)
                etPassword.setBackgroundResource(R.drawable.cornours_drawable)
            }
            etPassword.setOnClickListener {
                etPassword.setBackgroundResource(R.drawable.item_cornors_drawable)
                etName.setBackgroundResource(R.drawable.cornours_drawable)
            }
        }
    }

    private fun onClick() {
        binding.LoginLayout.setOnClickListener {
            startActivity(Intent(this@LoginActivity,ProvideInformationActivity::class.java))
        }

        binding.ForgetPasswordTV.setOnClickListener {
            startActivity(Intent(this@LoginActivity,ForgetPasswordActivity::class.java))
        }
        binding.SignUpTV.setOnClickListener {
            startActivity(Intent(this@LoginActivity,SignUpActivity::class.java))
        }
    }
}