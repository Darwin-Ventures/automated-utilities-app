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
import com.example.automatedutilitiesapp.databinding.FragmentLoginBinding
import com.jakewharton.rxbinding2.view.RxView
import kotlinx.android.synthetic.main.fragment_login.*
import java.util.concurrent.TimeUnit


class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater)

        navigateTo(binding.notMemberButton, R.id.registerFragment)

        // Inflate the layout for this fragment
        return binding.root
    }


    /**
     * Navigate to target
     */
    private fun navigateTo(view: View, destination: Int){
        RxView.clicks(view).map {
            findNavController().navigate(destination)
        }.throttleFirst(1000, TimeUnit.MILLISECONDS).subscribe()
    }

    /**
     * Set color of part notMemberButton text
     */
    private fun setNotMemberButtonTextColor() {
        val spannable = SpannableString(getString(R.string.not_a_member_sign_up_now))
        spannable.setSpan(
            ForegroundColorSpan(resources.getColor(R.color.colorPrimaryDark)),
            14,
            25,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        notMemberButton.text = spannable
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setNotMemberButtonTextColor()
    }

}
