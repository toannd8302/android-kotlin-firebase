package com.example.leaningapplicationusingfirebase.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.leaningapplicationusingfirebase.R
import com.example.leaningapplicationusingfirebase.models.User


/**
 * A simple [Fragment] subclass.
 * Use the [LibraryFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LibraryFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var userList = ArrayList<User>()

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_library, container, false)



    }

}