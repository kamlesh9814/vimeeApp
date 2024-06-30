package com.vimee.apps.activity

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.vimee.apps.R
import com.vimee.apps.databinding.ActivityMainBinding
import com.vimee.apps.fragment.ChatFragment
import com.vimee.apps.fragment.HomeFragment
import com.vimee.apps.fragment.LikesFragment
import com.vimee.apps.fragment.ProfileFragment

class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        switchFragment(HomeFragment(), "", true, null)
        binding.ivHome.setImageResource(R.drawable.colored_home)
        binding.ivEllipse.isVisible = true
        binding.ivEllipse1.isVisible = false
        binding.ivEllipse2.isVisible = false
        binding.ivEllipse4.isVisible = false
        colorChange()
    }
    @SuppressLint("UseCompatLoadingForDrawables")
    private fun colorChange() {
        binding.HomeLayout.setOnClickListener {
            switchFragment(HomeFragment(), "", true, null)
            binding.ivHome.setImageResource(R.drawable.colored_home)
            binding.ivChat.setImageResource(R.drawable.chat)
            binding.ivNotification.setImageResource(R.drawable.heart)
            val highlight = ContextCompat.getDrawable( this@MainActivity,R.drawable.transparent_stork)
            binding.ivProfile.background = highlight
            binding.ivEllipse.isVisible = true
            binding.ivEllipse1.isVisible = false
            binding.ivEllipse2.isVisible = false
            binding.ivEllipse4.isVisible = false
        }

        binding.NotificationLayout.setOnClickListener {
            switchFragment(LikesFragment(), "", true, null)
            binding.ivNotification.setImageResource(R.drawable.colored_heart)
            binding.ivChat.setImageResource(R.drawable.chat)
            binding.ivHome.setImageResource(R.drawable.home)
            val highlight = ContextCompat.getDrawable( this@MainActivity,R.drawable.transparent_stork)
            binding.ivProfile.background = highlight
            binding.ivEllipse.isVisible = false
            binding.ivEllipse1.isVisible = true
            binding.ivEllipse2.isVisible = false
            binding.ivEllipse4.isVisible = false
        }
        binding.ChatLayout.setOnClickListener {
            switchFragment(ChatFragment(), "", true, null)
            binding.ivChat.setImageResource(R.drawable.colored_chat)
            binding.ivNotification.setImageResource(R.drawable.heart)
            binding.ivHome.setImageResource(R.drawable.home)
            val highlight = ContextCompat.getDrawable( this@MainActivity,R.drawable.transparent_stork)
            binding.ivProfile.background = highlight
            binding.ivEllipse.isVisible = false
            binding.ivEllipse1.isVisible = false
            binding.ivEllipse2.isVisible = true
            binding.ivEllipse4.isVisible = false

        }

        binding.profileLayout.setOnClickListener {
            switchFragment(ProfileFragment(), "", true, null)
            binding.ivChat.setImageResource(R.drawable.chat)
            binding.ivNotification.setImageResource(R.drawable.heart)
            binding.ivHome.setImageResource(R.drawable.home)
            val highlight = resources.getDrawable( R.drawable.profile_drawable)
            binding.ivProfile.background = highlight
            binding.ivEllipse.isVisible = false
            binding.ivEllipse1.isVisible = false
            binding.ivEllipse2.isVisible = false
            binding.ivEllipse4.isVisible = true

        }
    }

    private fun switchFragment(fragment: Fragment?, Tag: String?, addToStack: Boolean, bundle: Bundle?) {
        val fragmentManager: FragmentManager = supportFragmentManager
        if (fragment != null) {
            val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.main_container, fragment, Tag)
            fragmentManager.popBackStack()
            if (bundle != null) fragment.arguments = bundle
            fragmentTransaction.commit()
            fragmentManager.executePendingTransactions()
        }
    }

}