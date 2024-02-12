package com.example.leaningapplicationusingfirebase

import android.app.Dialog
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.CalendarContract.Colors
import android.view.Gravity
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.fragment.app.Fragment
import com.example.leaningapplicationusingfirebase.databinding.ActivityMainBinding
import com.example.leaningapplicationusingfirebase.fragments.HomeFragment
import com.example.leaningapplicationusingfirebase.fragments.LibraryFragment
import com.example.leaningapplicationusingfirebase.fragments.ShortsFragment
import com.example.leaningapplicationusingfirebase.fragments.SubScriptionFragment
import com.google.android.material.bottomsheet.BottomSheetDialog

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(HomeFragment())
        binding.bottomNavigation.setBackground(null)

        binding.bottomNavigation.setOnItemSelectedListener {
            when(it.itemId){
                R.id.home -> replaceFragment(HomeFragment())
                R.id.shorts -> replaceFragment(ShortsFragment())
                R.id.subscriptions -> replaceFragment(SubScriptionFragment())
                R.id.library -> replaceFragment(LibraryFragment())
            }
            true
        }

        binding.fab.setOnClickListener {
            showBottomDialog()
        }

    }

    private fun replaceFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.commit()
    }
    private fun showBottomDialog() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.bottom_sheet_dialog)

        val videoLayout = dialog.findViewById<LinearLayout>(R.id.layout_video)
        val shortsLayout = dialog.findViewById<LinearLayout>(R.id.layout_shorts)
        val liveLayout = dialog.findViewById<LinearLayout>(R.id.layout_live)
        val cancelButton = dialog.findViewById<ImageView>(R.id.cancel_button)

        videoLayout.setOnClickListener {
            dialog.dismiss()
            Toast.makeText(this, "Upload a Video is clicked", Toast.LENGTH_SHORT).show()
        }

        shortsLayout.setOnClickListener {
            dialog.dismiss()
            Toast.makeText(this, "Create a short is Clicked", Toast.LENGTH_SHORT).show()
        }

        liveLayout.setOnClickListener {
            dialog.dismiss()
            Toast.makeText(this, "Go live is Clicked", Toast.LENGTH_SHORT).show()
        }

        cancelButton.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    dialog.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        dialog.window?.attributes?.windowAnimations = R.style.DialogAnimation
        dialog.window?.setGravity(Gravity.BOTTOM)
    }

}