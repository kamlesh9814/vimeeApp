package com.vimee.apps.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.vimee.apps.activity.ChatDetailActivity
import com.vimee.apps.databinding.ChatLayoutBinding

class ChatAdapter(var activity: FragmentActivity) : RecyclerView.Adapter<ChatAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ChatLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ChatLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            binding.apply {
                itemView.setOnClickListener {
                    val intent = Intent(activity, ChatDetailActivity::class.java)
                    activity.startActivity(intent)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return 20
    }
}