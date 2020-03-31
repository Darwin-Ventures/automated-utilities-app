package com.example.automatedutilitiesapp.views.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.automatedutilitiesapp.R
import com.example.automatedutilitiesapp.databinding.FragmentLaundryBinding
import com.jakewharton.rxbinding2.view.RxView
import kotlinx.android.synthetic.main.fragment_laundry.*
import java.util.concurrent.TimeUnit

class LaundryFragment : Fragment() {

    private lateinit var binding: FragmentLaundryBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLaundryBinding.inflate(inflater)

        navigateTo(binding.oldLaundryButton, R.id.oldLaundryFragment)

        return binding.root
    }

    /**
     * Navigates to destination
     */
    private fun navigateTo(view: View, target: Int){
        RxView.clicks(view).map{
            when (findNavController().currentDestination!!.id) {
                R.id.laundryFragment -> findNavController().navigate(target)
            }
        }.throttleFirst(1000, TimeUnit.MILLISECONDS).subscribe()
    }
}
