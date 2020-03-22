package com.example.automatedutilitiesapp.views.login_and_registration

import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.automatedutilitiesapp.R
import com.example.automatedutilitiesapp.databinding.FragmentRegisterBinding
import com.jakewharton.rxbinding2.view.RxView
import java.util.concurrent.TimeUnit
import kotlinx.android.synthetic.main.fragment_register.*


class RegisterFragment : Fragment() {

    private lateinit var binding: FragmentRegisterBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegisterBinding.inflate(inflater)

        navigateToLoginFragment(binding.alreadyRegisteredButton)

        // Inflate the layout for this fragment
        return binding.root
    }

    /**
     * Navigates to loginFragment
     */
    private fun navigateToLoginFragment(view: View){
        RxView.clicks(view).map {
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }.throttleFirst(1000, TimeUnit.MILLISECONDS).subscribe()
    }


    /**
     * Set color of part alreadyRegisteredButton text
     */
    private fun setRegisteredButtonTextColor(){
        val spannable = SpannableString(getString(R.string.already_member_login_me))
        spannable.setSpan(
            ForegroundColorSpan(resources.getColor(R.color.colorPrimaryDark)),
            0,
            17,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        alreadyRegisteredButton.text = spannable
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setRegisteredButtonTextColor()
    }

}
