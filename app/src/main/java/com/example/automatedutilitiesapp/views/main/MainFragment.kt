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

    private lateinit var  navHostFragment: NavHostFragment
    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMainBinding.inflate(inflater)


        navHostFragment = (childFragmentManager.findFragmentById(R.id.fragmentHost) as NavHostFragment?)!!

        NavigationUI.setupWithNavController(
            binding.bottomNavigationView,
            navHostFragment.navController
        )

        setBottomNavAndToolbar()

        return binding.root
    }

    private fun setBottomNavAndToolbar() {
        navHostFragment.navController.addOnDestinationChangedListener { _, destination, _ ->
            binding.homeToolbar.menu.clear()
            binding.homeToolbar.title = destination.label!!

            when(destination.id){
                R.id.oldLaundryFragment -> {
                    binding.bottomNavigationView.visibility = View.GONE
                }
                else -> {
                    binding.bottomNavigationView.visibility = View.VISIBLE
                }
            }
        }
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
