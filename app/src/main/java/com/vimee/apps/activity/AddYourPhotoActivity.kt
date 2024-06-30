package com.vimee.apps.activity

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import coil.load
import com.github.dhaval2404.imagepicker.ImagePicker
import com.vimee.apps.R
import com.vimee.apps.databinding.ActivityAddYourPhotoBinding
import okio.IOException

@Suppress("DEPRECATION")
class AddYourPhotoActivity : AppCompatActivity() {
    lateinit var binding: ActivityAddYourPhotoBinding
    private var profileImage: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddYourPhotoBinding.inflate(layoutInflater)
        onClick()
        setContentView(binding.root)
    }

    private fun onClick() {
        binding.ContinueLayout.setOnClickListener {
            startActivity(Intent(this@AddYourPhotoActivity, DateOfBirthActivity::class.java))
        }
        binding.backIV.setOnClickListener {
            finish()
        }
        binding.ivProfile.setOnClickListener {
            ImagePicker.with(this@AddYourPhotoActivity)
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
                binding.ivProfile.load(profileImage)
            } catch (e: IOException) {
                e.printStackTrace()
            }

        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(this, ImagePicker.getError(data), Toast.LENGTH_SHORT).show()

        }
    }
}