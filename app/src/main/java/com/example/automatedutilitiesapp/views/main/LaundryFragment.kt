package com.example.automatedutilitiesapp.views.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.automatedutilitiesapp.R



class LaundryFragment : Fragment() {

    //private lateinit var binding: FragmentLaundryBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_laundry, container, false)
    }

}
