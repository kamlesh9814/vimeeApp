package com.vimee.apps.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.vimee.apps.R
import com.vimee.apps.data.Data


class ChatDetailAdapter(context: Context, list: ArrayList<Data>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val VIEW_TYPE_ONE = 1
        const val VIEW_TYPE_TWO = 2
    }

    private val context: Context = context
    var list: ArrayList<Data> = list


    inner class View1Holder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var message: TextView = itemView.findViewById(R.id.chat_TV)
        var time: TextView = itemView.findViewById(R.id.time_TV)

        fun bind(position: Int) {
            val recyclerViewModel = list[position]
            message.text = recyclerViewModel.textData
            time.text = recyclerViewModel.time
        }
    }
    private inner class View2ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var message: TextView = itemView.findViewById(R.id.chat_TV)
        var time: TextView = itemView.findViewById(R.id.time_TV)

        fun bind(position: Int) {
            val recyclerViewModel = list[position]
            message.text = recyclerViewModel.textData
            time.text = recyclerViewModel.time
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):RecyclerView.ViewHolder {
        if (viewType == VIEW_TYPE_ONE) {
            return View1Holder(
                LayoutInflater.from(context).inflate(R.layout.item_container_received_message, parent, false)
            )
        }
        return View2ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_container_send_message, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (list[position].viewType == VIEW_TYPE_ONE) {
            (holder as View1Holder).bind(position)
        } else {
            (holder as View2ViewHolder).bind(position)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
    override fun getItemViewType(position: Int): Int {
        return list[position].viewType
    }
}