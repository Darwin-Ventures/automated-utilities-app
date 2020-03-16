package com.example.automatedutilitiesapp.views.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.automatedutilitiesapp.R
import com.example.automatedutilitiesapp.databinding.FragmentMainBinding
import kotlinx.android.synthetic.main.fragment_main.*


class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater)

        val navHostFragment = childFragmentManager.findFragmentById(R.id.fragmentHost) as NavHostFragment?


        if (navHostFragment != null) {
            NavigationUI.setupWithNavController(
                binding.bottomNavigationView,
                navHostFragment.navController
            )
        }

        navHostFragment?.navController?.addOnDestinationChangedListener { _, destination, _ ->
            binding.homeToolbar.title = destination.label!!
        }

        // Inflate the layout for this fragment
        return binding.root
    }


    /**
     * Set bottomNavView as NavController
     */
    private fun setNavController(){
        val navController = Navigation.findNavController(requireActivity(),
            R.id.fragmentHost)
        bottomNavigationView.setupWithNavController(navController)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setNavController()
    }

}
