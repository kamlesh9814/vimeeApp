package com.vimee.apps.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vimee.apps.R
import com.vimee.apps.adapter.ChatDetailAdapter
import com.vimee.apps.data.Data
import com.vimee.apps.databinding.ActivityChatDetailBinding

class ChatDetailActivity : AppCompatActivity() {
    lateinit var binding:ActivityChatDetailBinding
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setAdapter()
        binding.backIv.setOnClickListener {
            finish()
        }
    }

    private fun setAdapter() {
        val dataList = ArrayList<Data>()
        dataList.add(Data(ChatDetailAdapter.VIEW_TYPE_ONE, "1. Hi! I am in View 1", "12:00 PM"))
        dataList.add(Data(ChatDetailAdapter.VIEW_TYPE_TWO, "2. Hi! I am in View 2", "1:00 PM"))
        dataList.add(Data(ChatDetailAdapter.VIEW_TYPE_ONE, "3. Hi! I am in View 3", "2:00 PM"))
        dataList.add(Data(ChatDetailAdapter.VIEW_TYPE_TWO, "4. Hi! I am in View 4", "12:10 PM"))
        dataList.add(Data(ChatDetailAdapter.VIEW_TYPE_ONE, "5. Hi! I am in View 5", "12:40 PM"))
        dataList.add(Data(ChatDetailAdapter.VIEW_TYPE_TWO, "6. Hi! I am in View 6", "2:50 PM"))
        dataList.add(Data(ChatDetailAdapter.VIEW_TYPE_ONE, "7. Hi! I am in View 7", "2:00 PM"))
        dataList.add(Data(ChatDetailAdapter.VIEW_TYPE_TWO, "8. Hi! I am in View 8", "2:00 PM"))
        dataList.add(Data(ChatDetailAdapter.VIEW_TYPE_ONE, "9. Hi! I am in View 9", "1:00 PM"))
        dataList.add(Data(ChatDetailAdapter.VIEW_TYPE_TWO, "10. Hi! I am in View 10", "12:00 PM"))
        dataList.add(Data(ChatDetailAdapter.VIEW_TYPE_ONE, "11. Hi! I am in View 11", "2:00 PM"))
        dataList.add(Data(ChatDetailAdapter.VIEW_TYPE_TWO, "12. Hi! I am in View 12", "12:00 PM"))

        val adapter = ChatDetailAdapter(this, dataList)
        recyclerView = binding.chatRV
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }

    @Deprecated("Deprecated in Java", ReplaceWith("finish()"))
    override fun onBackPressed() {
        finish()
    }
}