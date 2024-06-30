package com.vimee.apps.activity


import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.media.MediaRecorder
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.vimee.apps.databinding.ActivitySpeakNowBinding
import java.io.File
import java.io.IOException


@Suppress("DEPRECATION")
class SpeakNowActivity : AppCompatActivity() {
    lateinit var binding: ActivitySpeakNowBinding
    private val AUDIO_RECORDER_FILE_EXT_3GP = ".3gp"
    private val AUDIO_RECORDER_FILE_EXT_MP4 = ".mp4"
    private val AUDIO_RECORDER_FOLDER = "AudioRecorder"
    private var recorder: MediaRecorder? = null
    val AUDIO_RECORD_REQUEST_CODE = 8001
    private val currentFormat = 0
    private val output_formats =
        intArrayOf(MediaRecorder.OutputFormat.MPEG_4, MediaRecorder.OutputFormat.THREE_GPP)
    private val file_exts = arrayOf(AUDIO_RECORDER_FILE_EXT_MP4, AUDIO_RECORDER_FILE_EXT_3GP)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySpeakNowBinding.inflate(layoutInflater)
        onClick()
        setContentView(binding.root)
        isRecordAudioPermissionGranted()

    }

    private fun isRecordAudioPermissionGranted(): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) ==
                PackageManager.PERMISSION_GRANTED
            ) {
                // put your code for Version>=Marshmallow
                true
            } else {
                if (shouldShowRequestPermissionRationale(Manifest.permission.RECORD_AUDIO)) {
                    Toast.makeText(
                        this,
                        "App required access to audio", Toast.LENGTH_SHORT
                    ).show()
                }
                requestPermissions(
                    arrayOf(
                        Manifest.permission.RECORD_AUDIO
                    ), AUDIO_RECORD_REQUEST_CODE
                )
                false
            }
        } else {
            // put your code for Version < Marshmallow
            true
        }
    }


    @SuppressLint("ClickableViewAccessibility")
    private fun onClick() {
        binding.RecordLayout.setOnClickListener {
            startActivity(Intent(this@SpeakNowActivity, AboutMeRecordActivity::class.java))
        }
        binding.backIV.setOnClickListener {
            finish()
        }

/*        binding.AudioRecorderIV.setOnTouchListener(object : View.OnTouchListener{

            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                when (event!!.action) {
                    MotionEvent.ACTION_DOWN -> {
                        Toast.makeText(this@SpeakNowActivity, "Start Recording", Toast.LENGTH_SHORT).show()
                        startRecording()
                        //binding.animationViewNoData.visibility = View.VISIBLE
                        return true
                    }
                    MotionEvent.ACTION_UP -> {
                        //binding.animationViewNoData.visibility = View.GONE
                        Toast.makeText(this@SpeakNowActivity, "stop Recording", Toast.LENGTH_SHORT).show()
                        stopRecording()
                    }
                }
                return false
            }

        })

    }
    private fun getFilename(): String? {
        val filepath = Environment.getExternalStorageDirectory().path
        val file = File(filepath, AUDIO_RECORDER_FOLDER)
        if (!file.exists()) {
            file.mkdirs()
        }
        return file.absolutePath + "/" + System.currentTimeMillis() + file_exts[currentFormat]
    }

    private fun startRecording() {
        recorder = MediaRecorder()
        recorder!!.setAudioSource(MediaRecorder.AudioSource.MIC)
        recorder!!.setOutputFormat(output_formats[currentFormat])
        recorder!!.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)
        recorder!!.setOutputFile(getFilename())
        recorder!!.setOnErrorListener(errorListener)
        recorder!!.setOnInfoListener(infoListener)
        try {
            recorder!!.prepare()
            recorder!!.start()
        } catch (e: IllegalStateException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    private val errorListener =
        MediaRecorder.OnErrorListener { mr, what, extra ->
            Log.e("TAG", ": $what, $extra")
     }*/
/*
    private val infoListener =
        MediaRecorder.OnInfoListener { mr, what, extra ->
            Log.e("TAG", "Warning:$what, $extra ")}


    private fun stopRecording() {
        if (null != recorder) {
            recorder!!.stop()
            recorder!!.reset()
            recorder!!.release()
            recorder = null
        }
    }*/
    }
}