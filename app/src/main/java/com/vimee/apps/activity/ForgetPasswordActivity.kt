package com.vimee.apps.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.vimee.apps.R
import com.vimee.apps.databinding.ActivityForgetPasswordBinding

class ForgetPasswordActivity : AppCompatActivity() {
    lateinit var binding: ActivityForgetPasswordBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForgetPasswordBinding.inflate(layoutInflater)
        onClick()
        colorChange()
        setContentView(binding.root)
    }

    private fun colorChange() {
        binding.apply {
            etEmail.setOnClickListener {
                etEmail.setBackgroundResource(R.drawable.item_cornors_drawable)

            }
        }
    }

    private fun onClick() {
        binding.SubmitLayout.setOnClickListener {
            startActivity(Intent(this@ForgetPasswordActivity, LoginActivity::class.java))
        }
        binding.backIV.setOnClickListener {
            finish()
        }
    }
}