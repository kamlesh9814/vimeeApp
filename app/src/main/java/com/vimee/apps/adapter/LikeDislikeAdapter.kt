package com.vimee.apps.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.vimee.apps.databinding.LikeDislikeLayoutBinding

class LikeDislikeAdapter(var activity: FragmentActivity) :
    RecyclerView.Adapter<LikeDislikeAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: LikeDislikeLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            LikeDislikeLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return 20
    }
}