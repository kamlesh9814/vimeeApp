package com.vimee.apps.activity

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import coil.load
import com.github.dhaval2404.imagepicker.ImagePicker
import com.vimee.apps.R
import com.vimee.apps.databinding.ActivityEditProfileBinding
import com.vimee.apps.utils.ConstantVar
import okio.IOException

class EditProfileActivity : AppCompatActivity() {
    lateinit var binding:ActivityEditProfileBinding
    private var profileImage: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditProfileBinding.inflate(layoutInflater)
        onClick()
        colorChange()
        setContentView(binding.root)
    }

    private fun colorChange() {
        binding.apply {
            etName.setOnClickListener {
                etName.setBackgroundResource(R.drawable.item_cornors_drawable)
                etUserName.setBackgroundResource(R.drawable.cornours_drawable)
                etBirthday.setBackgroundResource(R.drawable.cornours_drawable)
                etSelectInterest.setBackgroundResource(R.drawable.cornours_drawable)
                etLanguage.setBackgroundResource(R.drawable.cornours_drawable)
                etPurpose.setBackgroundResource(R.drawable.cornours_drawable)
            }
            etUserName.setOnClickListener {
                etUserName.setBackgroundResource(R.drawable.item_cornors_drawable)
                etName.setBackgroundResource(R.drawable.cornours_drawable)
                etBirthday.setBackgroundResource(R.drawable.cornours_drawable)
                etSelectInterest.setBackgroundResource(R.drawable.cornours_drawable)
                etLanguage.setBackgroundResource(R.drawable.cornours_drawable)
                etPurpose.setBackgroundResource(R.drawable.cornours_drawable)
            }
            etBirthday.setOnClickListener {
                etBirthday.setBackgroundResource(R.drawable.item_cornors_drawable)
                etName.setBackgroundResource(R.drawable.cornours_drawable)
                etUserName.setBackgroundResource(R.drawable.cornours_drawable)
                etSelectInterest.setBackgroundResource(R.drawable.cornours_drawable)
                etLanguage.setBackgroundResource(R.drawable.cornours_drawable)
                etPurpose.setBackgroundResource(R.drawable.cornours_drawable)
                ConstantVar.showDate(this@EditProfileActivity,binding.etBirthday)
            }
            etSelectInterest.setOnClickListener {
                etSelectInterest.setBackgroundResource(R.drawable.item_cornors_drawable)
                etName.setBackgroundResource(R.drawable.cornours_drawable)
                etUserName.setBackgroundResource(R.drawable.cornours_drawable)
                etBirthday.setBackgroundResource(R.drawable.cornours_drawable)
                etLanguage.setBackgroundResource(R.drawable.cornours_drawable)
                etPurpose.setBackgroundResource(R.drawable.cornours_drawable)
            }
            etLanguage.setOnClickListener {
                etLanguage.setBackgroundResource(R.drawable.item_cornors_drawable)
                etName.setBackgroundResource(R.drawable.cornours_drawable)
                etUserName.setBackgroundResource(R.drawable.cornours_drawable)
                etBirthday.setBackgroundResource(R.drawable.cornours_drawable)
                etSelectInterest.setBackgroundResource(R.drawable.cornours_drawable)
                etPurpose.setBackgroundResource(R.drawable.cornours_drawable)
            }
            etPurpose.setOnClickListener {
                etPurpose.setBackgroundResource(R.drawable.item_cornors_drawable)
                etName.setBackgroundResource(R.drawable.cornours_drawable)
                etUserName.setBackgroundResource(R.drawable.cornours_drawable)
                etBirthday.setBackgroundResource(R.drawable.cornours_drawable)
                etSelectInterest.setBackgroundResource(R.drawable.cornours_drawable)
                etLanguage.setBackgroundResource(R.drawable.cornours_drawable)
            }
        }
    }

    private fun onClick() {
        binding.backIV.setOnClickListener {
            finish()
        }
        binding.tvAddPhoto.setOnClickListener {
            ImagePicker.with(this@EditProfileActivity)
                .crop()
                .compress(1024)
                .maxResultSize(1080, 1080)
                .start(111)
        }
    }
    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 111 && resultCode == RESULT_OK && data != null) {
            try {
                profileImage = data.data
                binding.profileImage.load(profileImage)
            } catch (e: IOException) {
                e.printStackTrace()
            }

        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(this, ImagePicker.getError(data), Toast.LENGTH_SHORT).show()

        }
    }
}