package com.vimee.apps.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vimee.apps.R
import com.vimee.apps.activity.ChangePasswordActivity
import com.vimee.apps.activity.EditProfileActivity
import com.vimee.apps.activity.SubscriptionActivity
import com.vimee.apps.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {
    lateinit var binding:FragmentProfileBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(layoutInflater,container,false)
        onClick()
        return binding.root
    }

    private fun onClick() {
        binding.EditProfileIV.setOnClickListener {
            startActivity(Intent(requireActivity(),EditProfileActivity::class.java))
        }
        binding.ChangePasswordTV.setOnClickListener {
            startActivity(Intent(requireActivity(),ChangePasswordActivity::class.java))
        }

        binding.UpgradeSubscriptionTV.setOnClickListener {
            startActivity(Intent(requireActivity(),SubscriptionActivity::class.java))
        }
    }
}