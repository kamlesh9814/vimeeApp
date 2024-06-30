package com.vimee.apps.fragment
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vimee.apps.adapter.ChatAdapter
import com.vimee.apps.databinding.FragmentChatBinding

class ChatFragment : Fragment() {
    lateinit var binding:FragmentChatBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentChatBinding.inflate(layoutInflater,container,false)
        adapterCall()
        return binding.root
    }
    private fun adapterCall() {
        binding.ChatRV.adapter = ChatAdapter(requireActivity())
    }
}