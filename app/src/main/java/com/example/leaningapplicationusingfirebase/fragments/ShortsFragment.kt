package com.example.leaningapplicationusingfirebase.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.leaningapplicationusingfirebase.R

/**
 * A simple [Fragment] subclass.
 * Use the [ShortsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ShortsFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_shorts, container, false)
    }
}