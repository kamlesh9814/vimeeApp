package com.vimee.apps.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.vimee.apps.R
import com.vimee.apps.databinding.ActivityNativeLanguageBinding

class NativeLanguageActivity : AppCompatActivity() {
    lateinit var binding:ActivityNativeLanguageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNativeLanguageBinding.inflate(layoutInflater)
        onClick()
        colorChange()
        setContentView(binding.root)
    }

    private fun onClick() {
        binding.ContinueLayout1.setOnClickListener {
            startActivity(Intent(this@NativeLanguageActivity,AboutMeActivity::class.java))
        }
        binding.backIV.setOnClickListener {
            finish()
        }
    }
    private fun colorChange() {
        binding.apply {

            HavingFunLayout.setOnClickListener {
                HavingFunLayout.setBackgroundResource(R.drawable.have_fun_drawable)
                ContinueLayout.setBackgroundResource(R.drawable.item_backgroubd)
                LearningLayout.setBackgroundResource(R.drawable.item_backgroubd)
                SocializingLayout.setBackgroundResource(R.drawable.item_backgroubd)

            }
            ContinueLayout.setOnClickListener {
                ContinueLayout.setBackgroundResource(R.drawable.have_fun_drawable)
                HavingFunLayout.setBackgroundResource(R.drawable.item_backgroubd)
                LearningLayout.setBackgroundResource(R.drawable.item_backgroubd)
                SocializingLayout.setBackgroundResource(R.drawable.item_backgroubd)

            }
            LearningLayout.setOnClickListener {
                LearningLayout.setBackgroundResource(R.drawable.have_fun_drawable)
                HavingFunLayout.setBackgroundResource(R.drawable.item_backgroubd)
                ContinueLayout.setBackgroundResource(R.drawable.item_backgroubd)
                SocializingLayout.setBackgroundResource(R.drawable.item_backgroubd)

            }
            SocializingLayout.setOnClickListener {
                SocializingLayout.setBackgroundResource(R.drawable.have_fun_drawable)
                HavingFunLayout.setBackgroundResource(R.drawable.item_backgroubd)
                ContinueLayout.setBackgroundResource(R.drawable.item_backgroubd)
                LearningLayout.setBackgroundResource(R.drawable.item_backgroubd)

            }
        }
    }
}