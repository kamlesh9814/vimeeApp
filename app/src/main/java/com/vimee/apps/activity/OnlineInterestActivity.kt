package com.vimee.apps.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.vimee.apps.R
import com.vimee.apps.adapter.OnlineInterestDialogueAdapter
import com.vimee.apps.databinding.ActivityOnlineInterestBinding

class OnlineInterestActivity : AppCompatActivity() {
    lateinit var binding: ActivityOnlineInterestBinding
    var clicked = "0"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnlineInterestBinding.inflate(layoutInflater)
        onClick()
        colorChange()
        setContentView(binding.root)
    }

    private fun colorChange() {
       binding.etNameUser.setOnClickListener {
           binding.etNameUser.setBackgroundResource(R.drawable.item_cornors_drawable)
           val dialog = BottomSheetDialog(this)
           val view = layoutInflater.inflate(R.layout.dialogue_sheet_layout, null)
           val recycler = view.findViewById<RecyclerView>(R.id.recyclerView)
           recycler.adapter = OnlineInterestDialogueAdapter(this,clicked)
           dialog.setCancelable(true)
           dialog.setContentView(view)
           dialog.show()
        }
    }

    private fun onClick() {
        binding.ContinueLayout.setOnClickListener {
            startActivity(Intent(this@OnlineInterestActivity, TimeOnlineActivity::class.java))
        }
        binding.backIV.setOnClickListener {
            finish()
        }
    }
}