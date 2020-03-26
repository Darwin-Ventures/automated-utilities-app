package com.example.automatedutilitiesapp.views.start

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import com.example.automatedutilitiesapp.R
import com.example.automatedutilitiesapp.databinding.FragmentStartBinding
import com.google.firebase.auth.FirebaseAuth
import com.jakewharton.rxbinding2.view.RxView
import java.util.concurrent.TimeUnit


class StartFragment : Fragment() {


    private lateinit var binding: FragmentStartBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStartBinding.inflate(inflater)

        navigate(binding.loginButton, R.id.loginFragment)
        navigate(binding.signUpButton, R.id.registerFragment)

        val user = FirebaseAuth.getInstance().currentUser
        when{
            user != null -> navigateToMainFragment()
        }

        // Inflate the layout for this fragment
        return binding.root
    }

    /**
     * Go to target (login/register fragment)
     */
    private fun navigate(view: View, target: Int){
        RxView.clicks(view).map{
            when (findNavController().currentDestination!!.id) {
                R.id.startFragment -> findNavController().navigate(target)
            }
        }.throttleFirst(1000, TimeUnit.MILLISECONDS).subscribe()
    }

    /**
     * Navigates to homeFragment
     */
    private fun navigateToMainFragment(){
        when (findNavController().currentDestination!!.id) {
            R.id.mainFragment -> findNavController().navigate(R.id.action_startFragment_to_mainFragment)
        }
    }

}
