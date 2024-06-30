package com.vimee.apps.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vimee.apps.R
import com.vimee.apps.adapter.LikeDislikeAdapter
import com.vimee.apps.databinding.FragmentLikesBinding

class LikesFragment : Fragment() {
    lateinit var binding:FragmentLikesBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding  = FragmentLikesBinding.inflate(layoutInflater,container,false)
        adapterCall()
        return binding.root
    }

    private fun adapterCall() {
        binding.LikeDislikeRV.adapter = LikeDislikeAdapter(requireActivity())
    }

}