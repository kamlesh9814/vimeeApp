package com.vimee.apps.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.vimee.apps.R
import com.vimee.apps.databinding.ActivityDialogueVideoBinding


class OnlineInterestDialogueAdapter(var activity: FragmentActivity, var clicked: String) :
    RecyclerView.Adapter<OnlineInterestDialogueAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ActivityDialogueVideoBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ActivityDialogueVideoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
           with(holder) {
               binding.apply {
                   UnClickedIV.setOnClickListener {
                       clicked = if (clicked == "0") {
                           binding.UnClickedIV.setImageDrawable((ContextCompat.getDrawable(
                               activity,
                               R.drawable.color_checked)))
                           "1"
                       } else {
                           binding.UnClickedIV.setImageDrawable((ContextCompat.getDrawable(
                               activity,
                               R.drawable.empty_check_box)))
                           "0"
                       }
                   }
               }
           }

    }

    override fun getItemCount(): Int {
        return 10
    }
}